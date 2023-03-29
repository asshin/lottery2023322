package com.cqupt.wang.domain.activity.service.stateflow;

import com.cqupt.wang.common.Constants;
import com.cqupt.wang.common.Result;
import com.cqupt.wang.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @author zsw
 * @create 2023-03-29 14:37
 */
public abstract class AbstractState {
    @Resource
    protected IActivityRepository activityRepository;

    /**
     * 活动提审
     * @author zsw
     * @param activity
     * @return currentState
     */

    public abstract Result arraignment(Long activity, Enum<Constants.ActivityState> currentState);
    /**
     *审核通过
     * @author zsw
     * @param activityId
     * @param currentStatus
     * @return   审核结果
     * @throws
     * @since
     */

    public abstract Result checkPass(Long activityId, Enum<Constants.ActivityState> currentStatus);
    /**
     *审核拒绝
     * @author zsw
     * @param activityId
     * @param currentStatus
     * @return   审核结果
     * @throws
     * @since
     */

    public abstract Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentStatus);
    /**
     *撤销审核
     * @author zsw
     * @param activityId
     * @param currentStatus
     * @return   审核结果
     * @throws
     * @since
     */

    public abstract Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentStatus);
    /**
     *关闭
     * @author zsw
     * @param activityId
     * @param currentStatus
     * @return   审核结果
     * @throws
     * @since
     */

    public abstract Result close(Long activityId, Enum<Constants.ActivityState> currentStatus);

    /**
     *开启
     * @author zsw
     * @param activityId
     * @param currentStatus
     * @return   审核结果
     * @throws
     * @since
     */

    public abstract Result open(Long activityId, Enum<Constants.ActivityState> currentStatus);
    /**
     *运行活动中
     * @author zsw
     * @param activityId
     * @param currentStatus
     * @return   审核结果
     * @throws
     * @since
     */

    public abstract Result doing(Long activityId, Enum<Constants.ActivityState> currentStatus);

}
