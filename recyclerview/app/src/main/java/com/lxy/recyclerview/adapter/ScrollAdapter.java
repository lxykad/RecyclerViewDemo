package com.lxy.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.recyclerview.R;
import com.lxy.recyclerview.entity.HTimeLineBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2018/7/15.
 */

public class ScrollAdapter extends RecyclerView.Adapter<ScrollAdapter.ScrollHolder> {

    private List<HTimeLineBean> mList;
    private HTimeLineAdapter adapter;
    private LinearLayoutManager layoutManager;

    public ScrollAdapter(List<HTimeLineBean> mList, Context context) {
        this.mList = mList;

        adapter = new HTimeLineAdapter(mList);

    }

    @Override
    public ScrollHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_h_time_line_itemview, parent, false);

        return new ScrollHolder(view);
    }

    @Override
    public void onBindViewHolder(ScrollHolder holder, int position) {

        RecyclerView.LayoutManager manager = holder.recyclerView.getLayoutManager();
        if (manager == null) {
            layoutManager = new LinearLayoutManager(holder.recyclerView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.recyclerView.setLayoutManager(this.layoutManager);
        }
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ScrollHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;

        public ScrollHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view_item);
        }
    }

    @Override
    public void onViewRecycled(ScrollHolder holder) {
        super.onViewRecycled(holder);
    }
}
