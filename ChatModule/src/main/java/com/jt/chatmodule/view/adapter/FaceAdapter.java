package com.jt.chatmodule.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jt.chatmodule.R;

import java.util.List;

/**
 * @author 贾婷
 * @date 2020/1/8.
 * GitHub：https://github.com/jiating5
 * description：表情包适配器
 */
public class FaceAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public FaceAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.face_tv,item)
                .addOnClickListener(R.id.face_tv);
    }
}
