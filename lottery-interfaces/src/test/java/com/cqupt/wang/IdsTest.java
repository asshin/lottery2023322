package com.cqupt.wang;


import com.cqupt.wang.common.Constants;
import com.cqupt.wang.domain.support.ids.IIdGenerator;
import com.cqupt.wang.domain.support.ids.policy.RandomNumeric;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author zsw
 * @create 2023-03-30 14:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdsTest {
   Logger logger=LoggerFactory.getLogger(IdsTest.class);
   @Resource
    Map<Constants.Ids, IIdGenerator> idGeneratorMap;
   @Test
   public void test(){
       IIdGenerator SnowFlake = idGeneratorMap.get(Constants.Ids.SnowFlake);
       System.out.println(SnowFlake.nextId());
       IIdGenerator ShortCode = idGeneratorMap.get(Constants.Ids.ShortCode);
       System.out.println(ShortCode.nextId());
       IIdGenerator RandomNumeric = idGeneratorMap.get(Constants.Ids.RandomNumeric);
       System.out.println(RandomNumeric.nextId());
   }
}
