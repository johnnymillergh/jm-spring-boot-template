package com.jm.springboottemplate.common.mapper;

import com.jm.springboottemplate.common.domain.TestTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Description: TestTableDao, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-02-07
 * @time: 16:34
 **/
@Mapper
@Component
public interface TestTableMapper {
    TestTable getById(Integer id);
}
