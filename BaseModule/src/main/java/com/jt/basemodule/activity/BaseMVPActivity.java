package com.jt.basemodule.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jt.basemodule.base.BasePresenter;
import com.jt.basemodule.base.IModel;
import com.jt.basemodule.base.IView;

/**
 * @author 贾婷
 * @date 2019/12/27.
 * GitHub：https://github.com/jiating5
 * description：
 */
public abstract class BaseMVPActivity<T extends BasePresenter> extends BaseActivity {
    public T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initViews();
        initData();
        doEvent();
    }

    //事件处理
    protected abstract void doEvent();

    //初始化数据
    protected abstract void initData();

    //初始化视图
    protected abstract void initViews();

    protected abstract int getLayoutId();

}