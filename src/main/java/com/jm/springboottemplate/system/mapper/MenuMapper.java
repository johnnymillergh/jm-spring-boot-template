package com.jm.springboottemplate.system.mapper;

import com.jm.springboottemplate.system.domain.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: MenuDao, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-03
 * @time: 11:56
 **/
@Component
@Mapper
public interface MenuMapper {
    /**
     * Get all menus.
     *
     * @return List of menu.
     */
    List<Menu> getAllMenus();
}
