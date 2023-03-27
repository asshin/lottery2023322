package com.cqupt.wang.domain.award.service.factory;

import com.cqupt.wang.common.Constants;
import com.cqupt.wang.domain.award.service.goods.IDistributionGoods;
import com.cqupt.wang.domain.award.service.goods.impl.CouponGoods;
import com.cqupt.wang.domain.award.service.goods.impl.DescGoods;
import com.cqupt.wang.domain.award.service.goods.impl.PhysicalGoods;
import com.cqupt.wang.domain.award.service.goods.impl.RedeemCodeGoods;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zsw
 * @create 2023-03-27 9:55
 */

public class GoodsConfig {
    /** 奖品发放策略组 */
    protected static Map<Integer, IDistributionGoods> goodsMap = new ConcurrentHashMap<>();
    @Resource
    CouponGoods couponGoods;
    @Resource
    DescGoods descGoods;
    @Resource
    PhysicalGoods physicalGoods;
    @Resource
    RedeemCodeGoods redeemCodeGoods;
    @PostConstruct
    public  void Init(){
        goodsMap.put(Constants.AwardType.CouponGoods.getCode(),couponGoods);
        goodsMap.put(Constants.AwardType.DESC.getCode(),descGoods);
        goodsMap.put(Constants.AwardType.PhysicalGoods.getCode(),physicalGoods);
        goodsMap.put(Constants.AwardType.RedeemCodeGoods.getCode(),redeemCodeGoods);
    }
}
