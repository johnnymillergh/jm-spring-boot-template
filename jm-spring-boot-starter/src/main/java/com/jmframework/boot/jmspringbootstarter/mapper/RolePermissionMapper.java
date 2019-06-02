package com.jmframework.boot.jmspringbootstarter.mapper;

import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePermissionPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: RolePermissionMapper, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-02 17:55
 **/
@Mapper
@Component
public interface RolePermissionMapper {
    /**
     * Delete by role's ID
     *
     * @param po persistence object
     * @return affected row
     */
    Integer deleteByRoleId(RolePermissionPO po);

    /**
     * Insert a batch of records
     *
     * @param poList PO list
     * @return affected row
     */
    Integer insertBatch(List<RolePermissionPO> poList);
}
