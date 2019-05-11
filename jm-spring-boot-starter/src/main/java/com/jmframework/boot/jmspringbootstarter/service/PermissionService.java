package com.jmframework.boot.jmspringbootstarter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.PermissionPO;
import com.jmframework.boot.jmspringbootstarter.domain.response.GetApiListRO;

import java.util.List;

/**
 * Description: PermissionService, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-10 20:45
 **/
public interface PermissionService extends IService<PermissionPO> {
    /**
     * Save permissionPO
     *
     * @param permissionPO permissionPO
     * @return true - successful operation; false - failed operation
     */
    boolean savePermission(PermissionPO permissionPO);

    /**
     * Select permission list by role id
     *
     * @param ids Role's id list
     * @return PermissionPO list
     */
    List<PermissionPO> selectByRoleIdList(List<Long> ids);

    /**
     * Find permission by URL.
     *
     * @param url URL
     * @return permission
     */
    PermissionPO selectApiByUrl(String url);

    /**
     * Find APIs by URL prefix.
     *
     * @param urlPrefix URL prefix
     * @return permissions
     */
    List<PermissionPO> selectApisByUrlPrefix(String urlPrefix);

    List<GetApiListRO> queryApiList();
}
