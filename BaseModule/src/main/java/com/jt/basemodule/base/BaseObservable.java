package com.jt.basemodule.base;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 贾婷
 * @date 2019/12/30.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class BaseObservable {
    public <T> void doObservable(Observable<T> observable1,BaseObserver<T> observer){
        observable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void doObservable(Observable observable1,Observable observable2,BaseObserver observer){
        Observable observable = Observable.merge(observable1, observable2);
        doObservable(observable,observer);
//        Observable.concatArray();
    }
}
