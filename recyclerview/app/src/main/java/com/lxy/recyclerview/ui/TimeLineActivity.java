package com.lxy.recyclerview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lxy.recyclerview.R;
import com.lxy.recyclerview.adapter.TimeLineAdapter;
import com.lxy.recyclerview.adapter.TimeLineDecoration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author lxy
 */
public class TimeLineActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<HashMap<String, Object>> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);

        TimeLineAdapter adapter = new TimeLineAdapter(mList);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addItemDecoration(new TimeLineDecoration(this));

        mRecyclerView.setAdapter(adapter);
    }

    private void initData() {
        mList = new ArrayList<>();

        HashMap<String, Object> map1 = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        HashMap<String, Object> map3 = new HashMap<>();
        HashMap<String, Object> map4 = new HashMap<>();
        HashMap<String, Object> map5 = new HashMap<>();
        HashMap<String, Object> map6 = new HashMap<>();

        map1.put("title", "美国谷歌公司已发出");
        map1.put("text", "发件人:谷歌 CEO Sundar Pichai");
        mList.add(map1);

        map2.put("title", "国际顺丰已收入");
        map2.put("text", "等待中转");
        mList.add(map2);

        map3.put("title", "国际顺丰转件中");
        map3.put("text", "下一站中国");
        mList.add(map3);

        map4.put("title", "中国顺丰已收入");
        map4.put("text", "下一站广州华南理工大学");
        mList.add(map4);

        map5.put("title", "中国顺丰派件中");
        map5.put("text", "等待派件");
        mList.add(map5);

        map6.put("title", "华南理工大学已签收");
        map6.put("text", "收件人:Carson");
        mList.add(map6);

    }
}
