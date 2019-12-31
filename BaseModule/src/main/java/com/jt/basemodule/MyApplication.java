package com.jt.basemodule;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author 贾婷
 * @date 2019/12/28.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ARouter
        initARouter(this);
    }

    private void initARouter(MyApplication myApplication) {
        if (BuildConfig.DEBUG){
            //打印日志
            ARouter.openLog();
            //开启调试模式
            ARouter.openDebug();
        }
        ARouter.init(myApplication);
    }
}
