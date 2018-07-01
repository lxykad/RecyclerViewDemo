package com.lxy.recyclerview.ui;


import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author lxy
 */
public class SecondDecoration extends RecyclerView.ItemDecoration {

    private int mDividerWidth;

    public SecondDecoration(int width) {
        this.mDividerWidth = width;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // bottomPadding
        outRect.bottom = mDividerWidth;
    }
}
