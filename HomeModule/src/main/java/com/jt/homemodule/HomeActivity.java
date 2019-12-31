package com.jt.homemodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.jt.basemodule.activity.BaseActivity;
import com.jt.commonmodule.arouter.ARouterConfig;
import com.jt.homemodule.customview.CustomButtonGroup;

/**
 * @author 贾婷
 * @date 2019/12/28.
 * GitHub：https://github.com/jiating5
 * description：地图
 */

@Route(path = ARouterConfig.HOMEMODULE_MAP)
public class HomeActivity extends BaseActivity {

    @Autowired
    String success;

    CustomButtonGroup cbg;
    MapView mMapView = null;
    /**
     * 初始化地图控制器对象
     */
    AMap aMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ARouter.getInstance().inject(this);
        Toast.makeText(this,success,Toast.LENGTH_SHORT).show();
        cbg = findViewById(R.id.home_cbg);

        //获取地图控件引用
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
