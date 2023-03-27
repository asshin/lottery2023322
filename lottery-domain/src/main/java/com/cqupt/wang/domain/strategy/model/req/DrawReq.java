package com.cqupt.wang.domain.strategy.model.req;

/**
 * @author zsw
 * @create 2023-03-23 11:13
 */
public class DrawReq {
   private  String uId;
   private  Long strategyId;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public DrawReq(String uId, Long strategyId) {
        this.uId = uId;
        this.strategyId = strategyId;
    }

    public DrawReq() {
    }
}
