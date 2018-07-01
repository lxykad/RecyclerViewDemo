package com.lxy.recyclerview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxy.recyclerview.R;
import com.lxy.recyclerview.entity.SecondBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxy
 */
public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.SecondHolder> {

    private Context mContext;
    private List<SecondBean> mList = new ArrayList<>();

    public SecondAdapter(Context mContext, List<SecondBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public SecondHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_second_recycler_view, parent, false);
        SecondAdapter.SecondHolder holder = new SecondAdapter.SecondHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SecondHolder holder, int position) {
        SecondBean bean = mList.get(position);
        holder.tvName.setText(bean.name);

        if (bean.type % 5 == 0) {
            holder.tvType.setVisibility(View.VISIBLE);
            holder.tvType.setBackgroundColor(Color.parseColor("#39c6c1"));
            holder.tvType.setText(String.valueOf(bean.type));
        } else {
            holder.tvType.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SecondHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvType;

        public SecondHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
        }
    }

}
