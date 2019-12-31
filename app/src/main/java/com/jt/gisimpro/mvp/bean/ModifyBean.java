package com.jt.gisimpro.mvp.bean;

/**
 * @author 贾婷
 * @date 2019/12/31.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class ModifyBean {

    /**
     * code : 200
     * data : true
     * msg : 操作成功
     */

    private int code;
    private boolean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
