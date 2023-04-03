package com.cqupt.wang.domain.activity.service.partake;

import com.cqupt.wang.domain.activity.model.req.PartakeReq;
import com.cqupt.wang.domain.activity.model.vo.ActivityBillVO;
import com.cqupt.wang.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @author zsw
 * @create 2023-04-03 14:57
 */
public class ActivityPartakeSupport {
    @Resource
    protected  IActivityRepository activityRepository;
    protected ActivityBillVO queryActivityBill(PartakeReq req){
        return  activityRepository.queryActivityBill(req);
    }
}
