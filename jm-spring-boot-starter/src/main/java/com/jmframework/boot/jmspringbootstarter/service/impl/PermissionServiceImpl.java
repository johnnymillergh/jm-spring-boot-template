package com.jmframework.boot.jmspringbootstarter.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.mapper.PermissionMapper;
import com.jmframework.boot.jmspringbootstarter.service.PermissionService;
import com.jmframework.boot.jmspringbootstarterdomain.permission.payload.GetApiListPLO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.persistence.PermissionPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: PermissionServiceImpl, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-10 20:46
 **/
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionMapper permissionMapper;

    public PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public boolean savePermission(PermissionPO permissionPO) {
        return permissionMapper.save(permissionPO) > 0;
    }

    @Override
    public List<PermissionPO> selectByRoleIdList(List<Long> ids) {
        return permissionMapper.selectByRoleIdList(ids);
    }

    @Override
    public PermissionPO selectApiByUrl(String url) {
        return permissionMapper.selectApiByUrl(url);
    }

    @Override
    public List<PermissionPO> selectApisByUrlPrefix(String urlPrefix) {
        return permissionMapper.selectApisByUrlPrefix(urlPrefix);
    }

    @Override
    public List<PermissionPO> queryApiList(GetApiListPLO getApiListPLO) {
        return permissionMapper.queryApiList(new Page(getApiListPLO.getCurrentPage(), getApiListPLO.getPageSize()),
                                             getApiListPLO).getRecords();
    }
}
