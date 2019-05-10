package com.jmframework.boot.jmspringbootstarter.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.Permission;
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
    public boolean savePermission(Permission permission) {
        return permissionMapper.save(permission) > 0;
    }

    @Override
    public List<Permission> selectByRoleIdList(List<Long> ids) {
        return permissionMapper.selectByRoleIdList(ids);
    }

    @Override
    public Permission selectApiByUrl(String url) {
        return permissionMapper.selectApiByUrl(url);
    }

    @Override
    public List<Permission> selectApisByUrlPrefix(String urlPrefix) {
        return permissionMapper.selectApisByUrlPrefix(urlPrefix);
    }

    @Override
    public boolean save(Permission entity) {
        return false;
    }

    @Override
    public boolean saveBatch(Collection<Permission> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Permission> entityList, int batchSize) {
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
    public boolean remove(Wrapper<Permission> queryWrapper) {
        return false;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return false;
    }

    @Override
    public boolean updateById(Permission entity) {
        return false;
    }

    @Override
    public boolean update(Permission entity, Wrapper<Permission> updateWrapper) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Permission> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Permission entity) {
        return false;
    }

    @Override
    public Permission getById(Serializable id) {
        return null;
    }

    @Override
    public Collection<Permission> listByIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public Collection<Permission> listByMap(Map<String, Object> columnMap) {
        return null;
    }

    @Override
    public Permission getOne(Wrapper<Permission> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Permission> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Permission> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public int count(Wrapper<Permission> queryWrapper) {
        return 0;
    }

    @Override
    public List<Permission> list(Wrapper<Permission> queryWrapper) {
        return null;
    }

    @Override
    public IPage<Permission> page(IPage<Permission> page, Wrapper<Permission> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<Permission> queryWrapper) {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Wrapper<Permission> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<Permission> page, Wrapper<Permission> queryWrapper) {
        return null;
    }

    @Override
    public BaseMapper<Permission> getBaseMapper() {
        return null;
    }
}
