package com.lxy.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxy.recyclerview.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lxy
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.MyViewholder> {

    private LayoutInflater inflater;
    private ArrayList<HashMap<String, Object>> mList;

    public TimeLineAdapter(ArrayList<HashMap<String, Object>> list) {
        this.mList = list;
    }


    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_time_line, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        holder.title.setText((String) mList.get(position).get("title"));
        holder.text.setText((String) mList.get(position).get("text"));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewholder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView text;

        public MyViewholder(View root) {
            super(root);
            title = (TextView) root.findViewById(R.id.Itemtitle);
            text = (TextView) root.findViewById(R.id.Itemtext);
        }
    }
}
