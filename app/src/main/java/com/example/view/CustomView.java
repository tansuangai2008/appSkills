package com.example.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

/**
 * author : ly
 * date : 2020/9/8 16:35
 * description : 自定义View
 */
public class CustomView extends android.view.View {

    private Paint paint = null;

    public CustomView(Context context) {
        super(context);
        initPaint();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.GREEN);
        canvas.drawLine(getResources().getDimensionPixelOffset(R.dimen.margin_16dp), getHeight() / 2, getWidth() - getResources().getDimensionPixelOffset(R.dimen.margin_16dp), getHeight() / 2, paint);
//        canvas.save();
        Path path = new Path();
        path.moveTo(0, getHeight() / 2 + 10);
        path.lineTo(getWidth(), getHeight() / 2 + 10);
        path.lineTo(getWidth(), getHeight() / 2 + 20);
        path.lineTo(0, getHeight() / 2 + 20);
        path.close();
        paint.setColor(getResources().getColor(R.color.color_5680FF));
        canvas.drawPath(path, paint);
        //绘制弧线区域
        RectF rectF = new RectF(0, 0, 100, 100);
        canvas.drawArc(rectF, 0, 90, true, paint);

        int width = 200;
        int radius = 8;
        int height = 100;

        Path path1 = new Path();
        path1.moveTo(radius, 0);
        path1.lineTo(width-radius, 0);
        path1.quadTo(width, 0, width, radius);
        path1.lineTo(width, height - radius);
        path1.quadTo( width, height, width-radius,height);
        path1.lineTo(radius, height);
        path1.quadTo(0,height, 0,height-radius);
        path1.lineTo(0,radius);
        path1.quadTo(0,0, radius,0);
        path1.close();
        paint.setColor(getResources().getColor(R.color.color_79472C));
        paint.setAntiAlias(true);
        canvas.translate(400,300);
        canvas.drawPath(path1, paint);

        paint.setColor(getResources().getColor(R.color.color_334F8D));
        canvas.translate(0,100);
        canvas.drawCircle(60, 180, 100, paint);

//        canvas.drawText();
//        canvas.restore();


    }
}
