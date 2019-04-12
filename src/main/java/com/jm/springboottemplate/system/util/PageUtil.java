package com.jm.springboottemplate.system.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.jm.springboottemplate.system.constant.Constants;
import com.jm.springboottemplate.system.payload.PageCondition;
import org.springframework.data.domain.PageRequest;

/**
 * Description: PageUtil.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-03 13:40
 **/
public class PageUtil {
    /**
     * 校验分页参数，为NULL，设置分页参数默认值
     *
     * @param condition 查询参数
     * @param clazz     类
     * @param <T>       {@link PageCondition}
     */
    public static <T extends PageCondition> void checkPageCondition(T condition, Class<T> clazz) {
        if (ObjectUtil.isNull(condition)) {
            condition = ReflectUtil.newInstance(clazz);
        }
        // 校验分页参数
        if (ObjectUtil.isNull(condition.getCurrentPage())) {
            condition.setCurrentPage(Constants.DEFAULT_CURRENT_PAGE);
        }
        if (ObjectUtil.isNull(condition.getPageSize())) {
            condition.setPageSize(Constants.DEFAULT_PAGE_SIZE);
        }
    }

    /**
     * 根据分页参数构建{@link PageRequest}
     *
     * @param condition 查询参数
     * @param <T>       {@link PageCondition}
     * @return {@link PageRequest}
     */
    public static <T extends PageCondition> PageRequest ofPageRequest(T condition) {
        return PageRequest.of(condition.getCurrentPage(), condition.getPageSize());
    }
}
