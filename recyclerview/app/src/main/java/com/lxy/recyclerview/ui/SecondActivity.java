package com.lxy.recyclerview.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.lxy.recyclerview.OnRecycleItemClickListener;
import com.lxy.recyclerview.R;
import com.lxy.recyclerview.adapter.SecondAdapter;
import com.lxy.recyclerview.entity.SecondBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxy
 */
public class SecondActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SecondAdapter mAdapter;
    private List<SecondBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initData();
        initEvents();
    }


    private void initData() {
        for (int i = 0; i < 20; i++) {
            SecondBean bean = new SecondBean();
            bean.name = "name: " + i;
            bean.type = i;
            mList.add(bean);
        }
    }


    private void initEvents() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new SecondAdapter(this, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnRecycleItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition();
                SecondBean bean = mList.get(position);
                Toast.makeText(mRecyclerView.getContext(), bean.name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {

            }
        });
    }
}
