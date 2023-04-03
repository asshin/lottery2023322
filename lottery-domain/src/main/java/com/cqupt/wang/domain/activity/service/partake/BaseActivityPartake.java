package com.cqupt.wang.domain.activity.service.partake;

import com.cqupt.wang.common.Constants;
import com.cqupt.wang.common.Result;
import com.cqupt.wang.domain.activity.model.req.PartakeReq;
import com.cqupt.wang.domain.activity.model.res.PartakeResult;
import com.cqupt.wang.domain.activity.model.vo.ActivityBillVO;
import com.cqupt.wang.domain.activity.repository.IActivityRepository;
import com.cqupt.wang.domain.activity.service.partake.ActivityPartakeSupport;
import com.cqupt.wang.domain.activity.service.partake.IActivityPartake;

/**
 * @author zsw
 * @create 2023-04-03 15:48
 */
public  abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake {

    @Override
    public PartakeResult doPartake(PartakeReq req) {
        //查询活动账单
        ActivityBillVO activityBillVO = activityRepository.queryActivityBill(req);
        //活动信息校验【活动库存、状态、日期、个人参与次数】
        Result checkResult = this.checkActivityBill(req, activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())){
            return  new PartakeResult(checkResult.getCode(),checkResult.getInfo());
        }

        //扣减活动库存【目前为直接对配置库中的lottery.activity lottery.activity 直接操作表扣减库存，后续优化为Redis扣减
        Result subtractionActivityResult = this.subtractionActivityStock(req);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(subtractionActivityResult.getCode())){
            return  new PartakeResult(subtractionActivityResult.getCode(),subtractionActivityResult.getInfo());
        }
        //领取活动信息【个人用户把活动信息写入到用户表中】
        Result grabResult=this.grabActivity(req,activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(grabResult.getCode())){
            return  new PartakeResult(grabResult.getCode(),grabResult.getInfo());
        }

        //封装结果【返回的策略ID、用于继续完成抽奖步骤】
        PartakeResult partakeResult = new PartakeResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
        partakeResult.setStrategyId(activityBillVO.getStrategyId());
        return  partakeResult;
    }
    /**
     * 领取活动
     *
     * @param req 参与活动请求
     * @param activityBillVO    活动账单
     * @return 领取结果
     */
    protected abstract Result grabActivity(PartakeReq req, ActivityBillVO activityBillVO);

    /**
     * 扣减活动库存
     *
     * @param req 参与活动请求
     * @return 扣减结果
     */
    protected abstract Result subtractionActivityStock(PartakeReq req);
    /**
     * 活动信息校验处理，把活动库存、状态、日期、个人参与次数
     *
     * @param req 参与活动请求
     * @param activityBillVO     活动账单
     * @return 校验结果
     */
    protected abstract Result checkActivityBill(PartakeReq req, ActivityBillVO activityBillVO) ;


}
