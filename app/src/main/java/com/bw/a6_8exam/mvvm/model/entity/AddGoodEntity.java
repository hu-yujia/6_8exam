package com.bw.a6_8exam.mvvm.model.entity;

public class AddGoodEntity {
    /**
     * errorCode : 200
     * errorMsg : 添加购物车成功
     * result : null
     */

    private int errorCode;
    private String errorMsg;
    private Object result;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
