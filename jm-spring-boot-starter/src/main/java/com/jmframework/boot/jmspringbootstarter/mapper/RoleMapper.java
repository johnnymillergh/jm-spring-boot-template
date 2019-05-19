package com.jmframework.boot.jmspringbootstarter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.domain.payload.GetRoleListPLO;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.RolePO;
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
     * @param page           page
     * @param getRoleListPLO persistence object
     * @return role page list
     */
    List<RolePO> selectPageList(Page page, GetRoleListPLO getRoleListPLO);

    /**
     * Check role name's uniqueness
     *
     * @param roleName name of role
     * @return the occurrence of the name of role
     */
    Integer checkRoleName(String roleName);

    /**
     * Insert role
     *
     * @param po persistence object
     * @return primary key
     */
    Long insertRole(RolePO po);
}
