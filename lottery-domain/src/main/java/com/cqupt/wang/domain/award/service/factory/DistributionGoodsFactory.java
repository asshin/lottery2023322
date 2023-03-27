package com.cqupt.wang.domain.award.service.factory;

import com.cqupt.wang.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Service;
import sun.security.action.PutAllAction;

/**
 * @author zsw
 * @create 2023-03-27 10:31
 */
@Service
public class DistributionGoodsFactory extends GoodsConfig {
    public IDistributionGoods getDistributionGoodsService(Integer awardType){
        return goodsMap.get(awardType);
    }
}
