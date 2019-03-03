package com.jm.springboottemplate.common.service;

import com.jm.springboottemplate.common.domain.TestTable;

/**
 * Description: DemoService, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-02-07
 * @time: 16:44
 **/
public interface DemoService {
    /**
     * Get test table data by ID.
     *
     * @param id Primary key of test_table.
     * @return Test table data.
     */
    TestTable getById(Integer id);
}
