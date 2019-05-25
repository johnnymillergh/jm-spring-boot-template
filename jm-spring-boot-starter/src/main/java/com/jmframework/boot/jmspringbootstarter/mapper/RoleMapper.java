package com.jmframework.boot.jmspringbootstarter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: RoleMapper, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-02 17:52
 **/
@Mapper
@Component
public interface RoleMapper extends BaseMapper<RolePO> {
    /**
     * Select role by user ID.
     *
     * @param userId User ID
     * @return Roles
     */
    List<RolePO> selectByUserId(Long userId);

    /**
     * Select page list
     *
     * @param page page object
     * @return role page list
     */
    List<RolePO> selectPageList(Page page);

    /**
     * Check role name's uniqueness
     *
     * @param po persistence object
     * @return the occurrence of the name of role
     */
    Integer checkRoleName(RolePO po);

    /**
     * Insert role
     *
     * @param po persistence object
     * @return primary key
     */
    Long insertRole(RolePO po);

    /**
     * Select role by name
     *
     * @param name role name
     * @return role
     */
    RolePO selectRoleByName(String name);

    /**
     * Update role by ID
     *
     * @param po persistence object
     * @return affected row
     */
    int updateRoleById(RolePO po);
}
