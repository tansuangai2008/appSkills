package com.example.recyclehelper.itemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.recyclehelper.ItemMoveListener;
import com.example.recyclehelper.adapter.RecyclerViewAdapter;
import com.example.recyclehelper.model.ItemEntity;

import java.util.List;

/**
 * author : ly
 * date : 2020/11/18 14:57
 * description : 时间轴分割线
 */
public class TimeLineItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = TimeLineItemDecoration.class.getSimpleName();

    //画布
    private Paint mPaint;

    //outRect的四个参数————itemView的间距
    private float mOffsetLeft;
    private float mOffsetRight;
    private float mOffsetTop;
    private float mOffsetBottom;

    //时间轴结点的半径
    private float mNodeRadius;
    private Context context;
    private ItemMoveListener itemMoveListener;

    public TimeLineItemDecoration(Context context, ItemMoveListener itemMoveListener) {
        this.context = context;
        this.itemMoveListener = itemMoveListener;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);  //抗锯齿
//        mPaint.setColor(Color.RED);
        //outRect
        mOffsetLeft = context.getResources().getDimension(R.dimen.margin_24dp);
        mOffsetRight = context.getResources().getDimension(R.dimen.margin_6dp);
        mOffsetTop = context.getResources().getDimension(R.dimen.margin_0dp);
        mOffsetBottom = context.getResources().getDimension(R.dimen.margin_0dp);
        //圆圈
        mNodeRadius = context.getResources().getDimension(R.dimen.margin_3dp);

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        RecyclerViewAdapter recyclerViewAdapter = (RecyclerViewAdapter) parent.getAdapter();
//        ItemEntity itemEntity = recyclerViewAdapter.getItem(2);
//        Log.e(TAG, "当前打印的对象=" + JSON.toJSONString(itemEntity));
//        for (int i = 0; i < childCount; i++) {
//            View view = parent.getChildAt(i);
//
//            float dividerTop = view.getTop() - mOffsetTop;
//            float dividerLeft = parent.getPaddingLeft();
//            float dividerBottom = view.getBottom() + mOffsetBottom;
//
//            float centerX = dividerLeft + mOffsetLeft / 2;
//            float centerY = dividerTop + (dividerBottom - dividerTop) / 2;
//
//            float upLineTopX = centerX;
//            float upLineTopY = dividerTop;
//            float upLineBottomX = centerX;
//            float upLineBottomY = centerY - mNodeRadius;
//            ItemEntity item = recyclerViewAdapter.getItem(i);
////            if (item.getText().equals("设计")) {
////                ConstraintLayout constraintLayout = view.findViewById(R.id.cl_root);
//////                TextView textView = new TextView(context);
//////                textView.setText("仅作测试");
//////                textView.setTextColor(context.getResources().getColor(R.color.red));
//////                constraintLayout.addView(textView);
////                View diyView = LayoutInflater.from(context).inflate(R.layout.layout_yellow_tips, null);
////                constraintLayout.addView(diyView);
////                continue;
////            }
//            //绘制上半部轴线
//            c.drawLine(upLineTopX, upLineTopY, upLineBottomX, upLineBottomY, mPaint);
//
//            float downLineTopX = centerX;
//            float downLineTopY = centerY + mNodeRadius;
//            float downLineBottomX = centerX;
//            float downLineBottomY = dividerBottom;
//
//            //绘制上半部轴线
//            c.drawLine(downLineTopX, downLineTopY, downLineBottomX, downLineBottomY, mPaint);
//
//
//            //绘制时间轴结点
//            // 结点（实心）
////            c.drawCircle(centerX,centerY,mNodeRadius,mPaint);
//            // 结点（空心）
//            mPaint.setStyle(Paint.Style.STROKE);
//            c.drawCircle(centerX, centerY, mNodeRadius, mPaint);
//            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
////            }
//
//        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        RecyclerViewAdapter recyclerViewAdapter = (RecyclerViewAdapter) parent.getAdapter();
//        View diyView = LayoutInflater.from(context).inflate(R.layout.layout_yellow_tips, null);
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int pos = parent.getChildAdapterPosition(view);
            ItemEntity item = recyclerViewAdapter.getItem(pos);
            RecyclerView.ViewHolder viewHolder = parent.findViewHolderForAdapterPosition(pos);
            RecyclerViewAdapter.ItemViewHolder tempView = recyclerViewAdapter.onCreateViewHolder(parent, 0);

            float dividerTop = view.getTop() - mOffsetTop;
            float dividerLeft = parent.getPaddingLeft();
            float dividerBottom = view.getBottom() + mOffsetBottom;

            float centerX = dividerLeft + mOffsetLeft / 2;
            float centerY = dividerTop + (dividerBottom - dividerTop) / 2;

            float upLineTopX = centerX;
            float upLineTopY = dividerTop;
            float upLineBottomX = centerX;
            float upLineBottomY = centerY - mNodeRadius;
            if (item.getText().equals("计算机")) {
                TextView textView = new TextView(context);
                textView.setText("仅作测试");
                textView.setTextColor(context.getResources().getColor(R.color.red));
                RelativeLayout relativeLayout = viewHolder.itemView.findViewById(R.id.cl_root);
//                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(context.getResources().getDimensionPixelOffset(R.dimen.margin_100dp),context.getResources().getDimensionPixelOffset(R.dimen.margin_100dp));
//                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//                textView.setLayoutParams(layoutParams);
//                relativeLayout.addView(textView,layoutParams);
//                c.save();
                int saveCount = c.save();
                View diyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_yellow_tips, null, true);
                Log.e(TAG, "view.getMeasuredWidth()=" + view.getMeasuredWidth());
                int tempWidth = View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), View.MeasureSpec.UNSPECIFIED);
                int tempHeight = View.MeasureSpec.makeMeasureSpec(context.getResources().getDimensionPixelOffset(R.dimen.margin_40dp), View.MeasureSpec.UNSPECIFIED);
                Log.e(TAG, "tempWidth()=" + tempWidth);
                diyView.measure(tempWidth, tempHeight);
                diyView.layout(0, 0, tempWidth, tempHeight);
