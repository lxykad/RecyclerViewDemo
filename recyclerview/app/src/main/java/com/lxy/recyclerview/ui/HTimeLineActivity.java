package com.lxy.recyclerview.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lxy.recyclerview.R;
import com.lxy.recyclerview.adapter.HTimeLineAdapter;
import com.lxy.recyclerview.entity.HTimeLineBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxy
 */
public class HTimeLineActivity extends AppCompatActivity {

    private List<HTimeLineBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htime_line);

        initData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        HTimeLineAdapter adapter = new HTimeLineAdapter(mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HTimeLineBean bean = new HTimeLineBean();
            bean.title = "titletitletitletitletitletitletitletitletitletitletitle: " + i;
            bean.content = "contentcontentcontentcontentcontentcontentcontentcontent: " + i;
            mList.add(bean);
        }
    }
}
