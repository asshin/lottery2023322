package com.cqupt.wang;

import com.alibaba.fastjson.JSON;
import com.cqupt.wang.common.Constants;
import com.cqupt.wang.domain.award.model.req.GoodsReq;
import com.cqupt.wang.domain.award.model.res.DistributionRes;
import com.cqupt.wang.domain.award.service.factory.DistributionGoodsFactory;
import com.cqupt.wang.domain.award.service.goods.IDistributionGoods;
import com.cqupt.wang.domain.strategy.model.req.DrawReq;
import com.cqupt.wang.domain.strategy.model.res.DrawResult;
import com.cqupt.wang.domain.strategy.model.vo.DrawAwardInfo;
import com.cqupt.wang.domain.strategy.service.draw.IDrawExec;
import com.cqupt.wang.infrastructure.dao.IActivityDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zsw
 * @create 2023-03-27 10:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AwardTest {
    private Logger logger = LoggerFactory.getLogger(AwardTest.class);

    @Resource
    private IActivityDao activityDao;

    @Resource
    private IDrawExec drawExec;

    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;
    @Test
    public void test_award() {
        // 执行抽奖
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq("小傅哥", 10001L));

        // 判断抽奖结果
        Integer drawState = drawResult.getDrawState();
        if (Constants.DrawState.FAIL.getCode().equals(drawState)) {
            logger.info("未中奖 DrawAwardInfo is null");
            return;
        }

        // 封装发奖参数，orderId：2109313442431 为模拟ID，需要在用户参与领奖活动时生成
        DrawAwardInfo drawAwardInfo = drawResult.getDrawAwardInfo();
        GoodsReq goodsReq = new GoodsReq(drawResult.getuId(), "2109313442431", drawAwardInfo.getAwardId(), drawAwardInfo.getAwardName(), drawAwardInfo.getAwardContent());

        // 根据 awardType 从抽奖工厂中获取对应的发奖服务
        IDistributionGoods distributionGoodsService = distributionGoodsFactory.getDistributionGoodsService(drawAwardInfo.getAwardType());
        DistributionRes distributionRes = distributionGoodsService.doDistribution(goodsReq);

        logger.info("测试结果：{}", JSON.toJSONString(distributionRes));
    }
}
