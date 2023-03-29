package com.cqupt.wang.domain.strategy.service.draw;

import com.cqupt.wang.common.Constants;
import com.cqupt.wang.domain.activity.model.vo.StrategyDetailVO;
import com.cqupt.wang.domain.strategy.model.aggregates.StrategyRich;
import com.cqupt.wang.domain.strategy.model.req.DrawReq;
import com.cqupt.wang.domain.strategy.model.res.DrawResult;
import com.cqupt.wang.domain.strategy.model.vo.*;
import com.cqupt.wang.domain.strategy.service.algorithm.IDrawAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zsw
 * @create 2023-03-24 20:46
 */
public abstract class AbstractDrawBase extends  DrawStrategySupport implements IDrawExec {
    private Logger logger = LoggerFactory.getLogger(AbstractDrawBase.class);
    @Override
    public DrawResult doDrawExec(DrawReq req) {
        //获取抽奖策略配置
        StrategyRich strategyRich = queryStrategyRich(req.getStrategyId());
        StrategyBriefVO strategy = strategyRich.getStrategy();
        List<StrategyDetailBriefVO> strategyDetailList = strategyRich.getStrategyDetailList();
        //校验和初始化数据
        checkAndInitRateData(strategy.getStrategyId(),strategy.getStrategyMode(),strategyDetailList);
        IDrawAlgorithm iDrawAlgorithm = drawAlgorithmMap.get(strategy.getStrategyMode());
        //查询由于没有库存、封控等原因不在抽奖范围的奖品
        List<String> excludes = queryAwardExcludes(strategy.getStrategyId());
        String AwardId = iDrawAlgorithm.randomDraw(strategyRich.getStrategyId(), excludes);

        //封装结果返回
        return buildDrawResult(req.getuId(),strategy.getStrategyId(),AwardId);

    }
    /**
     * 获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等，这类数据是含有业务逻辑的，所以需要由具体的实现方决定
     *
     * @param StrategyId 策略ID
     * @return 排除的奖品ID集合
     */
    public abstract List<String> queryAwardExcludes(Long StrategyId);

    /**
     * 执行抽奖算法
     *
     * @param strategyId      策略ID
     * @param drawAlgorithm   抽奖算法模型
     * @param excludeAwardIds 排除的抽奖ID集合
     * @return 中奖奖品ID
     */
    public abstract String drawStrategyAlgorithm(Long strategyId,IDrawAlgorithm drawAlgorithm,List<String> excludeAwardIds);

    /**
     * 校验抽奖策略是否已经初始化到内存
     *
     * @param strategyId         抽奖策略ID
     * @param strategyMode       抽奖策略模式
     * @param strategyDetailList 抽奖策略详情
     */
    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetailBriefVO> strategyDetailList) {
        //只有单项概率要检查是否初始化
//        if (1 != strategyMode){
//            return;
//        }
        IDrawAlgorithm drawAlgorithm = drawAlgorithmMap.get(strategyMode);

        //已经初始化，则不必重复
        boolean existRateTuple = drawAlgorithm.isExistRateTuple(strategyId);
        if (existRateTuple){
            return;
        }
        // 解析并初始化中奖概率数据到散列表
        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetailBriefVO strategyDetail : strategyDetailList) {
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }

        drawAlgorithm.initRateTuple(strategyId, awardRateInfoList);

    }

    /**
     * 包装抽奖结果
     *
     * @param uId        用户ID
     * @param strategyId 策略ID
     * @param awardId    奖品ID，null 情况：并发抽奖情况下，库存临界值1 -> 0，会有用户中奖结果为 null
     * @return 中奖结果
     */
    private DrawResult buildDrawResult(String uId,Long strategyId,String awardId) {
        if (null == awardId) {
            logger.info("执行策略抽奖完成【未中奖】，用户：{} 策略ID：{}", uId, strategyId);
            return new DrawResult(uId, strategyId, Constants.DrawState.FAIL.getCode());
        }
        AwardBriefVO award = queryAwardInfoByAwardId(awardId);
        DrawAwardInfo drawAwardInfo = new DrawAwardInfo(award.getAwardId(),award.getAwardType(), award.getAwardName(),award.getAwardContent());
        logger.info("执行策略抽奖完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{}", uId, strategyId, awardId, award.getAwardName());

        return new DrawResult(awardId,strategyId, Constants.DrawState.SUCCESS.getCode(),drawAwardInfo);
    }
}
