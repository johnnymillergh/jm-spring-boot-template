package com.jmframework.boot.jmspringbootstarter.service.impl;

import com.jmframework.boot.jmspringbootstarter.exception.SecurityException;
import com.jmframework.boot.jmspringbootstarter.mapper.PermissionMapper;
import com.jmframework.boot.jmspringbootstarter.mapper.UserMapper;
import com.jmframework.boot.jmspringbootstarter.service.RoleService;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.HttpStatus;
import com.jmframework.boot.jmspringbootstarterdomain.permission.persistence.PermissionPO;
import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePO;
import com.jmframework.boot.jmspringbootstarterdomain.user.UserPrincipal;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>CustomUserDetailsServiceImpl</h1>
 * <p>Custom user detail service.</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-03 13:40
 **/
@Slf4j
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PermissionMapper permissionMapper;

    @Autowired
    public CustomUserDetailsServiceImpl(UserMapper userMapper,
                                        RoleService roleService,
                                        PermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String credentials) throws UsernameNotFoundException {
        UserPO user = userMapper.selectByUsernameOrEmailOrCellphone(credentials, credentials, credentials)
                                .orElseThrow(() -> {
                                    String errorMessage = "User's account not found: " + credentials;
                                    log.error(errorMessage);
                                    return new UsernameNotFoundException(errorMessage);
                                });
        List<RolePO> rolesByUserId = roleService.getRolesByUserId(user.getId());
        if (CollectionUtils.isEmpty(rolesByUserId)) {
            throw new SecurityException(HttpStatus.ROLE_NOT_FOUND);
        }
        List<Long> roleIds = rolesByUserId.stream()
                                          .map(RolePO::getId)
                                          .collect(Collectors.toList());
        List<PermissionPO> permissionList = permissionMapper.selectByRoleIdList(roleIds);
        return UserPrincipal.create(user, rolesByUserId, permissionList);
    }
}
