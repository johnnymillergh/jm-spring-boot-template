package com.jm.springboottemplate.common.service.impl;

import com.jm.springboottemplate.common.dao.TestTableDao;
import com.jm.springboottemplate.common.domain.TestTable;
import com.jm.springboottemplate.common.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: TestServiceImpl, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-02-07
 * @time: 16:45
 **/
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestTableDao testTableDao;

    @Override

    public TestTable getById(Integer id) {
        return testTableDao.getById(id);
    }
}
