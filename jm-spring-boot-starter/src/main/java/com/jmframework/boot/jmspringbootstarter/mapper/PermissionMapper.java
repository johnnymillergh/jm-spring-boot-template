package com.jmframework.boot.jmspringbootstarter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: PermissionMapper, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-02 17:52
 **/
@Mapper
@Component
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * Save a permission
     *
     * @param permission permission
     * @return permission's ID
     */
    Long save(Permission permission);

    /**
     * Select permission list by role id
     *
     * @param ids Role's id list
     * @return Permission list
     */
    List<Permission> selectByRoleIdList(List<Long> ids);

    /**
     * Find permission by URL.
     *
     * @param url URL
     * @return permission
     */
    Permission selectApiByUrl(String url);

    /**
     * Find APIs by URL prefix.
     *
     * @param urlPrefix URL prefix
     * @return permissions
     */
    List<Permission> selectApisByUrlPrefix(String urlPrefix);
}
