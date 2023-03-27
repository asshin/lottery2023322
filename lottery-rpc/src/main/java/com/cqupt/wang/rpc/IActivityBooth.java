package com.cqupt.wang.rpc;

import com.cqupt.wang.rpc.req.ActivityReq;
import com.cqupt.wang.rpc.res.ActivityRes;

/**
 * @author zsw
 * @create 2023-03-22 23:12
 */
public interface IActivityBooth {
    ActivityRes queryActivityById(ActivityReq req);
}
