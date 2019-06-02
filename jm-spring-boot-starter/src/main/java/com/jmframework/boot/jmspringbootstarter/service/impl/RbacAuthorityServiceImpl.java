package com.jmframework.boot.jmspringbootstarter.service.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.jmframework.boot.jmspringbootstarter.configuration.CustomConfiguration;
import com.jmframework.boot.jmspringbootstarter.exception.SecurityException;
import com.jmframework.boot.jmspringbootstarter.mapper.RoleMapper;
import com.jmframework.boot.jmspringbootstarter.service.PermissionService;
import com.jmframework.boot.jmspringbootstarter.service.RbacAuthorityService;
import com.jmframework.boot.jmspringbootstarter.util.JwtUtil;
import com.jmframework.boot.jmspringbootstarterdomain.common.UserPrincipal;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.HttpStatus;
import com.jmframework.boot.jmspringbootstarterdomain.permission.constant.PermissionType;
import com.jmframework.boot.jmspringbootstarterdomain.permission.persistence.PermissionPO;
import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description: Route Authority service
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 14:25
 **/
@Component
public class RbacAuthorityServiceImpl implements RbacAuthorityService {
    private final RoleMapper roleMapper;
    private final PermissionService permissionService;
    private final RequestMappingHandlerMapping mapping;
    private final CustomConfiguration customConfiguration;
    private final JwtUtil jwtUtil;

    @Autowired
    public RbacAuthorityServiceImpl(RoleMapper roleMapper,
                                    PermissionService permissionService,
                                    RequestMappingHandlerMapping mapping,
                                    CustomConfiguration customConfiguration,
                                    JwtUtil jwtUtil) {
        this.roleMapper = roleMapper;
        this.permissionService = permissionService;
        this.mapping = mapping;
        this.customConfiguration = customConfiguration;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        String username = jwtUtil.getUsernameFromRequest(request);
        // Super user has no restriction on any requests.
        if (customConfiguration.getSuperUser().equals(username)) {
            return true;
        }

        this.checkRequest(request);

        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;

        if (userInfo instanceof UserDetails) {
            UserPrincipal principal = (UserPrincipal) userInfo;
            Long userId = principal.getId();

            List<RolePO> rolePOList = roleMapper.selectByUserId(userId);
            List<Long> roleIds = rolePOList.stream()
                                           .map(RolePO::getId)
                                           .collect(Collectors.toList());
            List<PermissionPO> permissionPOList = permissionService.selectByRoleIdList(roleIds);

            // Filter button permission for frond-end
            List<PermissionPO> btnPerms =
                    permissionPOList.stream()
                                 // Sieve out page permissions
                                 .filter(permission -> Objects.equals(permission.getType(),
                                                                    PermissionType.BUTTON.getType()))
                                 // Sieve out permission that has no URL
                                 .filter(permission -> StrUtil.isNotBlank(permission.getUrl()))
                                 // Sieve out permission that has no method
                                 .filter(permission -> StrUtil.isNotBlank(permission.getMethod()))
                                 .collect(Collectors.toList());

            for (PermissionPO btnPerm : btnPerms) {
                AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(btnPerm.getUrl(), btnPerm.getMethod());
                if (antPathMatcher.matches(request)) {
                    hasPermission = true;
                    break;
                }
            }

            return hasPermission;
        } else {
            return false;
        }
    }

    /**
     * Check request.
     *
     * @param request HTTP Request
     */
    private void checkRequest(HttpServletRequest request) {
        String currentMethod = request.getMethod();
        Multimap<String, String> urlMapping = allUrlMapping();

        for (String uri : urlMapping.keySet()) {
            // 通过 AntPathRequestMatcher 匹配 url
            // 可以通过 2 种方式创建 AntPathRequestMatcher
            // 1：new AntPathRequestMatcher(uri,method) 这种方式可以直接判断方法是否匹配，
            //  因为这里我们把 方法不匹配 自定义抛出，所以，我们使用第2种方式创建
            // 2：new AntPathRequestMatcher(uri) 这种方式不校验请求方法，只校验请求路径
            AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(uri);
            if (antPathMatcher.matches(request)) {
                if (!urlMapping.get(uri)
                               .contains(currentMethod)) {
                    throw new SecurityException(HttpStatus.METHOD_NOT_ALLOWED);
                } else {
                    return;
                }
            }
        }

        throw new SecurityException(HttpStatus.NOT_FOUND);
    }

    /**
     * 获取 所有URL Mapping，返回格式为{"/test":["GET","POST"],"/sys":["GET","DELETE"]}
     *
     * @return {@link ArrayListMultimap} 格式的 URL Mapping
     */
    private Multimap<String, String> allUrlMapping() {
        Multimap<String, String> urlMapping = ArrayListMultimap.create();

        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

        handlerMethods.forEach((key, value) -> {
            // 获取当前 key 下的获取所有URL
            Set<String> url = key.getPatternsCondition()
                                 .getPatterns();
            RequestMethodsRequestCondition method = key.getMethodsCondition();

            // 为每个URL添加所有的请求方法
            url.forEach(item -> urlMapping.putAll(item, method.getMethods()
                                                              .stream()
                                                              .map(Enum::toString)
                                                              .collect(Collectors.toList())));
        });

        return urlMapping;
    }
}