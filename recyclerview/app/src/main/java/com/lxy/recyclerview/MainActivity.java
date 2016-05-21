package com.lxy.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mList;
    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvents();
    }

    private void initEvents() {

        mRecyclerview.addOnItemTouchListener(new OnRecycleItemClickListener(mRecyclerview) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                //position
                int position = vh.getAdapterPosition();
                //Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
                //
                TextView tv = (TextView) vh.itemView.findViewById(R.id.tv_title);
                String s = tv.getText().toString();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initView() {
        mRecyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new RecyclerAdapter(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);

    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 'A'; i <= 'Z'; i++){
            mList.add((char)i+"");
        }

        mAdapter.addItems(mList);
    }

}
