package com.jt.homemodule.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.jt.homemodule.R;

/**
 * @author 贾婷
 * @date 2020/1/5.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class CustomSingeSideView extends LinearLayout {
    public CustomSingeSideView(Context context) {
        super(context);
        addView(LayoutInflater.from(context).inflate(R.layout.custom_singeside_layout,null));
    }

    public CustomSingeSideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addView(LayoutInflater.from(context).inflate(R.layout.custom_singeside_layout,null));
    }

    public CustomSingeSideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
