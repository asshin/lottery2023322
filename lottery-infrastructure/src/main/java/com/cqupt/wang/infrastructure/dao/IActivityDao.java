package com.cqupt.wang.infrastructure.dao;

import com.cqupt.wang.domain.activity.model.vo.AlterStateVO;
import com.cqupt.wang.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zsw
 * @create 2023-03-22 22:32
 */
@Mapper
public interface IActivityDao {
    /**
     * 插入数据
     *
     * @param req 入参
     */
    void insert(Activity req);
    /**
     * 根据活动号查询活动信息
     *
     * @param activityId 活动号
     * @return 活动信息
     */
    Activity queryActivityById(Long activityId);

    /**
     * 变更活动状态，要确保beforestate状态和数据目前状态保持一致（多线程下可能不一致）
     *
     * @param alterStateVO  [activityId、beforeState、afterState]
     * @return 更新数量
     */
    int alterState(AlterStateVO alterStateVO);
}
