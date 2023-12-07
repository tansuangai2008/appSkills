package com.example.vote;

import android.content.Context;
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

/**
 * author : LIU YANG
 * date   : 2023/12/6
 * desc   : 投票View
 */
public class VoteView extends View {

    private final static String TAG = "VoteView";

    private int mTotalWidth;
    private RectF mleftRect;

    public VoteView(Context context) {
        super(context);
    }

    public VoteView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VoteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        mTotalWidth = canvas.getWidth();

        // 设置扇形所在矩形的位置和大小
        float left = 16; // 左边距
        float top = 10;  // 上边距
        float right = 50;  // 右边距
        float bottom = 50;  // 下边距

        // 设置扇形的起始角度和扫描角度
        float startAngle = 90;  // 起始角度
        float sweepAngle = 180;  // 扫描角度
        RectF leftRect = new RectF(left, top, right, bottom);
        leftRect.set(left, top, right, bottom);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        // 绘制扇形
        canvas.drawArc(leftRect, startAngle, sweepAngle, true, paint);

        //绘制矩形
        mleftRect = new RectF(leftRect.centerX(), top, 160, bottom);
        Paint mOuterPaint = new Paint();
        mOuterPaint.setColor(Color.GREEN);
        mOuterPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(mleftRect, mOuterPaint);

        //画左边的斜角
        Path path = new Path();
        path.moveTo(180, top);  // 移动到右上角
        path.lineTo(160, bottom);  // 连线到右下角
        path.lineTo(160, top);  // 连线到右上角加上斜角的高度
        path.close();  // 封闭路径
        // 绘制斜角
        canvas.drawPath(path, mOuterPaint);

        //右边的斜角靠左的的
        Paint mLeftPaint = new Paint();
        mLeftPaint.setColor(Color.RED);

        //绘制最右边的圆弧
        float startRightAngle = 270;  // 起始角度
        float sweepRightAngle = 180;  // 扫描角度
        RectF leftRightRect = new RectF(getWidth() - 16 - 34, top, getWidth() - 16, bottom);
        // 绘制扇形
        canvas.drawArc(leftRightRect, startRightAngle, sweepRightAngle, true, mLeftPaint);
        float ccTest = (int) (leftRightRect.centerX() - (getWidth() - 16));
        Log.e(TAG, "获取 =ccTest=" + ccTest);
        //绘制最右边的矩形
        RectF mRightRect = new RectF(leftRightRect.centerX() - 200, top, leftRightRect.centerX(), bottom);
        canvas.drawRect(mRightRect, mLeftPaint);

        //测试计算公式是否准确
        RectF mTestRect = new RectF(leftRightRect.centerX(), top, (getWidth() - 16), bottom);
        Paint mTestPaint = new Paint();
        mTestPaint.setStyle(Paint.Style.STROKE);
        mTestPaint.setColor(Color.GRAY);
        canvas.drawRect(mTestRect, mTestPaint);

        Path pathRight = new Path();
        pathRight.moveTo(mRightRect.left, top);  // 移动到右上角
        pathRight.lineTo(mRightRect.left - 20, bottom);  // 连线到右下角
        pathRight.lineTo(mRightRect.left, bottom);  // 连线到右上角加上斜角的高度
        pathRight.close();  // 封闭路径
        // 绘制斜角
        canvas.drawPath(pathRight, mLeftPaint);

        //计算获取的条件
        //获取整个屏幕的宽、左右两块的圆角区域的固定宽度、左右两块斜角区域的固定宽度

    }
}
