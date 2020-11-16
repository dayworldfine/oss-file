package com.oss.tool;

public class ResponseModel<T> {
    private int error;
    private String message;
    private T data;
    private static final ResponseModel ok = new ResponseModel();

    public void setError(int error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getError() {
        return this.error;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public ResponseModel() {
        this.error = 10000;
        this.message = ErrorCodes.get(this.error);
        this.data = null;
    }

    public static ResponseModel OK() {
        return ok;
    }

    public ResponseModel(int error) {
        this.error = error;
        this.message = ErrorCodes.get(error);
        this.data = null;
    }

    public ResponseModel(T data) {
        this.error = 10000;
        this.message = ErrorCodes.get(this.error);
        this.data = data;
    }

    public ResponseModel(int error, T data) {
        this.error = error;
        this.message = ErrorCodes.get(error);
        this.data = data;
    }

    public static ResponseModel success() {
        return new ResponseModel(10000, "");
    }

    public static <T> ResponseModel success(T data) {
        return new ResponseModel(10000, data);
    }

    public static ResponseModel error(int error) {
        ResponseModel m = new ResponseModel();
        m.setError(error);
        m.setMessage(ErrorCodes.get(error));
        return m;
    }

    public static ResponseModel errorWithMsg(int error, String message) {
        ResponseModel m = new ResponseModel();
        m.setError(error);
        m.setMessage(message);
        return m;
    }
}
