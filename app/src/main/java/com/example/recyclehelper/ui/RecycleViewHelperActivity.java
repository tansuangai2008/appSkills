package com.example.recyclehelper.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.recyclehelper.MyItemTouchHelperCallback;
import com.example.recyclehelper.OnStartDragListener;
import com.example.recyclehelper.adapter.RecyclerViewAdapter;
import com.example.recyclehelper.itemdecoration.TimeLineItemDecoration;
import com.example.recyclehelper.model.ItemEntity;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewHelperActivity extends AppCompatActivity implements OnStartDragListener {

    private List<ItemEntity> mList;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recycle_helper);
        initData();
        initViews();
    }

    private void initData() {
        mList = new ArrayList<>();
        String [] strings = {"Android","后端","前端","iOS","人工智能","产品","工具资源","阅读","小学生天地","中学生天地","大学生天地","语文天地","特别关注","计算机","设计"};
        for (String string : strings) {
            ItemEntity item = new ItemEntity();
            item.setChecked(false);
            item.setText(string);
            mList.add(item);
        }
    }

    private void initViews() {
        RecyclerView recyclerView = findViewById(R.id.main_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mList,this);
        adapter.setOnClickSwitchListener(new RecyclerViewAdapter.OnClickSwitchListener() {
            @Override
            public void onClick(int position, boolean isChecked) {
                //实际开发中做发送请求等等一些处理
            }
        });
        recyclerView.setAdapter(adapter);
        mItemTouchHelper = new ItemTouchHelper(new MyItemTouchHelperCallback(adapter));
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new TimeLineItemDecoration(this));
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        //通知ItemTouchHelper开始拖拽
        mItemTouchHelper.startDrag(viewHolder);
    }
}
