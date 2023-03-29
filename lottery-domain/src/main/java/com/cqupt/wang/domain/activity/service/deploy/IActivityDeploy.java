package com.cqupt.wang.domain.activity.service.deploy;

import com.cqupt.wang.domain.activity.model.req.ActivityConfigReq;

/**
 * @author zsw
 * @create 2023-03-29 16:49
 */
public interface IActivityDeploy {
    /**
     * 创建活动信息
     *
     * @param req 活动配置信息
     */
    void createActivity(ActivityConfigReq req);

    /**
     * 修改活动信息
     *
     * @param req 活动配置信息
     */
    void updateActivity(ActivityConfigReq req);
}
