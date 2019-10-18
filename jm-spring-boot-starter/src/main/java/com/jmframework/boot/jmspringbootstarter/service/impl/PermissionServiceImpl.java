package com.jmframework.boot.jmspringbootstarter.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.mapper.PermissionMapper;
import com.jmframework.boot.jmspringbootstarter.service.PermissionService;
import com.jmframework.boot.jmspringbootstarterdomain.permission.constant.ApiStatus;
import com.jmframework.boot.jmspringbootstarterdomain.permission.payload.GetApiListPLO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.persistence.PermissionPO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>PermissionServiceImpl</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-10 20:46
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionMapper permissionMapper;

    @Override
    public boolean savePermission(PermissionPO po) {
        return permissionMapper.save(po) > 0;
    }

    @Override
    public List<PermissionPO> selectByRoleIdList(List<Long> ids) {
        return permissionMapper.selectByRoleIdList(ids);
    }

    @Override
    public ApiStatus checkApiIsInUse(String url) {
        return permissionMapper.countInUseApiByUrl(url) == 1 ? ApiStatus.IN_USE : ApiStatus.IDLED;
    }

    @Override
    public List<PermissionPO> selectApisByUrlPrefix(String urlPrefix) {
        return permissionMapper.selectApisByUrlPrefix(urlPrefix);
    }

    @Override
    public List<PermissionPO> queryApiList(GetApiListPLO plo) {
        return permissionMapper.selectApiPageList(new Page(plo.getCurrentPage(), plo.getPageSize())).getRecords();
    }
}
