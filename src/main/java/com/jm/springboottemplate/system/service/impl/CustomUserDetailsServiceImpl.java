package com.jm.springboottemplate.system.service.impl;

import com.jm.springboottemplate.system.constant.UniversalStatus;
import com.jm.springboottemplate.system.domain.UserPrincipal;
import com.jm.springboottemplate.system.domain.persistence.Permission;
import com.jm.springboottemplate.system.domain.persistence.Role;
import com.jm.springboottemplate.system.domain.persistence.User;
import com.jm.springboottemplate.system.exception.SecurityException;
import com.jm.springboottemplate.system.mapper.PermissionMapper;
import com.jm.springboottemplate.system.mapper.RoleMapper;
import com.jm.springboottemplate.system.mapper.UserMapper;
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
        User user = userMapper.findByUsernameOrEmailOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone,
                usernameOrEmailOrPhone)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + usernameOrEmailOrPhone));
        List<Role> roles = roleMapper.selectByUserId(user.getId());
        if (CollectionUtils.isEmpty(roles)) {
            throw new SecurityException(UniversalStatus.ROLE_NOT_FOUND);
        }
        List<Long> roleIds = roles.stream()
                .map(Role::getId)
                .collect(Collectors.toList());
        List<Permission> permissions = permissionMapper.selectByRoleIdList(roleIds);
        return UserPrincipal.create(user, roles, permissions);
    }
}
