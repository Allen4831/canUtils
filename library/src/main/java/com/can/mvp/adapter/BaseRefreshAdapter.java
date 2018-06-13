package com.can.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by can on 2018/6/13.
 * 通用适配器
 */

public abstract class BaseRefreshAdapter<T> extends RecyclerView.Adapter {

    private Context context;
    private List<T> list = new ArrayList<>();

    public BaseRefreshAdapter(Context context){
        this.context = context;
    }

    //设置数据
    public void setList(List<T> list){
        this.list = list;
        notifyDataSetChanged();
    }

    //添加数据
    public void addList(List<T> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(getLayoutId(viewType)==0){
            return null;
        }else{
            View view = LayoutInflater.from(context).inflate(getLayoutId(viewType),null);
            if(view==null)
                return null;
            else
                return getViewHolder(view,viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setView(holder,list.get(position),position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public abstract RecyclerView.ViewHolder getViewHolder(View view,int type);

    public abstract int getLayoutId(int type);

    public abstract void setView(RecyclerView.ViewHolder viewHolder, T t, int position);

}
