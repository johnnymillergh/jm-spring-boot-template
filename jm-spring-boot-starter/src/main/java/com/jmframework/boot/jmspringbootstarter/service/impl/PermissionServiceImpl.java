package com.jmframework.boot.jmspringbootstarter.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.domain.payload.GetApiListPLO;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.PermissionPO;
import com.jmframework.boot.jmspringbootstarter.domain.response.GetApiListRO;
import com.jmframework.boot.jmspringbootstarter.mapper.PermissionMapper;
import com.jmframework.boot.jmspringbootstarter.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
    public List<GetApiListRO> queryApiList(GetApiListPLO getApiListPLO) {
        return permissionMapper.queryApiList(new Page(getApiListPLO.getCurrentPage(), getApiListPLO.getPageSize()),
                                             getApiListPLO).getRecords();
    }

    @Override
    public boolean save(PermissionPO entity) {
        return false;
    }

    @Override
    public boolean saveBatch(Collection<PermissionPO> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<PermissionPO> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean removeById(Serializable id) {
        return false;
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return false;
    }

    @Override
    public boolean remove(Wrapper<PermissionPO> queryWrapper) {
        return false;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return false;
    }

    @Override
    public boolean updateById(PermissionPO entity) {
        return false;
    }

    @Override
    public boolean update(PermissionPO entity, Wrapper<PermissionPO> updateWrapper) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<PermissionPO> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(PermissionPO entity) {
        return false;
    }

    @Override
    public PermissionPO getById(Serializable id) {
        return null;
    }

    @Override
    public Collection<PermissionPO> listByIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public Collection<PermissionPO> listByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public PermissionPO getOne(Wrapper<PermissionPO> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<PermissionPO> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<PermissionPO> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public int count(Wrapper<PermissionPO> queryWrapper) {
        return 0;
    }

    @Override
    public List<PermissionPO> list(Wrapper<PermissionPO> queryWrapper) {
        return null;
    }

    @Override
    public IPage<PermissionPO> page(IPage<PermissionPO> page, Wrapper<PermissionPO> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<PermissionPO> queryWrapper) {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Wrapper<PermissionPO> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<PermissionPO> page, Wrapper<PermissionPO> queryWrapper) {
        return null;
    }

    @Override
    public BaseMapper<PermissionPO> getBaseMapper() {
        return null;
    }
}
