package com.jm.springboottemplate.common.service.impl;

import com.jm.springboottemplate.common.domain.TestTable;
import com.jm.springboottemplate.common.mapper.TestTableMapper;
import com.jm.springboottemplate.common.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description: DemoServiceImpl, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-02-07 16:45
 **/
@Service
@Transactional(rollbackFor = Throwable.class)
public class DemoServiceImpl implements DemoService {
    @Autowired
    private TestTableMapper testTableMapper;

    @Override
    public TestTable getById(Integer id) {
        return testTableMapper.getById(id);
    }
}
