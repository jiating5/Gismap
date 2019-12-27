package com.jt.basemodule.base;

/**
 * @author 贾婷
 * @date 2019/12/27.
 * GitHub：https://github.com/jiating5
 * description：P层的基类
 */
public abstract class BasePresenter<V extends IView,M extends IModel> {
    public V mView;
    public M mModel;
    /**
     * 绑定View
     */
    public void AttachView(V view){
        mView = view;
    }

    /**
     * 解绑View
     */
    public void DettachView(){
        mView = null;
    }
}
