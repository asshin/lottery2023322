package com.cqupt.wang.interfaces;

import com.cqupt.wang.common.Result;
import com.cqupt.wang.infrastructure.dao.IActivityDao;
import com.cqupt.wang.infrastructure.po.Activity;
import com.cqupt.wang.rpc.IActivityBooth;
import com.cqupt.wang.rpc.dto.ActivityDto;
import com.cqupt.wang.rpc.req.ActivityReq;
import com.cqupt.wang.rpc.res.ActivityRes;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author zsw
 * @create 2023-03-22 22:43
 */
@Service
public class ActivityBooth  implements IActivityBooth {
    @Resource
    IActivityDao activityDao;
    @Override
    public ActivityRes queryActivityById(ActivityReq req) {
        Activity activity = activityDao.queryActivityById(req.getActivityId());
        ActivityDto activityDto = new ActivityDto();
        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setState(activity.getState());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return  new ActivityRes(Result.BuildSuccessResult(),activityDto);
    }
}
