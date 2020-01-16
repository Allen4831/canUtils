package com.can.canutils.ui;

import android.view.View;

import com.can.canutils.R;
import com.can.canutils.adapter.RecycleViewPagerAdapter;
import com.can.mvp.base.BaseActivity;
import com.can.mvp.views.BindView;
import com.can.mvp.views.NestedViewPager;

/**
 * Created by can on 2018/6/15.
 * 竖直ViewPager
 */

public class ViewPagerActivity extends BaseActivity {

    @BindView(id = R.id.vp)
    private NestedViewPager mVp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_viewpager;
    }

    @Override
    public void initView(View view) {
        mVp.setAdapter(new RecycleViewPagerAdapter((getSupportFragmentManager())));
    }
}
