package com.jmframework.boot.jmspringbootstarter.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.mapper.RoleMapper;
import com.jmframework.boot.jmspringbootstarter.service.RoleService;
import com.jmframework.boot.jmspringbootstarterdomain.role.payload.GetRoleListPLO;
import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePO;
import org.apache.commons.lang3.StringUtils;
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
    public boolean checkRoleName(RolePO po) {
        return roleMapper.checkRoleName(po) == 0;
    }

    @Override
    public boolean insertRole(RolePO rolePO) {
        return roleMapper.insertRole(rolePO) > 0;
    }

    @Override
    public String handleRoleName(String roleName) {
        String processedRoleName = StringUtils.trim(roleName).toLowerCase();
        return processedRoleName.replaceAll("\\s", "_");
    }

    @Override
    public RolePO searchRole(String roleName) {
        return roleMapper.selectRoleByName(roleName);
    }

    @Override
    public boolean updateRole(RolePO po) {
        return roleMapper.updateRoleById(po) == 1;
    }
}
