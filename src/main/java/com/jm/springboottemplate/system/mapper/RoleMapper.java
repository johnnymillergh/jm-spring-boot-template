package com.jm.springboottemplate.system.mapper;

import com.jm.springboottemplate.system.domain.persistence.Role;
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
public interface RoleMapper {
    /**
     * Select role by user ID.
     *
     * @param userId User ID
     * @return Roles
     */
    List<Role> selectByUserId(Long userId);
}
