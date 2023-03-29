package com.cqupt.wang.domain.activity.service.deploy.Impl;

import com.alibaba.fastjson.JSON;
import com.cqupt.wang.domain.activity.model.aggregates.ActivityConfigRich;
import com.cqupt.wang.domain.activity.model.req.ActivityConfigReq;
import com.cqupt.wang.domain.activity.model.vo.ActivityVO;
import com.cqupt.wang.domain.activity.model.vo.AwardVO;
import com.cqupt.wang.domain.activity.model.vo.StrategyDetailVO;
import com.cqupt.wang.domain.activity.model.vo.StrategyVO;
import com.cqupt.wang.domain.activity.repository.IActivityRepository;
import com.cqupt.wang.domain.activity.service.deploy.IActivityDeploy;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.List;

/**
 * @author zsw
 * @create 2023-03-29 16:55
 */
@Service
public class ActivityDeployImpl implements IActivityDeploy {
  private   Logger logger= LoggerFactory.getLogger(ActivityDeployImpl.class);
    @Resource
    private  IActivityRepository iActivityRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createActivity(ActivityConfigReq req) {
           logger.info("创建活动配置开始，activityId:{}",req.getActivityId());
       ActivityConfigRich activityConfigRich = req.getActivityConfigRich();

       try {
           //添加活动配置
           ActivityVO activity = activityConfigRich.getActivity();
           iActivityRepository.addActivity(activity);
           //添加奖品配置
           List<AwardVO> awardList = activityConfigRich.getAwardList();
           iActivityRepository.addAward(awardList);
           //添加策略配置
           StrategyVO strategy = activityConfigRich.getStrategy();
           iActivityRepository.addStrategy(strategy);
           //添加策略详情配置
           List<StrategyDetailVO> strategyDetailList = strategy.getStrategyDetailList();
           iActivityRepository.addStrategyDetailList(strategyDetailList);
           logger.info("创建活动配置完成,activityId:{}",req.getActivityId());
       } catch (Exception e) {
           logger.error("创建活动配置失败，唯一索引冲突 activityId：{} reqJson：{}", req.getActivityId(), JSON.toJSONString(req), e);
           throw  e;
       }
   }

    @Override
    public void updateActivity(ActivityConfigReq req) {
        // TODO: 非核心功能后续补充
    }
}
