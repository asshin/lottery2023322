package com.cqupt.wang.domain.strategy.service.algorithm;

import com.cqupt.wang.domain.strategy.model.vo.AwardRateInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zsw
 * @create 2023-03-23 14:53
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm {

    private boolean isExistRateTuple;
    // 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
    private final int HASH_INCREMENT = 0x61c88647;

    // 数组初始化长度
    private final int RATE_TUPLE_LENGTH = 128;
    // 存放概率与奖品对应的散列结果，strategyId -> rateTuple
    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();
    // d -> [awardId->begin、awardI奖品区间概率值，strategyId->end]
    protected Map<Long, List<AwardRateInfo>> awardRateInfoMap = new ConcurrentHashMap<>();
    @Override
    public void initRateTuple(Long strategyId, List<AwardRateInfo> awardRateInfoList) {
        //保存奖品概率信息
        awardRateInfoMap.put(strategyId,awardRateInfoList);
        String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k -> new String[RATE_TUPLE_LENGTH]);

        int cursorVal=0;
        for (AwardRateInfo awardRateInfo:awardRateInfoList){
            int rateVal=awardRateInfo.getAwardRate().multiply(new BigDecimal(100)).intValue();
           //循环填冲散列值
            for (int i = cursorVal+1; i <=cursorVal+rateVal ; i++) {
                      rateTuple[hashIdx(i)]=awardRateInfo.getAwardId();

            }
            cursorVal+=rateVal;

        }
    }

    @Override
    public boolean isExistRateTuple(Long strategyId) {
        return rateTupleMap.containsKey(strategyId);
    }


    protected int hashIdx(int val) {
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }
}
