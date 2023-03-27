package com.cqupt.wang.domain.award.service.goods;

import com.cqupt.wang.domain.award.model.req.GoodsReq;
import com.cqupt.wang.domain.award.model.res.DistributionRes;

/**
 * @author zsw
 * @create 2023-03-27 9:56
 */
public interface IDistributionGoods {
    /**
     *奖品配送接口，奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     *
     * @author zsw
     * @param  req
     * @return DistributionRes
     * @throws
     * @since
     */

    DistributionRes doDistribution(GoodsReq req);
    Integer getDistributionGoodsName();
}
