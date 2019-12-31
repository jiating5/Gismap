package com.jt.commonmodule.callback;

/**
 * @author 贾婷
 * @date 2019/12/29.
 * GitHub：https://github.com/jiating5
 * description：返回
 */
public interface ResultCallback<T> {
    void onSuccess(T result);
    void onFailed();
}
