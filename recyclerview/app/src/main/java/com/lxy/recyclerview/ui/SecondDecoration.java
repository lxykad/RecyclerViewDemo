package com.lxy.recyclerview.ui;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.lxy.recyclerview.adapter.SecondAdapter;

/**
 * @author lxy
 *         http://www.jcodecraeer.com/a/anzhuokaifa/2017/0615/8079.html
 */
public class SecondDecoration extends RecyclerView.ItemDecoration {

    private Rect mPinnedHeaderRect = null;
    private int mPinnedHeaderPosition = -1;

    private int mDividerWidth;
    private Paint mPaint;

    public SecondDecoration(int width) {
        this.mDividerWidth = width;
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#30ff0000"));
    }

    /**
     * 设置padding
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // item的position
        int position = parent.getChildAdapterPosition(view);
        if (position != 0) {
            // paddingTop
            outRect.top = mDividerWidth;
        }
    }

    /**
     * 绘制分割线
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getLeft();
        final int right = parent.getRight();
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            final int bottom = childView.getTop();
            final int top = bottom - mDividerWidth;
           // c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    /**
     * 绘制悬浮条
     * onDrawOver绘制的内容显示在最上层
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        final int itemCount = state.getItemCount();
        final int childCount = parent.getChildCount();
        final int left = parent.getLeft() + parent.getPaddingLeft();
        final int right = parent.getRight() - parent.getPaddingRight();

        String preGroupName;      //标记上一个item对应的Group
        String currentGroupName = null;       //当前item对应的Group

        if (parent.getAdapter() instanceof SecondAdapter && childCount > 0) {
            SecondAdapter adapter = (SecondAdapter) parent.getAdapter();
            View firstView = parent.getChildAt(0);
            // 当前可见的第一个item的position
            int firstVisiablePosition = parent.getChildAdapterPosition(firstView);

            if (adapter.isPinnedPosition(firstVisiablePosition)) {
                // 需要悬浮
                RecyclerView.ViewHolder pinnedHeaderViewHolder = adapter.onCreateViewHolder(parent,
                        adapter.getItemViewType(firstVisiablePosition));
                adapter.onBindViewHolder((SecondAdapter.SecondHolder) pinnedHeaderViewHolder, firstVisiablePosition);
                //要固定的view
                View itemView = pinnedHeaderViewHolder.itemView;
                ensurePinnedHeaderViewLayout(itemView, parent);

                int sectionPinOffset = 0;
                for (int index = 0; index < parent.getChildCount(); index++) {
                    if (adapter.isPinnedPosition(parent.getChildAdapterPosition(parent.getChildAt(index)))) {
                        View sectionView = parent.getChildAt(index);
                        int sectionTop = sectionView.getTop();
                        int pinViewHeight = itemView.getHeight();
                        if (sectionTop < pinViewHeight && sectionTop > 0) {
                            sectionPinOffset = sectionTop - pinViewHeight;
                        }
                    }
                }
                int saveCount = c.save();
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
                if (layoutParams == null) {
                    throw new NullPointerException("PinnedHeaderItemDecoration");
                }
                c.translate(layoutParams.leftMargin, sectionPinOffset);
                c.clipRect(0, 0, parent.getWidth(), itemView.getMeasuredHeight());
                itemView.draw(c);
                c.restoreToCount(saveCount);

                if (mPinnedHeaderRect == null) {
                    mPinnedHeaderRect = new Rect();
                }
                mPinnedHeaderRect.set(0, 0, parent.getWidth(), itemView.getMeasuredHeight() + sectionPinOffset);


            } else {
                // 不需要悬浮
                mPinnedHeaderRect = null;
            }

        }


    }

    /**
     * 根据第一个可见的adapter的位置去获取临近的一个要固定的position的位置
     *
     * @param adapterFirstVisible 第一个可见的adapter的位置
     * @return -1：未找到 >=0 找到位置
     */
    private int getPinnedHeaderViewPosition(int adapterFirstVisible, SecondAdapter adapter) {
        for (int index = adapterFirstVisible; index >= 0; index--) {
            if (adapter.isPinnedPosition(index)) {
                return index;
            }
        }
        return -1;
    }

    private void ensurePinnedHeaderViewLayout(View pinView, RecyclerView recyclerView) {
        if (pinView.isLayoutRequested()) {
            /**
             * 用的是RecyclerView的宽度测量，和RecyclerView的宽度一样
             */
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) pinView.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("PinnedHeaderItemDecoration");
            }
            int widthSpec = View.MeasureSpec.makeMeasureSpec(
                    recyclerView.getMeasuredWidth() - layoutParams.leftMargin - layoutParams.rightMargin, View.MeasureSpec.EXACTLY);

            int heightSpec;
            if (layoutParams.height > 0) {
                heightSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY);
            } else {
                heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            }
            pinView.measure(widthSpec, heightSpec);
            pinView.layout(0, 0, pinView.getMeasuredWidth(), pinView.getMeasuredHeight());
        }
    }

    /**
     * 获取type的name，由activity实现
     * 根据position 获取 条目对象，返回name
     */
    public interface TypeListener {
        String getTypeName(int position);
    }
}
