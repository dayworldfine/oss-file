package com.oss.tool;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 5229730106751318035L;
    private boolean success;
    private String msg;
    private T data;
    private int errorCode;

    public ResponseResult() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean isError() {
        return !this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public static <T> ResponseResult<T> responseSuccessResult(T data) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(true);
        if (null != data) {
            responseResult.setData(data);
        }

        return responseResult;
    }

    public static ResponseResult responseErrorResult(String msg) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(false);
        responseResult.setMsg(msg);
        responseResult.setErrorCode(-1);
        return responseResult;
    }

    public static ResponseResult responseErrorResultWithCode(int errorCode, String msg) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(false);
        responseResult.setMsg(msg);
        responseResult.setErrorCode(errorCode);
        return responseResult;
    }

    public static ResponseResult responseResultWithErrorCode(int errorCode) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(false);
        responseResult.setMsg(ErrorCodes.get(errorCode));
        responseResult.setErrorCode(errorCode);
        return responseResult;
    }

    public static <T> ResponseResult<T> responseOK() {
        ResponseResult<T> responseResult = new ResponseResult();
        responseResult.setSuccess(true);
        responseResult.setErrorCode(0);
        return responseResult;
    }

    public static ResponseResult responseResult(boolean success) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setSuccess(success);
        return responseResult;
    }
}
