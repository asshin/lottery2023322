package com.cqupt.wang.domain.strategy.service.algorithm.impl;

import com.cqupt.wang.domain.strategy.model.aggregates.StrategyRich;
import com.cqupt.wang.domain.strategy.model.vo.AwardRateInfo;
import com.cqupt.wang.domain.strategy.repository.IStrategyRepository;
import com.cqupt.wang.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zsw
 * @create 2023-03-23 15:31
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {



        String[] rateTuple = rateTupleMap.get(strategyId);
        assert rateTuple !=null;
        //生成随机数
        int randomId = new SecureRandom().nextInt(100);
        int idx=hashIdx(randomId);
        String awardId=rateTuple[idx];
        if(excludeAwardIds.contains(awardId)){
            return  "未中奖";
        }
        return  awardId;
    }
}
