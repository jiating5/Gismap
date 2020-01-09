package com.jt.chatmodule.view.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.jt.chatmodule.R;

/**
 * @author 贾婷
 * @date 2020/1/2.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class SiderBar extends View {
    // 触摸事件

    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    // 26个字母

    public static String[] b = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",

            "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#" };

    private int choose = -1;// 选中

    private Paint paint = new Paint();



    private TextView mTextDialog;

    private float singleHeight;

    public static int dialogColor[] = { R.mipmap.gesture_node_pressed, R.mipmap.gesture_node_wrong,

            R.mipmap.gesture_node_pressed, R.mipmap.gesture_node_wrong, R.mipmap.gesture_node_pressed };

    public SiderBar(Context context) {
        super(context);
    }

    public SiderBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SiderBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setTextView(TextView mTextDialog) {

        this.mTextDialog = mTextDialog;

    }

    /**

     * 重写这个方法

     */

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        // 获取焦点改变背景颜色.

        int height = getHeight();// 获取对应高度

        int width = getWidth(); // 获取对应宽度



        // 获取每一个字母的高度

        singleHeight = (height * 1f) / b.length;

        singleHeight = (height * 1f - singleHeight / 2) / b.length;

        for (int i = 0; i < b.length; i++) {

            paint.setColor(Color.rgb(23, 122, 216));

            // paint.setColor(Color.WHITE);

            paint.setTypeface(Typeface.DEFAULT_BOLD);

            paint.setAntiAlias(true);

            paint.setTextSize(25);

            // 选中的状态

            if (i == choose) {

                paint.setColor(Color.parseColor("#c60000"));

                paint.setFakeBoldText(true);

            }

            // x坐标等于中间-字符串宽度的一半.

            float xPos = width / 2 - paint.measureText(b[i]) / 2;

            float yPos = singleHeight * i + singleHeight;

            canvas.drawText(b[i], xPos, yPos, paint);

            paint.reset();// 重置画笔

        }



    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override

    public boolean dispatchTouchEvent(MotionEvent event) {

        final int action = event.getAction();

        final float y = event.getY();// 点击y坐标

        final int oldChoose = choose;

        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
        final int c = (int) (y / getHeight() * b.length);



        switch (action) {

            case MotionEvent.ACTION_UP:

                setBackgroundDrawable(new ColorDrawable(0x00000000));

                choose = -1;

                invalidate();

                if (mTextDialog != null) {

                    mTextDialog.setVisibility(View.INVISIBLE);

                }

                break;

            // 除开松开事件的任何触摸事件

            default:

//                setBackgroundResource(R.mipmap.gesture_node_pressed);

                if (oldChoose != c) {

                    if (c >= 0 && c < b.length) {

                        if (listener != null) {
                            listener.onTouchingLetterChanged(b[c]);
                        }

                        if (mTextDialog != null) {

                            mTextDialog.setText(b[c]);

                            mTextDialog.setVisibility(View.VISIBLE);

                            // 动态改变文字dialog的位置

                            int right = mTextDialog.getLeft();

                            mTextDialog.setX(right / 2 * 3);

                            if(c>24){

                                mTextDialog.setY(singleHeight * 24);

                            }else{

                                mTextDialog.setY(singleHeight * c);

                            }

//                            mTextDialog.setBackground(getContext().getResources().getDrawable(dialogColor[c / 6]));

                        }



                        choose = c;

                        invalidate();

                    }
                }
                break;
        }
        return true;

    }

    /**

     * 向外公开的方法

     *

     * @param onTouchingLetterChangedListener

     */

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {

        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    /**

     * 接口

     *

     * @author coder

     *

     */

    public interface OnTouchingLetterChangedListener {

        public void onTouchingLetterChanged(String s);

    }
}
