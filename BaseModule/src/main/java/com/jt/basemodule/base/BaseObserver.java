package com.jt.basemodule.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author 贾婷
 * @date 2019/12/30.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
