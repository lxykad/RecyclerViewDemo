package com.lxy.recyclerview.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lxy.recyclerview.R;

/**
 * Created by lxy
 */

public class TimeLineDecoration extends RecyclerView.ItemDecoration {
    // 写右边字的画笔(具体信息)
    private Paint mPaint;

    // 写左边日期字的画笔( 时间 + 日期)
    private Paint mPaint1;
    private Paint mPaint2;

    // 左 上偏移长度
    private int itemView_leftinterval;
    private int itemView_topinterval;

    // 轴点半径
    private int circle_radius;

    // 图标
    private Bitmap mIcon;

    public TimeLineDecoration(Context context) {
        // 轴点画笔(红色)
        mPaint = new Paint();
        mPaint.setColor(Color.RED);

        // 左边时间文本画笔(蓝色)
        // 此处设置了两只分别设置 时分 & 年月
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setTextSize(30);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.BLUE);

        // 赋值ItemView的左偏移长度为200
        itemView_leftinterval = 200;

        // 赋值ItemView的上偏移长度为50
        itemView_topinterval = 50;

        // 赋值轴点圆的半径为10
        circle_radius = 10;

        // 获取图标资源
        //  mIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // 设置左、上 padding
        outRect.set(itemView_leftinterval, itemView_topinterval, 0, 0);
    }

    /**
     * 重写ondraw ，绘制时间轴和文本
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();

        /**
         * 遍历每个Item，分别获取它们的位置信息，然后再绘制对应的分割线
         */
        for (int i = 0; i < childCount; i++) {
            // 获取每个Item对象
            View itemView = parent.getChildAt(i);

            /**
             * 绘制轴点  圆心(x,y)
             */
            float centerx = itemView.getLeft() - itemView_leftinterval / 3f;
            float centery = itemView.getTop() - itemView_topinterval + (itemView_topinterval + itemView.getHeight()) / 2f;
            // 绘制轴点
            c.drawCircle(centerx, centery, circle_radius, mPaint);

            // 通过Canvas绘制角标
            // c.drawBitmap(mIcon, centerx - circle_radius, centery - circle_radius, mPaint);

            /**
             * 绘制上半轴线
             */
            // 上端点坐标(x,y)
            float upLine_up_x = centerx;
            float upLine_up_y = itemView.getTop() - itemView_topinterval;
            // 下端点坐标(x,y)
            float upLine_bottom_x = centerx;
            float upLine_bottom_y = centery - circle_radius;
            //绘制上半部轴线
            c.drawLine(upLine_up_x, upLine_up_y, upLine_bottom_x, upLine_bottom_y, mPaint);

            /**
             * 绘制下半轴线
             */
            // 上端点坐标(x,y)
            float bottomLine_up_x = centerx;
            float bottom_up_y = centery + circle_radius;
            // 下端点坐标(x,y)
            float bottomLine_bottom_x = centerx;
            float bottomLine_bottom_y = itemView.getBottom();
            //绘制下半部轴线
            c.drawLine(bottomLine_up_x, bottom_up_y, bottomLine_bottom_x, bottomLine_bottom_y, mPaint);


            /**
             * 绘制左边时间文本
             */
            // 获取每个Item的位置
            int index = parent.getChildAdapterPosition(itemView);
            // 设置文本起始坐标
            float Text_x = itemView.getLeft() - itemView_leftinterval * 5 / 6;
            float Text_y = upLine_bottom_y;

            // 根据Item位置设置时间文本
            switch (index) {
                case (0):
                    // 设置日期绘制位置
                    c.drawText("13:40", Text_x, Text_y, mPaint1);
                    c.drawText("2017.4.03", Text_x + 5, Text_y + 20, mPaint2);
                    break;
                case (1):
                    // 设置日期绘制位置
                    c.drawText("17:33", Text_x, Text_y, mPaint1);
                    c.drawText("2017.4.03", Text_x + 5, Text_y + 20, mPaint2);
                    break;
                case (2):
                    // 设置日期绘制位置
                    c.drawText("20:13", Text_x, Text_y, mPaint1);
                    c.drawText("2017.4.03", Text_x + 5, Text_y + 20, mPaint2);
                    break;
                case (3):
                    // 设置日期绘制位置
                    c.drawText("11:40", Text_x, Text_y, mPaint1);
                    c.drawText("2017.4.04", Text_x + 5, Text_y + 20, mPaint2);
                    break;
                case (4):
                    c.drawText("13:20", Text_x, Text_y, mPaint1);
                    c.drawText("2017.4.04", Text_x + 5, Text_y + 20, mPaint2);
                    break;
                case (5):
                    // 设置日期绘制位置
                    c.drawText("22:40", Text_x, Text_y, mPaint1);
                    c.drawText("2017.4.04", Text_x + 5, Text_y + 20, mPaint2);
                    break;
                default:
                    c.drawText("已签收", Text_x, Text_y, mPaint1);
                    break;
            }
        }
    }
}
