package com.can.canutils.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.can.canutils.R;

import java.util.List;

/**
 * Created by can on 2018/6/15.
 * 适配器
 */

public class VerticalScrollPagerAdapter extends PagerAdapter {

    private Context context;//上下文
    private List<String> list;//数据集合
    private View[] views ;//view数组

    public VerticalScrollPagerAdapter(Context context, List<String> list, View[] views){
        this.context = context;
        this.list = list;
        this.views = views;
    }

    @Override
    public int getCount() {
        //设置总数
        if(list.size()>0)
            return Integer.MAX_VALUE;
        else
            return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //当前实际下标
        int index = position%list.size();
        container.removeView(views[index]);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        //当前实际下标
        final int index = position%list.size();
        View view = views[index];
        if(view!=null&&view.getParent()!=null){
            return view;
        }else{
            View view_item = LayoutInflater.from(context).inflate(R.layout.item_vertical_textview,null);
            TextView tv =  view_item.findViewById(R.id.tv_item);
            tv.setText(list.get(index));
            views[index] = tv;
            ViewGroup parent = (ViewGroup) tv.getParent();
            if (parent != null) {
                //防止布局出错
                parent.removeAllViews();
            }
            container.addView(views[index]);
            return tv;
        }
    }
}
