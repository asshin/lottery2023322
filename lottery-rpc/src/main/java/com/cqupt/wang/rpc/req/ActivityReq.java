package com.cqupt.wang.rpc.req;

import java.io.Serializable;

/**
 * @author zsw
 * @create 2023-03-22 22:46
 */
public class ActivityReq implements Serializable {
    private  Long activityId;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
