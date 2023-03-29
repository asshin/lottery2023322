package com.cqupt.wang.domain.activity.service.stateflow.event;

import com.cqupt.wang.common.Constants;
import com.cqupt.wang.common.Result;
import com.cqupt.wang.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * @author zsw
 * @create 2023-03-29 14:45
 */
@Component
public class ArraignmentState extends AbstractState {
    @Override
    public Result arraignment(Long activity, Enum<Constants.ActivityState> currentState) {
        return   Result.buildResult(Constants.ResponseCode.UN_ERROR,"提审中，不可重复提交");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        boolean success = activityRepository.alterStatus(activityId, currentStatus, Constants.ActivityState.PASS);
        return success ?Result.buildResult(Constants.ResponseCode.SUCCESS,"审核通过"):Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        boolean success = activityRepository.alterStatus(activityId, currentStatus, Constants.ActivityState.REFUSE);
        return success?Result.buildResult(Constants.ResponseCode.SUCCESS,"已拒绝审核"):Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        boolean success = activityRepository.alterStatus(activityId, currentStatus, Constants.ActivityState.REVOKE);
        return success?Result.buildResult(Constants.ResponseCode.SUCCESS,"已经撤销审核"):Result.buildErrorResult("活动状态变更失败");
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR,"提审中，不可关闭审核");
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR,"非关闭活动状态，不可开启");
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return Result.buildResult(Constants.ResponseCode.UN_ERROR,"待审核活动不可执行活动中变更");
    }
}
