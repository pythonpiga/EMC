package com.broad.emc.module.vo;

public class ReturnData {

    private final static String SUCCESS_CODE = "200";
    private final static String FAIL_CODE = "400";
    private final static String EXCEPTION_CODE = "500";

    private String code;    // 状态码
    private Object data;    // 返回值
    private String msg;     // 描述

    public ReturnData(String code, Object data, String msg) {
        super();
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Boolean isSuccess() {
        String code = this.code;
        if (code.equals(SUCCESS_CODE)) {
            return true;
        } else {
            return false;
        }
    }

    public static ReturnData getSuccessData() {
        return new ReturnData(SUCCESS_CODE, null, "成功");
    }

    public static ReturnData getSuccessData(Object data, String msg) {
        return new ReturnData(SUCCESS_CODE, data, msg);
    }

    public static ReturnData getSuccessData(Object data) {
        return new ReturnData(SUCCESS_CODE, data, null);
    }

    public static ReturnData getFailData(String msg) {
        return new ReturnData(FAIL_CODE, null, msg);
    }

    public static ReturnData getExceptionData(String msg) {
        return new ReturnData(EXCEPTION_CODE, null, msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
