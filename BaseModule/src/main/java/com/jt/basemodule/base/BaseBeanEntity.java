package com.jt.basemodule.base;

/**
 * @author 贾婷
 * @date 2019/12/30.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class BaseBeanEntity<T> {
    private int code;
    private T data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
