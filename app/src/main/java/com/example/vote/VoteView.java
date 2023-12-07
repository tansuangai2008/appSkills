package com.example.vote;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

/**
 * author : LIU YANG
 * date   : 2023/12/6
 * desc   : 投票View
 */
public class VoteView extends View {

    private final static String TAG = "VoteView";
    //计算获取的条件
    //获取整个屏幕的宽、左右两块的圆角区域的固定宽度、左右两块斜角区域的固定宽度
    private float density;
    private RectF mleftRect;
    private float roundWith = 8;
    private float angleWith = 16;
    private float greenPercent = 0.1f;

    public VoteView(Context context) {
        this(context, null);

    }

    public VoteView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VoteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        density = context.getResources().getDisplayMetrics().density;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.VoteView, defStyleAttr, 0);
        angleWith = a.getDimension(R.styleable.VoteView_angleWith, angleWith * density);
        a.recycle();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        roundWith = getHeight();

        // 设置扇形所在矩形的位置和大小
        float left = 0; // 左边距
        float top = 0;  // 上边距
        float right = roundWith;  // 右边距
        float bottom = getHeight();  // 下边距

        // 设置扇形的起始角度和扫描角度
        float startAngle = 90;  // 起始角度
        float sweepAngle = 180;  // 扫描角度
        RectF leftRect = new RectF(left, top, right, bottom);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        // 绘制扇形
        canvas.drawArc(leftRect, startAngle, sweepAngle, true, paint);

        //计算百分比 算出剩余可参与计算的空间(也就是宽度)
        float leftContentWidth = getWidth() - angleWith - roundWith;

        float leftGreen = (float) (leftContentWidth * greenPercent);
        float rightRed = (float) leftContentWidth - leftGreen;
        Log.e(TAG, "leftGreen= " + leftGreen + "=rightRed=" + rightRed);
        if (leftGreen == 0) {
            leftGreen = leftRect.centerX();
        }
        //绘制矩形
        mleftRect = new RectF(leftRect.centerX(), top, leftGreen, bottom);
        Paint mOuterPaint = new Paint();
        mOuterPaint.setColor(Color.GREEN);
        mOuterPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(mleftRect, mOuterPaint);

        //画左边的斜角
        Path path = new Path();
        path.moveTo(mleftRect.right + angleWith, top); // 移动到右上角
        path.lineTo(mleftRect.right, bottom);  // 连线到右下角
        path.lineTo(mleftRect.right, top);  // 连线到右上角加上斜角的高度
        path.close();  // 封闭路径
        // 绘制斜角
        canvas.drawPath(path, mOuterPaint);

        //右边的斜角靠左的的
        Paint mLeftPaint = new Paint();
        mLeftPaint.setColor(Color.RED);

        //绘制最右边的圆弧
        float startRightAngle = 270;  // 起始角度
        float sweepRightAngle = 180;  // 扫描角度
        RectF leftRightRect = new RectF(getWidth() - roundWith, top, getWidth(), bottom);
        // 绘制扇形
        canvas.drawArc(leftRightRect, startRightAngle, sweepRightAngle, true, mLeftPaint);
        float ccTest = (int) (leftRightRect.centerX() - (getWidth()));
        Log.e(TAG, "获取 =ccTest=" + ccTest);
        //绘制最右边的矩形
        RectF mRightRect = new RectF(leftRightRect.centerX() - rightRed, top, leftRightRect.centerX(), bottom);
        canvas.drawRect(mRightRect, mLeftPaint);

        //测试计算公式是否准确 !!!!!!
//        RectF mTestRect = new RectF(leftRightRect.centerX(), top, getWidth(), bottom);
//        Paint mTestPaint = new Paint();
//        mTestPaint.setStyle(Paint.Style.STROKE);
//        mTestPaint.setColor(Color.GRAY);
//        canvas.drawRect(mTestRect, mTestPaint);

        Path pathRight = new Path();
        pathRight.moveTo(mRightRect.left, top);  // 移动到右上角
        pathRight.lineTo(mRightRect.left - angleWith, bottom);  // 连线到右下角
        pathRight.lineTo(mRightRect.left, bottom);  // 连线到右上角加上斜角的高度
        pathRight.close();  // 封闭路径
        // 绘制斜角
        canvas.drawPath(pathRight, mLeftPaint);
        //计算获取的条件
        //获取整个屏幕的宽、左右两块的圆角区域的固定宽度、左右两块斜角区域的固定宽度

    }

    private void setGreenPercent(float percent) {
        this.greenPercent = percent;
        invalidate();
    }
}
