package com.jt.basemodule;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jt.basemodule.finalStr.AliyunUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * @author 贾婷
 * @date 2019/12/28.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ARouter
        initARouter(this);
        //初始化zxing
        ZXingLibrary.initDisplayOpinion(this);
        AliyunUtils.getInstance().init(this);
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
