package com.lxy.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxy.recyclerview.R;
import com.lxy.recyclerview.entity.HTimeLineBean;

import java.util.List;

/**
 * Created by lxy
 */

public class HTimeLineAdapter extends RecyclerView.Adapter<HTimeLineAdapter.MyViewHolder> {

    private List<HTimeLineBean> mList;

    public HTimeLineAdapter(List<HTimeLineBean> mList) {
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_h_time_line_h, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HTimeLineBean bean = mList.get(position);
        holder.title.setText(bean.title);
        holder.content.setText(bean.content);

        /*
        纵向列表时
        if (position == mList.size() - 1) {
            holder.line.setVisibility(View.GONE);
        } else {
            holder.line.setVisibility(View.VISIBLE);
        }
        */

        /**
         * 横向列表时
         */
        if (position == mList.size() - 1) {
            holder.line.setVisibility(View.INVISIBLE);
        } else {
            holder.line.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView content;
        private View line;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tv_title);
            content = (TextView) itemView.findViewById(R.id.tv_content);
            line = itemView.findViewById(R.id.view_line);
        }
    }

    public void clear(){
        mList.clear();
        notifyDataSetChanged();
    }
}
