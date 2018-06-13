package com.can.canutils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.can.canutils.R;
import com.can.canutils.bean.HomeArticleListBean;
import com.can.mvp.adapter.BaseRefreshAdapter;

/**
 * Created by can on 2018/6/13.
 * 首页文章列表适配器
 */

public class HomeArticleListAdapter extends BaseRefreshAdapter<HomeArticleListBean.DataBean.DatasBean> {


    public HomeArticleListAdapter(Context context) {
        super(context);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_title);
        }
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view, int type) {
        return new ViewHolder(view);
    }


    @Override
    public int getLayoutId(int type) {
        return R.layout.item_home_article_list;
    }

    @Override
    public void setView(RecyclerView.ViewHolder viewHolder, HomeArticleListBean.DataBean.DatasBean dataBean, int position) {
        if(viewHolder instanceof ViewHolder){
            ((ViewHolder) viewHolder).tv.setText(dataBean.getTitle());
        }
    }

}
