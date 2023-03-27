package com.cqupt.wang;

import com.cqupt.wang.domain.strategy.model.req.DrawReq;
import com.cqupt.wang.domain.strategy.model.res.DrawResult;
import com.cqupt.wang.domain.strategy.service.draw.impl.DrawExecImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zsw
 * @create 2023-03-23 19:51
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawTest {
    private Logger logger = LoggerFactory.getLogger(DrawExecImpl.class);
    @Resource
    DrawExecImpl drawExecImpl;
    @Test
    public  void drawTest(){
        DrawReq req = new DrawReq("xiaofuge", 10001L);
        DrawResult drawResult = drawExecImpl.doDrawExec(req);
       logger.info("执行{}策略，获奖为{}",drawResult.getStrategyId(),drawResult.getDrawAwardInfo().getAwardName());

    }
}
