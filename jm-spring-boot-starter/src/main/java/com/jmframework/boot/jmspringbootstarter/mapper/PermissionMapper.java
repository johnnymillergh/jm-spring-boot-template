package com.jmframework.boot.jmspringbootstarter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarterdomain.permission.payload.GetApiListPLO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.persistence.PermissionPO;
import com.jmframework.boot.jmspringbootstarterdomain.permission.response.GetApiListRO;
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
public interface PermissionMapper extends BaseMapper<PermissionPO> {
    /**
     * Save a permission
     *
     * @param permissionPO permissionPO
     * @return permission's ID
     */
    Long save(PermissionPO permissionPO);

    /**
     * Select permission list by role id
     *
     * @param ids Role's id list
     * @return Permission list
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

    /**
     * Query API list
     *
     * @return API list
     */
    IPage<GetApiListRO> queryApiList(Page page, GetApiListPLO getApiListPLO);
}
