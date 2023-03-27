package com.cqupt.wang.rpc.res;


import com.cqupt.wang.common.Result;
import com.cqupt.wang.rpc.dto.ActivityDto;

import java.io.Serializable;

/**
 * @author zsw
 * @create 2023-03-22 22:56
 */
public class ActivityRes implements Serializable {
    Result result;
    ActivityDto activityDto;
    public ActivityRes() {
    }

    public ActivityRes(Result result) {
        this.result = result;
    }

    public ActivityRes(Result result, ActivityDto activity) {
        this.result = result;
        this.activityDto = activity;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public ActivityDto getActivityDto() {
        return activityDto;
    }

    public void setActivityDto(ActivityDto activityDto) {
        this.activityDto = activityDto;
    }
}
