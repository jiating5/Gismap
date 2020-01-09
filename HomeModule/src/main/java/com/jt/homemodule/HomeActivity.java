package com.jt.homemodule;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.jt.basemodule.activity.BaseActivity;
import com.jt.commonmodule.arouter.ARouterConfig;
import com.jt.homemodule.customview.CustomButtonGroup;
import com.jt.homemodule.view.adapter.TimeRecyclerAdapter;
import com.jt.homemodule.view.bean.TimeBean;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 贾婷
 * @date 2019/12/28.
 * GitHub：https://github.com/jiating5
 * description：地图
 */

@Route(path = ARouterConfig.HOMEMODULE_MAP)
public class HomeActivity extends BaseActivity {

    @Autowired
    String usercode;

    ImageView ivCode;
    CustomButtonGroup cbg;
    RecyclerView recyclerView;
    MapView mMapView = null;
    List<TimeBean> list = new ArrayList<>();
    /**
     * 初始化地图控制器对象
     */
    AMap aMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ARouter.getInstance().inject(this);
        Toast.makeText(this,usercode,Toast.LENGTH_SHORT).show();
        cbg = findViewById(R.id.home_cbg);
        ivCode = findViewById(R.id.home_iv_code);
        initData();
        recyclerView = findViewById(R.id.main_time_recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new TimeRecyclerAdapter(R.layout.time_layout_item,list));
        //二维码
        ivCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap mBitmap  = CodeUtils.createImage(usercode, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.erweima));
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                View inflate = LayoutInflater.from(HomeActivity.this).inflate(R.layout.home_ercode_layout, null);
                ImageView imageView = inflate.findViewById(R.id.ercode_ivcode);
                imageView.setImageBitmap(mBitmap);
                builder.setView(inflate);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //联系人
        cbg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouterConfig.CHATMODULE_LINKMAN).withString("usercode",usercode).navigation();
            }
        });

        //获取地图控件引用
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        //去掉地图右下角缩放按钮
        aMap.getUiSettings().setZoomControlsEnabled(false);
    }

    private void initData() {
        list.add(new TimeBean("2019/12/19  16:30:00","床前明月光","八维游戏学院"));
        list.add(new TimeBean("2020/1/1  15:00:00","疑是地上霜","八维传媒学院"));
        list.add(new TimeBean("2018/2/9  18:30:00","举头望明月","八维高翻学院"));
        list.add(new TimeBean("2014/8/31  20:00:00","低头思故乡","八维云计算学院"));
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
