package com.jm.springboottemplate.system.payload;

import lombok.Data;

/**
 * Description: PageCondition, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-02 17:38
 **/
@Data
public class PageCondition {
    /**
     * Current page
     */
    private Integer currentPage;
    /**
     * Page size
     */
    private Integer pageSize;
}
