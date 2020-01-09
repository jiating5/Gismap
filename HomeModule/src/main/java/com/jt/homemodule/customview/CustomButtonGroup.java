package com.jt.homemodule.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.jt.homemodule.R;

/**
 * @author 贾婷
 * @date 2019/12/28.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class CustomButtonGroup extends LinearLayout {

    public CustomButtonGroup(Context context) {
        super(context);
    }

    public CustomButtonGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomButtonGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        addView(LayoutInflater.from(context).inflate(R.layout.home_custom_bottom,null));
    }
}
