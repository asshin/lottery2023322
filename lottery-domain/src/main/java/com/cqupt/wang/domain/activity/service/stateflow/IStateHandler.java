package com.cqupt.wang.domain.activity.service.stateflow;

import com.cqupt.wang.common.Constants;
import com.cqupt.wang.common.Result;

/**
 * @author zsw
 * @create 2023-03-29 9:37
 */
public interface IStateHandler {
    /**
     *提审
     * @author zsw
     * @param activityId
     * @param currentStatus
     * @return   审核结果
     * @throws
     * @since
     */

    Result arraignment(Long activityId, Enum<Constants.ActivityState> currentStatus);
    /**
     *审核通过
     * @author zsw
     * @param activityId
     * @param currentStatus
     * @return   审核结果
     * @throws
     * @since
     */

    Result checkPass(Long activityId, Enum<Constants.ActivityState> currentStatus);
    /**
     *审核拒绝
     * @author zsw
     * @param activityId
     * @param currentStatus
     * @return   审核结果
     * @throws
     * @since
     */

    Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentStatus);
    /**
     *撤销审核
     * @author zsw
     * @param activityId
     * @param currentStatus
     * @return   审核结果
     * @throws
     * @since
     */

    Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentStatus);
    /**
     *关闭
     * @author zsw
     * @param activityId
     * @param currentStatus
     * @return   审核结果
     * @throws
     * @since
     */

    Result close(Long activityId, Enum<Constants.ActivityState> currentStatus);

    /**
     *开启
     * @author zsw
     * @param activityId
     * @param currentStatus
     * @return   审核结果
     * @throws
     * @since
     */

    Result open(Long activityId, Enum<Constants.ActivityState> currentStatus);
    /**
     *运行活动中
     * @author zsw
     * @param activityId
     * @param currentStatus
     * @return   审核结果
     * @throws
     * @since
     */

    Result doing(Long activityId, Enum<Constants.ActivityState> currentStatus);

}
