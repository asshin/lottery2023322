package com.cqupt.wang.domain.strategy.model.aggregates;

import com.cqupt.wang.infrastructure.po.Strategy;
import com.cqupt.wang.infrastructure.po.StrategyDetail;

import java.util.List;

/**
 * @author zsw
 * @create 2023-03-23 11:22
 */
public class StrategyRich {
    //策略id
    private  Long strategyId;
    //策略配置
    private Strategy strategy;
    //策略详情
    private List<StrategyDetail> strategyDetailList;

    public StrategyRich(Long strategyId, Strategy strategy, List<StrategyDetail> strategyDetailList) {
        this.strategyId = strategyId;
        this.strategy = strategy;
        this.strategyDetailList = strategyDetailList;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<StrategyDetail> getStrategyDetailList() {
        return strategyDetailList;
    }

    public void setStrategyDetailList(List<StrategyDetail> strategyDetailList) {
        this.strategyDetailList = strategyDetailList;
    }

    public StrategyRich() {
    }
}
