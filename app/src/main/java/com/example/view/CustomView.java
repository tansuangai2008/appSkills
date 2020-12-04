package com.example.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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

    private void initPaint(){
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.YELLOW);
        canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2,paint);
//        canvas.save();
        Path path = new Path();
        path.moveTo(0, getHeight()/2+10);
        path.lineTo(getWidth(), getHeight()/2+10);
        path.lineTo(getWidth(), getHeight()/2+20);
        path.lineTo(0,getHeight()/2+20);
        path.close();
        paint.setColor(getResources().getColor(R.color.color_5680FF));
        canvas.drawPath(path,paint);

//        canvas.drawText();
//        canvas.restore();





    }
}
