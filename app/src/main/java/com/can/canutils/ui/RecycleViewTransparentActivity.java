package com.can.canutils.ui;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.can.canutils.R;
import com.can.canutils.adapter.HomeArticleListAdapter;
import com.can.canutils.adapter.RecyclyViewTransparentAdapater;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.views.BindView;
import com.can.mvp.views.RecycleViewTransparentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CAN on 18-8-24.
 */

public class RecycleViewTransparentActivity extends BaseActivity {


    @BindView(id = R.id.recycler_view)
    private RecycleViewTransparentView recycleViewTransparentView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycleview_transparent;
    }

    @Override
    public void initData(Bundle bundle) {
        super.initData(bundle);
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        RecyclyViewTransparentAdapater adapter = new RecyclyViewTransparentAdapater(this);
        List<String> strings = new ArrayList<>();
        for (int i=0;i<10;i++){
            strings.add("我是标签"+i+1);
        }
        adapter.setList(strings);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleViewTransparentView.recyclerView.setLayoutManager(linearLayoutManager);
        recycleViewTransparentView.recyclerView.setAdapter(adapter);
    }
}
