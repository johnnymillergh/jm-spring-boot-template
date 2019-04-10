package com.jm.springboottemplate.system.mapper;

import com.jm.springboottemplate.system.domain.persistence.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: PermissionMapper, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-02
 * @time: 17:52
 **/
@Mapper
@Component
public interface PermissionMapper {
    /**
     * 根据角色列表查询权限列表
     *
     * @param ids 角色id列表
     * @return 权限列表
     */
    List<Permission> selectByRoleIdList(List<Long> ids);

    /**
     * Find permission by URL.
     *
     * @param url URL
     * @return permission
     */
    Permission findApiByUrl(String url);

    /**
     * Find APIs by URL prefix.
     *
     * @param urlPrefix URL prefix
     * @return permissions
     */
    List<Permission> findApisByUrlPrefix(String urlPrefix);
}
