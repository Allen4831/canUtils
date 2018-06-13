package com.can.canutils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.can.mvp.R;

/**
 * Created by can on 2018/6/13.
 */

public class My22Adapter extends RecyclerView.Adapter {

    private Context context;
    public My22Adapter(Context context){
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_toast);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_toast_text,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder){
            ((ViewHolder) holder).tv.setText(" 你好 " +position);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
