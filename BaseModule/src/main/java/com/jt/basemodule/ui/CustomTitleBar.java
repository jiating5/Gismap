package com.jt.basemodule.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jt.basemodule.R;

/**
 * @author 贾婷
 * @date 2019/1/1.
 * GitHub：https://github.com/jiating5
 * description：自定义标题
 */

public class CustomTitleBar extends LinearLayout {
    ImageView ivCustomViewTitlebarLeft;
    ImageView ivCustomViewTitlebarRight;
    TextView tvCustomViewTitlebarTitle;
    TextView tvCustomViewTitlebarRight;
    public CustomTitleBar(Context context) {
        super(context);
    }

    public CustomTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public CustomTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.customview_titlebar, null);
        ivCustomViewTitlebarLeft = view.findViewById(R.id.iv_customview_titlebar_left);
        ivCustomViewTitlebarRight = view.findViewById(R.id.iv_customview_titlebar_right);
        tvCustomViewTitlebarTitle = view.findViewById(R.id.tv_customview_titlebar_title);

        tvCustomViewTitlebarRight = view.findViewById(R.id.tv_customview_titlebar_right);

        //找Id
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        int titleName = typedArray.getResourceId(R.styleable.CustomTitleBar_titlebarName, 0);
        int leftSrc = typedArray.getResourceId(R.styleable.CustomTitleBar_leftSrc, 0);
        int rightSrc = typedArray.getResourceId(R.styleable.CustomTitleBar_rightSrc, 0);
        boolean isRightText = typedArray.getBoolean(R.styleable.CustomTitleBar_rightTypeText, true);


        if (0!=titleName){
            tvCustomViewTitlebarTitle.setText(titleName);
        }

        if (0!=leftSrc){
            ivCustomViewTitlebarLeft.setImageResource(leftSrc);
        }

        if (0!=rightSrc){
            if (isRightText){
                tvCustomViewTitlebarRight.setText(rightSrc);
                ivCustomViewTitlebarRight.setVisibility(GONE);
            }else {
                ivCustomViewTitlebarRight.setImageResource(rightSrc);
                tvCustomViewTitlebarRight.setVisibility(GONE);
            }
        }

        //释放
        typedArray.recycle();
        this.addView(view);
    }

    //左边的控件点击事件
    public void leftOnClick(OnClickListener listener){
        ivCustomViewTitlebarLeft.setOnClickListener(listener);
    }

    //右边的控件点击事件
    public void rightOnClick(OnClickListener listener){
        ivCustomViewTitlebarRight.setOnClickListener(listener);
        tvCustomViewTitlebarRight.setOnClickListener(listener);
    }

}
