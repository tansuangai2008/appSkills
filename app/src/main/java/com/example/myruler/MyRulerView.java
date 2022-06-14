package com.example.myruler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

/**
 * author : liuyang
 * date   : 2022/6/13
 * desc   : 我的自定义尺子
 */
public class MyRulerView extends View {

    private static final String TAG = "MyRulerView";

    //刻度尺高度
    private static final int dividing_rule_height = 70;
    //左右距离
    private static final int dividing_rule_margin_left_right = 10;

    //第一条线距离边框距离
    private static final int first_line_margin = 5;

    //打算绘制的厘米数
    private static final int default_count = 10;

    private int mTotalWidth;
    private int mDividRuleHeight;
    private int mDividRuleLeftMargin;
    private int mFirstLineMargin;
    private Rect mOutRect;
    private int mLineInterval;

    private Paint mOuterPaint;
    private Paint mLinePaint;
    private Paint mTextPaint;

    private int mMaxLineTop;
    private int mMiddleLineTop;
    private int mMinLineTop;

    public MyRulerView(Context context) {
        super(context);
    }

    public MyRulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyRulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initData(canvas);
        drawOuter(canvas);
        drawLines(canvas);
        drawNumbers(canvas);
    }

    private void initData(Canvas canvas) {
        mTotalWidth = canvas.getWidth();
        mDividRuleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividing_rule_height, getResources().getDisplayMetrics());
        mDividRuleLeftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividing_rule_margin_left_right, getResources().getDisplayMetrics());
        mFirstLineMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, first_line_margin, getResources().getDisplayMetrics());
        mMaxLineTop = mDividRuleHeight / 2;
        mMiddleLineTop = mDividRuleHeight / 2 / 4 * 3;
        mMinLineTop = mDividRuleHeight / 2 / 2;

        mOutRect = new Rect(mDividRuleLeftMargin, 30, mTotalWidth - mDividRuleLeftMargin, mDividRuleHeight);
        mLineInterval = (mTotalWidth - 2 * mDividRuleLeftMargin - 2 * mFirstLineMargin) / (default_count * 10 - 1);

        mOuterPaint = new Paint();
        mLinePaint = new Paint();
        mTextPaint = new Paint();
        mTextPaint.setColor(getResources().getColor(R.color.color_000000));
        mTextPaint.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void drawOuter(Canvas canvas) {
        mOuterPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mOutRect, mOuterPaint);
    }

    private void drawLines(Canvas canvas) {
        canvas.save();
        canvas.translate(mDividRuleLeftMargin + mFirstLineMargin, mDividRuleHeight);
        int top = mMaxLineTop;
        for (int i = 0; i <= default_count * 10; i++) {
            if (i % 10 == 0) {
                top = mMaxLineTop;
            } else if (i % 5 == 0) {
                top = mMiddleLineTop;
            } else {
                top = mMinLineTop;
            }
            canvas.drawLine(0, 0, 0, -top, mLinePaint);
            canvas.translate(mLineInterval, 0);
        }
        canvas.restore();
    }

    private void drawNumbers(Canvas canvas) {
        canvas.save();
        canvas.translate(mDividRuleLeftMargin + mFirstLineMargin, mDividRuleHeight);
        int top = mMaxLineTop;
        int num = 0;
        for (int i = 0; i <= default_count * 10; i++) {
            if (i % 10 == 0) {
                top = mMaxLineTop;
                num = i / 10;
                canvas.drawText(num + "", 0, 40, mTextPaint);
            } else if (i % 5 == 0) {
                top = mMiddleLineTop;
            } else {
                top = mMinLineTop;
            }
            canvas.translate(mLineInterval, 0);
        }
        canvas.restore();
    }
}
