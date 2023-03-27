package com.cqupt.wang.domain.strategy.service.draw;

import com.cqupt.wang.domain.strategy.model.aggregates.StrategyRich;
import com.cqupt.wang.domain.strategy.repository.impl.StrategyRepository;
import com.cqupt.wang.infrastructure.po.Award;
import com.cqupt.wang.infrastructure.po.Strategy;

import javax.annotation.Resource;

/**
 * @author zsw
 * @create 2023-03-24 20:38
 */
public class DrawStrategySupport extends DrawConfig {
    @Resource
    protected StrategyRepository strategyRepository;


    /**
     * 查询策略配置信息
     *
     * @param strategyId 策略ID
     * @return 策略配置信息
     */
     protected  StrategyRich queryStrategyRich(Long strategyId){
         return  strategyRepository.queryStrategyRich(strategyId);
     }

    /**
     * 查询策略配置信息
     *
     * @param  awardId 奖品id
     * @return 策略配置信息
     */
    protected Award queryAwardInfoByAwardId(String awardId){
        return strategyRepository.queryAwardInfo(awardId);
    }

}
