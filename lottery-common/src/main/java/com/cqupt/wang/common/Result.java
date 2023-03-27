package com.cqupt.wang.common;

import java.io.Serializable;

/**
 * @author zsw
 * @create 2023-03-22 16:12
 */
public class Result  implements Serializable {
    private static final long serialVersionUID = -3826891916021780628L;
    private  String code;
    private  String info;

    public Result(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public  static Result BuildSuccessResult(){
        return  new Result(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo());

    }
    public static Result buildErrorResult() {
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
