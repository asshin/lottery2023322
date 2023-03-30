package com.cqupt.wang.domain.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import com.cqupt.wang.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zsw
 * @create 2023-03-30 10:59
 */
@Component
public class SnowFlake implements IIdGenerator {
    private Snowflake snowflake;
    @PostConstruct
    public  void init(){
        long workId;
        try {
            workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
             workId =NetUtil.getLocalhost().hashCode();
        }
        //获取5位的机器id
        workId=workId>>16&31;
        //机房id默认为1
        long dataCenterId=1L;
        snowflake= IdUtil.createSnowflake(workId,dataCenterId);
    }
    @Override
    public Long nextId() {
     return  snowflake.nextId();
    }
}
