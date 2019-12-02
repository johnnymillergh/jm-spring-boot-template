package com.jmframework.boot.jmspringbootstarterdomain.common.base.pagination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.codehaus.plexus.util.StringUtils;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <h1>PaginationBase</h1>
 * <p>PaginationBase is a common payload bean for getting pagination list, containing 2 properties:</p>
 * <ol>
 * <li>currentPage</li>
 * <li>pageSize</li>
 *
 * </ol>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 12/2/19 2:17 PM
 **/
@Data
public class PaginationBase {
    /**
     * Current page.
     */
    @JsonIgnore
    @NotNull(message = "The `current page` is required!")
    @Min(value = 1, message = "The `current page` mustn't be less than 1!")
    private Long currentPage = 1L;
    /**
     * The size of page.
     */
    @JsonIgnore
    @NotNull(message = "The size of page is required!")
    @Range(min = 10, max = 100, message = "The range of the size of page must be: 10 <= the size of page <= 100!")
    private Long pageSize = 10L;
    /**
     * The order-by field in MySQL statement.
     */
    @JsonIgnore
    private String orderBy;
    /**
     * Order rule. Default: DESC.
     */
    @JsonIgnore
    private String orderRule = "DESC";
    /**
     * Order-by statement which is being jointed to MyBatis mapper statement.
     */
    @JsonIgnore
    private String orderByStatement;

    public String getOrderByStatement() {
        if (!StringUtils.isBlank(orderBy)) {
            return String.format("%s %s %s", "ORDER BY", orderBy, orderRule);
        }
        return orderByStatement;
    }
}
