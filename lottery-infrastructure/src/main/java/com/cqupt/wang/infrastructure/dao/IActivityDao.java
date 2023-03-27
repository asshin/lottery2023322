package com.cqupt.wang.infrastructure.dao;

import com.cqupt.wang.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zsw
 * @create 2023-03-22 22:32
 */
@Mapper
public interface IActivityDao {
    void insert(Activity req);
    Activity queryActivityById(Long activity);
}
