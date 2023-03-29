package com.cqupt.wang.infrastructure.repository;

 
import com.cqupt.wang.domain.strategy.model.aggregates.StrategyRich;
import com.cqupt.wang.domain.strategy.model.vo.AwardBriefVO;
import com.cqupt.wang.domain.strategy.model.vo.StrategyBriefVO;
import com.cqupt.wang.domain.strategy.model.vo.StrategyDetailBriefVO;
import com.cqupt.wang.domain.strategy.repository.IStrategyRepository;
import com.cqupt.wang.infrastructure.dao.IAwardDao;
import com.cqupt.wang.infrastructure.dao.IStrategyDao;
import com.cqupt.wang.infrastructure.dao.IStrategyDetailDao;
import com.cqupt.wang.infrastructure.po.Award;
import com.cqupt.wang.infrastructure.po.Strategy;
import com.cqupt.wang.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zsw
 * @create 2023-03-23 14:35
 */
@Component
public class StrategyRepository implements IStrategyRepository {
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;
    @Resource
    private IAwardDao awardDao;
    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetails = strategyDetailDao.queryStrategyDetailList(strategyId);
        StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
        BeanUtils.copyProperties(strategy,strategyBriefVO);
        List<StrategyDetailBriefVO> strategyDetailBriefVOList = new ArrayList<>();
        for (StrategyDetail strategyDetail : strategyDetails) {
            StrategyDetailBriefVO strategyDetailBriefVO=new StrategyDetailBriefVO();
            BeanUtils.copyProperties(strategyDetail,strategyDetailBriefVO);
            strategyDetailBriefVOList.add(strategyDetailBriefVO);
        }
        return new StrategyRich(strategyId,strategyBriefVO,strategyDetailBriefVOList);

    }

    @Override
    public AwardBriefVO queryAwardInfo(String awardId) {
        Award award = awardDao.queryAwardInfo(awardId);
        AwardBriefVO awardBriefVO = new AwardBriefVO();
        BeanUtils.copyProperties(award,awardBriefVO);
        return  awardBriefVO;
    }

    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        StrategyDetail req = new StrategyDetail();
        req.setStrategyId(strategyId);
        req.setAwardId(awardId);
        int count = strategyDetailDao.deductStock(req);
        return count == 1;
    }
}
