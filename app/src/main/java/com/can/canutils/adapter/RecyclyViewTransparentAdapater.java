package com.can.canutils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.can.canutils.R;
import com.can.mvp.adapter.BaseRefreshAdapter;

import java.util.List;

/**
 * Created by CAN on 18-8-24.
 */

public class RecyclyViewTransparentAdapater extends BaseRefreshAdapter<String> {


    public RecyclyViewTransparentAdapater(Context context) {
        super(context);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view, int type) {
        return new ViewHolder(view);
    }

    @Override
    public int getLayoutId(int type) {
        return R.layout.item_recycle_view_transparent;
    }

    @Override
    public void setView(RecyclerView.ViewHolder viewHolder, String strings, int position) {
        if(viewHolder instanceof ViewHolder){
            ((ViewHolder) viewHolder).tv.setText(strings);
        }
    }
}
