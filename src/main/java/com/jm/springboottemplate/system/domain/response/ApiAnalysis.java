package com.jm.springboottemplate.system.domain.response;

import lombok.Data;

/**
 * Description: ApiAnalysis, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-12 18:36
 **/
@Data
public class ApiAnalysis {
    private Integer totalApiCount;
    private Integer idledApiCount;
    private Integer inUseAPiCount;
}
