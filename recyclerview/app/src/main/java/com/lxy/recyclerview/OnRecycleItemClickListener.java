package com.lxy.recyclerview;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liuxinyu on 2016/5/21.
 */
public abstract class OnRecycleItemClickListener implements RecyclerView.OnItemTouchListener {
    private RecyclerView mRecyclerView;
    private GestureDetectorCompat mGesture;

    public OnRecycleItemClickListener(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mGesture = new GestureDetectorCompat(recyclerView.getContext(), new ItemGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGesture.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGesture.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    class ItemGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View item = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (item != null) {
                RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(item);
                onItemClick(childViewHolder);
            }

            return true;
        }

        //长按
        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            View item = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (item != null) {
                RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(item);
                onItemLongClick(childViewHolder);
            }

        }
    }
    //条目点击回调接口
    public abstract void onItemClick(RecyclerView.ViewHolder vh);
    //条目长按事件
    public abstract void onItemLongClick(RecyclerView.ViewHolder vh);

}