//                c.translate(layoutParams.leftMargin, 1200);
//                c.clipRect(0, 0, 2000, 1000);
                c.translate(0, view.getTop());
                itemMoveListener.moveItemTranslate(view.getTop());
//                measureHoverView(parent, diyView);
//                diyView.draw(c);
                c.restoreToCount(saveCount);
//                c.restore();
                continue;
            } else {
                RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    int lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                    List<ItemEntity> list = recyclerViewAdapter.getAll();
                    boolean hideFlag = false;
                    for (int k = firstItemPosition; k <= lastItemPosition; k++) {
                        ItemEntity itemEntity = list.get(k);
                        if ("计算机".equals(itemEntity.getText())) {
                            hideFlag = true;
                            break;
                        }
                    }
                    if (!hideFlag) {
                        itemMoveListener.setVisible(false);
                    }
                }
            }


        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //设置outRect的四个参数
//        outRect.left = (int) mOffsetLeft;
//        outRect.right = (int) mOffsetRight;
//        outRect.top = (int) mOffsetTop;
//        outRect.bottom = (int) mOffsetBottom;
    }

    private void measureHoverView(RecyclerView parent, View hoverView) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) hoverView.getLayoutParams();
        int widthSize;
        int widthMode;
        if (params.width == -1) {
            widthSize = parent.getMeasuredWidth();
            widthMode = View.MeasureSpec.EXACTLY;
        } else {
            widthSize = parent.getMeasuredWidth();
            widthMode = View.MeasureSpec.AT_MOST;
        }
        int heightSize;
        int heightMode;
        if (params.height == -1) {
            heightSize = parent.getMeasuredHeight();
            heightMode = View.MeasureSpec.EXACTLY;
        } else {
            heightSize = parent.getMeasuredHeight();
            heightMode = View.MeasureSpec.AT_MOST;
        }

        View.MeasureSpec.makeMeasureSpec(widthSize, widthMode);
        View.MeasureSpec.makeMeasureSpec(heightSize, heightMode);
        hoverView.layout(0, 0, hoverView.getMeasuredWidth(), hoverView.getMeasuredHeight());


    }

}
