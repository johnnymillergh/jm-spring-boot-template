package com.jm.springboottemplate.system.mapper;

import com.jm.springboottemplate.system.domain.persistance.Permission;
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
//    @Query(value = "SELECT DISTINCT sec_permission.* FROM sec_permission,sec_role,sec_role_permission WHERE sec_role.id = sec_role_permission.role_id AND sec_permission.id = sec_role_permission.permission_id AND sec_role.id IN (:ids)", nativeQuery = true)
    List<Permission> selectByRoleIdList(List<Long> ids);
}
