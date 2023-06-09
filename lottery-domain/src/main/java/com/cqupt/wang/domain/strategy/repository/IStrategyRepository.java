package com.cqupt.wang.domain.strategy.repository;

import com.cqupt.wang.domain.strategy.model.aggregates.StrategyRich;
import com.cqupt.wang.domain.strategy.model.vo.AwardBriefVO;


import java.util.List;

/**
 * @author zsw
 * @create 2023-03-23 11:30
 */
public interface IStrategyRepository {
    StrategyRich queryStrategyRich(Long strategyId);

    AwardBriefVO queryAwardInfo(String awardId);

    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     * @return           扣减结果
     */
    boolean deductStock(Long strategyId, String awardId);
}
