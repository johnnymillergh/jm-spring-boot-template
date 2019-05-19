package com.jmframework.boot.jmspringbootstarter.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.domain.payload.GetRoleListPLO;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.RolePO;
import com.jmframework.boot.jmspringbootstarter.mapper.RoleMapper;
import com.jmframework.boot.jmspringbootstarter.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>RoleServiceImpl</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-18 12:03
 **/
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RolePO> getList(GetRoleListPLO getRoleListPLO) {
        return roleMapper.selectPageList(new Page(getRoleListPLO.getCurrentPage(), getRoleListPLO.getPageSize()), null);
    }

    @Override
    public boolean checkRoleName(String roleName) {
        return roleMapper.checkRoleName(roleName) == 0;
    }

    @Override
    public boolean insertRole(RolePO rolePO) {
        return roleMapper.insertRole(rolePO) > 0;
    }
}
