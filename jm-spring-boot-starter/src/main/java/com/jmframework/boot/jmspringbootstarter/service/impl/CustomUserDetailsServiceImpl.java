package com.jmframework.boot.jmspringbootstarter.service.impl;

import com.jmframework.boot.jmspringbootstarter.constant.UniversalStatus;
import com.jmframework.boot.jmspringbootstarter.domain.UserPrincipal;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.PermissionPO;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.RolePO;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.UserPO;
import com.jmframework.boot.jmspringbootstarter.exception.SecurityException;
import com.jmframework.boot.jmspringbootstarter.mapper.PermissionMapper;
import com.jmframework.boot.jmspringbootstarter.mapper.RoleMapper;
import com.jmframework.boot.jmspringbootstarter.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: Custom user detail service.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-03 13:40
 **/
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;

    @Autowired
    public CustomUserDetailsServiceImpl(UserMapper userMapper,
                                        RoleMapper roleMapper,
                                        PermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
        UserPO userPO = userMapper.findByUsernameOrEmailOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone,
                                                                usernameOrEmailOrPhone)
                                  .orElseThrow(() -> new UsernameNotFoundException("User not found: " + usernameOrEmailOrPhone));
        List<RolePO> rolePOList = roleMapper.selectByUserId(userPO.getId());
        if (CollectionUtils.isEmpty(rolePOList)) {
            throw new SecurityException(UniversalStatus.ROLE_NOT_FOUND);
        }
        List<Long> roleIds = rolePOList.stream()
                                       .map(RolePO::getId)
                                       .collect(Collectors.toList());
        List<PermissionPO> permissionPOS = permissionMapper.selectByRoleIdList(roleIds);
        return UserPrincipal.create(userPO, rolePOList, permissionPOS);
    }
}
