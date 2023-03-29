package com.cqupt.wang.domain.activity.service.stateflow.event;

import com.cqupt.wang.common.Constants;
import com.cqupt.wang.common.Result;
import com.cqupt.wang.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @author zsw
 * @create 2023-03-29 16:04
 */
@Component
public class CloseState extends AbstractState {
    @Override
    public Result arraignment(Long activity, Enum<Constants.ActivityState> currentState) {
        return   Result.buildResult(Constants.ResponseCode.UN_ERROR,"活动关闭中，不可进行活动提审");

    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return   Result.buildResult(Constants.ResponseCode.UN_ERROR,"活动关闭中不可审核通过");

    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return   Result.buildResult(Constants.ResponseCode.UN_ERROR,"活动关闭中不可审核拒绝");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return   Result.buildResult(Constants.ResponseCode.UN_ERROR,"活动关闭中不可撤销审核");
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动关闭不可重复关闭");

    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        boolean success = activityRepository.alterStatus(activityId, currentStatus, Constants.ActivityState.OPEN);
        return success ? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动开启完成") : Result.buildErrorResult("活动状态变更失败");

    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR, "活动关闭不可变更活动中");

    }
}
