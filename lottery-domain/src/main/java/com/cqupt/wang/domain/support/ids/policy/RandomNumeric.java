package com.cqupt.wang.domain.support.ids.policy;
import org.apache.commons.lang3.RandomStringUtils;
import com.cqupt.wang.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

/**
 * @author zsw
 * @create 2023-03-30 11:10
 */
@Component
public class RandomNumeric  implements IIdGenerator {

    @Override
    public Long nextId() {

        return  Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
