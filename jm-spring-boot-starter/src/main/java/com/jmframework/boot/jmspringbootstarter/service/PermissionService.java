package com.jmframework.boot.jmspringbootstarter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.Permission;

import java.util.List;

/**
 * Description: PermissionService, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-10 20:45
 **/
public interface PermissionService extends IService<Permission> {
    /**
     * Save permission
     *
     * @param permission permission
     * @return true - successful operation; false - failed operation
     */
    boolean savePermission(Permission permission);

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
