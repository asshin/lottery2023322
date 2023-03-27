package com.cqupt.wang.domain.strategy.service.draw;

import com.cqupt.wang.domain.strategy.model.req.DrawReq;
import com.cqupt.wang.domain.strategy.model.res.DrawResult;

/**
 * @author zsw
 * @create 2023-03-23 15:59
 */
public interface IDrawExec {
    public DrawResult doDrawExec(DrawReq req);
}
