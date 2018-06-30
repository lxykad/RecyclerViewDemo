package com.lxy.recyclerview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


/**
 * @author lxy
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mList;
    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapter mAdapter;
    //
    private ItemTouchHelper mHelper;

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
            @Override//条目点击
            public void onItemClick(RecyclerView.ViewHolder vh) {
                //position
                int position = vh.getAdapterPosition();
                TextView tv = (TextView) vh.itemView.findViewById(R.id.tv_title);
                String s = tv.getText().toString();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

            }

            @Override//条目长按
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                Toast.makeText(MainActivity.this,"长按",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {

        mRecyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);

        mAdapter = new RecyclerAdapter(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setAdapter(mAdapter);

        //条目拖拽
        mHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlag;
                int swipFlag;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    //拖拽标志、方向
                    dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    //滑动标志，0表示不做滑动处理。如果该值非0，则拖拽时会回调onmove方法，
                    swipFlag = 0;
                } else {
                    dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    swipFlag = 0;
                }
                return makeMovementFlags(dragFlag, swipFlag);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //开始的位置
                int fromPosition = viewHolder.getAdapterPosition();
                //目标位置
                int toPosition = target.getAdapterPosition();
                //拖拽移动后排序
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(mList, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(mList, i, i - 1);
                    }
                }
                mAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }


            @Override//长按拖拽的时候调用 设置背景色
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#69c6c1"));
                }
            }

            @Override//拖拽完成，松开手指的时候调用
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
            }

            @Override//设置条目是否可以拖拽
            public boolean isLongPressDragEnabled() {
                /**
                 * true 可拖拽，false不可以
                 *根据项目需求，可以这里返回false设置不可以拖拽，
                 * 然后在长按回调里通过方法 itemTouchHelper.startDrag(vh);
                 * 设置指定条目可以拖拽或者不可以拖拽
                 */
                return true;
            }
        });
        mHelper.attachToRecyclerView(mRecyclerview);

    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 'A'; i <= 'Z'; i++) {
            mList.add((char) i + "");
        }

        mAdapter.addItems(mList);
    }

}
