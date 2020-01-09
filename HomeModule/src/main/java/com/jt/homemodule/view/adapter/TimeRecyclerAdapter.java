package com.jt.homemodule.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jt.homemodule.R;
import com.jt.homemodule.view.bean.TimeBean;

import java.util.List;

/**
 * @author 贾婷
 * @date 2019/1/2.
 * GitHub：https://github.com/jiating5
 * description：地图
 */
public class TimeRecyclerAdapter extends BaseQuickAdapter<TimeBean, BaseViewHolder> {

    public TimeRecyclerAdapter(int layoutResId, @Nullable List<TimeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TimeBean item) {

        helper.setText(R.id.item_time,item.getTime())
        .setText(R.id.item_activity,item.getActivity())
        .setText(R.id.item_adress,item.getAddress());
    }
}
