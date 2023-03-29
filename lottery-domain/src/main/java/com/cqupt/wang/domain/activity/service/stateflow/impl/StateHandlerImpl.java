package com.cqupt.wang.domain.activity.service.stateflow.impl;

import com.cqupt.wang.common.Constants;
import com.cqupt.wang.common.Result;
import com.cqupt.wang.domain.activity.service.stateflow.IStateHandler;
import com.cqupt.wang.domain.activity.service.stateflow.StateConfig;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author zsw
 * @create 2023-03-29 16:21
 */
@Service
public class StateHandlerImpl extends StateConfig implements IStateHandler {
    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).arraignment(activityId,currentStatus);

    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).checkPass(activityId,currentStatus);
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).checkRefuse(activityId,currentStatus);
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).checkRevoke(activityId,currentStatus);
    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).close(activityId,currentStatus);
    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).open(activityId,currentStatus);
    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentStatus) {
        return stateMap.get(currentStatus).doing(activityId,currentStatus);
    }
}
