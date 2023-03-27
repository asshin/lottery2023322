package com.cqupt.wang.domain.strategy.service.algorithm.impl;

import com.cqupt.wang.domain.strategy.model.vo.AwardRateInfo;
import com.cqupt.wang.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zsw
 * @create 2023-03-23 14:52
 * 默认抽奖算法，保证一定抽到奖，当一个奖品抽完后重新分配概率
 */
@Component("defaultRateRandomDrawAlgorithm")
public class DefaultRateRandomDrawAlgorithm  extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        List<AwardRateInfo> awardRateInfo = awardRateInfoMap.get(strategyId);
        List<AwardRateInfo> differenceRateInfo =new ArrayList<>();
        BigDecimal diferenceCount=BigDecimal.ZERO;
        for (AwardRateInfo awardRate : awardRateInfo) {
            if (excludeAwardIds.contains(awardRate.getAwardId())){
                continue;
            }
             differenceRateInfo.add(awardRate);
             diferenceCount=diferenceCount.add(awardRate.getAwardRate());//记录剩余的物品概率总和用于调整中间概率
        }
        //前置判断
        if (differenceRateInfo.size()==0) {
            return  "";
        }
        //只有一个奖品有余量就返回该奖品
        if(differenceRateInfo.size()==1){
            AwardRateInfo awardRateInfo1 = differenceRateInfo.get(0);
            return  awardRateInfo1.getAwardId();
        }
        // 循环获取奖品
        String awardId = "";
        int cursorVal=0;
        int randomId = new SecureRandom().nextInt(100)+1;
        for (AwardRateInfo rateInfo : differenceRateInfo) {
            //ROUND_UP
            //
            //    舍入远离零的舍入模式。
            //    在丢弃非零部分之前始终增加数字(始终对非零舍弃部分前面的数字加1)。
            //    注意，此舍入模式始终不会减少计算值的大小。
            //BigDecimal divisor 除数， int scale 精确小数位， int roundingMode 舍入模式
            int cur = rateInfo.getAwardRate().divide(diferenceCount,2,BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();
            if(cursorVal+cur>=randomId){
                awardId= rateInfo.getAwardId();
                break;
            }
            cursorVal+=cur;

        }

        return awardId;
    }
}
