package com.jmframework.boot.jmspringbootstarter.domain.payload.base;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * <h1>Pagination</h1>
 * <p>Pagination is a common payload bean for getting pagination list, containing 2 properties:</p>
 * <ol>
 * <li>currentPage</li>
 * <li>pageSize</li>
 *
 * </ol>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-11 13:42
 **/
@Data
public class Pagination {
    private static final long serialVersionUID = -8674163654434252540L;
    @Min(value = 1)
    private Long currentPage = 1L;
    @Range(min = 10, max = 100)
    private Long pageSize = 10L;
}
