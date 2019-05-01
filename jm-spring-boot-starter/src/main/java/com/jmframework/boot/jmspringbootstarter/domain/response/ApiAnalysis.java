package com.jmframework.boot.jmspringbootstarter.domain.response;

import cn.hutool.core.util.NumberUtil;
import lombok.Data;

/**
 * Description: ApiAnalysis, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-04-12 18:36
 **/
@Data
public class ApiAnalysis {
    private Integer totalApiCount = 0;
    private Integer idledApiCount = 0;
    private Integer inUseApiCount = 0;

    public void appendIdledApiCount(Integer count) {
        this.idledApiCount = NumberUtil.parseInt(NumberUtil.add(this.idledApiCount, count).toString());
    }

    public void appendInUseApiCount(Integer count) {
        this.inUseApiCount = NumberUtil.parseInt(NumberUtil.add(this.inUseApiCount, count).toString());
    }

    public void calculateSum() {
        this.totalApiCount = this.idledApiCount + this.inUseApiCount;
    }
}
