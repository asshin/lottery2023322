package com.cqupt.wang.domain.support.ids;

import com.cqupt.wang.common.Constants;
import com.cqupt.wang.domain.support.ids.policy.RandomNumeric;
import com.cqupt.wang.domain.support.ids.policy.ShortCode;
import com.cqupt.wang.domain.support.ids.policy.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zsw
 * @create 2023-03-30 14:20
 */
@Configuration
public class IdContext {
//    @Resource
//    RandomNumeric randomNumeric;
//    @Resource
//    ShortCode shortCode;
//    @Resource
//    SnowFlake snowFlake;
//   public static ConcurrentHashMap<Enum<Constants.Ids>,IIdGenerator> idMap=new ConcurrentHashMap<>();
//
//    @PostConstruct
//    public  void init(){
//        idMap.put(Constants.Ids.RandomNumeric,randomNumeric);
//        idMap.put(Constants.Ids.ShortCode,shortCode);
//        idMap.put(Constants.Ids.SnowFlake,snowFlake);
//    }


    /**
     * 创建 ID 生成策略对象，属于策略设计模式的使用方式
     *
     * @param snowFlake 雪花算法，长码，大量
     * @param shortCode 日期算法，短码，少量，全局唯一需要自己保证
     * @param randomNumeric 随机算法，短码，大量，全局唯一需要自己保证
     * @return IIdGenerator 实现类
     */
    @Bean
    public Map<Constants.Ids, IIdGenerator> idGenerator(SnowFlake snowFlake, ShortCode shortCode, RandomNumeric randomNumeric) {
        Map<Constants.Ids, IIdGenerator> idGeneratorMap = new HashMap<>(8);
        idGeneratorMap.put(Constants.Ids.SnowFlake, snowFlake);
        idGeneratorMap.put(Constants.Ids.ShortCode, shortCode);
        idGeneratorMap.put(Constants.Ids.RandomNumeric, randomNumeric);
        return idGeneratorMap;
    }
}
