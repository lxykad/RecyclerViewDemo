package com.lxy.recyclerview.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lxy.recyclerview.R;
import com.lxy.recyclerview.adapter.ScrollAdapter;
import com.lxy.recyclerview.entity.HTimeLineBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxy
 */
public class ScrollRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<HTimeLineBean> mList;
    private ScrollAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_recycler_view);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ScrollAdapter(mList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            HTimeLineBean bean = new HTimeLineBean();
            if (i == 0) {
                bean.title = "title: " + i;
                bean.content = "content " + i;
            } else {
                bean.title = "titletitle: " + i;
                bean.content = "contentcontentcontentcontentcontentcontentcontentcontent: " + i;
            }
            mList.add(bean);
        }
    }
}
