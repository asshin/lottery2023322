package com.cqupt.wang.domain.activity.service.stateflow;

import com.cqupt.wang.common.Constants;
import com.cqupt.wang.domain.activity.service.stateflow.event.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zsw
 * @create 2023-03-29 16:24
 */
public class StateConfig {
    protected  ConcurrentHashMap<Enum<Constants.ActivityState>,AbstractState> stateMap =new ConcurrentHashMap<>();
    @Resource
    private ArraignmentState arraignmentState;
    @Resource
    private CloseState closeState;
    @Resource
    private DoingState doingState;
    @Resource
    private EditingState editingState;
    @Resource
    private OpenState openState;
    @Resource
    private PassState passState;
    @Resource
    private RefuseState refuseState;
    @PostConstruct
    public  void init(){
           stateMap.put(Constants.ActivityState.ARRAIGNMENT,arraignmentState);
           stateMap.put(Constants.ActivityState.CLOSE,closeState);
           stateMap.put(Constants.ActivityState.DOING,doingState);
           stateMap.put(Constants.ActivityState.EDIT,editingState);
           stateMap.put(Constants.ActivityState.OPEN,openState);
           stateMap.put(Constants.ActivityState.PASS,passState);
           stateMap.put(Constants.ActivityState.REFUSE,refuseState);
    }
}
