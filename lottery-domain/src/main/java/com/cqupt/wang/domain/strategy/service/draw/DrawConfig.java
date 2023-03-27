package com.cqupt.wang.domain.strategy.service.draw;

import com.cqupt.wang.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.cqupt.wang.domain.strategy.service.algorithm.impl.DefaultRateRandomDrawAlgorithm;
import com.cqupt.wang.domain.strategy.service.algorithm.impl.SingleRateRandomDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zsw
 * @create 2023-03-23 15:50
 */
public class DrawConfig {
    @Resource
    DefaultRateRandomDrawAlgorithm defaultRateRandomDrawAlgorithm;
    @Resource
    SingleRateRandomDrawAlgorithm singleRateRandomDrawAlgorithm;

    protected  static Map<Integer, IDrawAlgorithm> drawAlgorithmMap=new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        drawAlgorithmMap.put(2, defaultRateRandomDrawAlgorithm);
        drawAlgorithmMap.put(1, singleRateRandomDrawAlgorithm);
    }

}
