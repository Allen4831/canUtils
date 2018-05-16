package com.can.mvp.views.baseviews;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.can.mvp.R;
import com.can.mvp.utils.NetWorkUtil;

/**
 * Created by can on 2018/3/9.
 * 自定义下拉刷新-上拉加载更多
 */

public class IRecycleView extends SwipeRefreshLayout{

    //支持嵌套滑动的ScrollView
    private NestedScrollView scrollView;
    public RecyclerView recyclerView;
    //加载中的TextView
    private TextView textView;
    //加载中的loading
    private ProgressBar progressBar;
    //加载更多时显示的View
    private LinearLayout llLoadMoreView;
    //头部View
    private LinearLayout llHeader;
    //尾部View
    private LinearLayout llFooter;
    //滑动距离
    private int scrollDistance;
    //是否为加载更多
    private boolean isLoadMore = false;
    //能否加载更多
    private boolean isCanLoadMore = true;
    //下拉刷新和上拉加载的监听
    private IRecycleView.OnIRecycleListener refreshListener;
    //数据状态改变的展示
    public DataStateLayout view_loading;

    public IRecycleView(Context context) {
        super(context);
        this.init(context);
    }

    public IRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context);
    }

    /**
     * 初始化操作
     * @param context 上下文
     */
    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_irecycleview, this);
        this.scrollView = (NestedScrollView)this.findViewById(R.id.scrollView);
        this.recyclerView = (RecyclerView)this.findViewById(R.id.recycler);
        this.textView = (TextView)this.findViewById(R.id.textView);
        this.progressBar = (ProgressBar)this.findViewById(R.id.progressBar);
        this.llLoadMoreView = (LinearLayout)this.findViewById(R.id.ll_loadmore);
        this.llHeader = (LinearLayout)this.findViewById(R.id.ll_header);
        this.llFooter = (LinearLayout)this.findViewById(R.id.ll_footer);
        this.view_loading = (DataStateLayout) this.findViewById(R.id.view_loading);
        this.initRecyclerView();
        this.initSwipeRefreshLayout();
        this.loadMore(false);
    }

    /**
     * 添加头部View
     * @param view
     */
    public void addHeaderView(View view) {
        if(this.llHeader != null && view != null) {
            this.llHeader.addView(view);
        }
    }

    /**
     * 移除头部View
     * @param view
     */
    public void removeHeaderView(View view) {
        if(this.llHeader != null && view != null) {
            this.llHeader.removeView(view);
        }

    }

    /**
     * 移除头部View
     * @param index 下标
     */
    public void removeHeaderView(int index) {
        if(this.llHeader != null && index >= 0 && index < this.llHeader.getChildCount()) {
            this.llHeader.removeViewAt(index);
        }

    }

    /**
     * 移除所有头部View
     */
    public void removeAllHeaderView() {
        if(this.llHeader != null) {
            this.llHeader.removeAllViews();
        }
    }

    /**
     * 获取头部View
     * @param index 下标
     * @return
     */
    public boolean getHeadView(int index){
        if(this.llHeader!=null&& index >= 0 && index < this.llHeader.getChildCount()){
            return true;
        }
        return false;
    }

    /**
     * 添加尾部View
     * @param view
     */
    public void addFooterView(View view) {
        if(this.llFooter != null && view != null) {
            this.llFooter.addView(view);
        }
    }

    /**
     * 获取尾部View
     * @param index 下标
     * @return
     */
    public boolean getFooterView(int index){
        if(this.llFooter!=null&& index >= 0 && index < this.llFooter.getChildCount()){
            return true;
        }
        return false;
    }

    /**
     * 移除尾部View
     * @param view
     */
    public void removeFooterView(View view) {
        if(this.llFooter != null && view != null) {
            this.llFooter.removeView(view);
        }
    }

    /**
     * 移除尾部View
     * @param index 下标
     */
    public void removeFooterView(int index) {
        if(this.llFooter != null && index >= 0 && index < this.llFooter.getChildCount()) {
            this.llFooter.removeViewAt(index);
        }

    }

    /**
     * 移除所有尾部View
     */
    public void removeAllFooterView() {
        if(this.llFooter != null) {
            this.llFooter.removeAllViews();
        }
    }

    /**
     * 刷新完成后的操作
     */
    public void refreshComlete() {
        if(NetWorkUtil.isNetWork(getContext())){//网络正常时
            if(recyclerView.getChildCount()==0){//空数据时
                setState(DataStateLayout.STATE_NODATA);
            }else {//非空数据时
                setState(DataStateLayout.STATE_HIDE_LAYOUT);
            }
            llHeader.setVisibility(VISIBLE);
        }else{//无网络时
            if(recyclerView.getChildCount()==0) {//没有数据时
                setState(DataStateLayout.STATE_NETWORK_ERROR);
                llHeader.setVisibility(GONE);
            }else {//有数据时
                setState(DataStateLayout.STATE_HIDE_LAYOUT);
            }
        }
        if(this.isRefreshing()) {
            this.setRefreshing(false);
        } else {
            this.loadMore(false);
        }
    }

    /**
     * 加载更多
     * @param loading 是否能加载更多
     */
    public void loadMore(boolean loading) {
        if(loading) {
            this.progressBar.setVisibility(VISIBLE);
            this.textView.setVisibility(VISIBLE);
            this.textView.setText("加载中...");
            this.isCanLoadMore = true;
            this.isLoadMore = true;
        } else {
            this.isLoadMore = false;
        }
    }

    /**
     * 没有更多数据的展示
     * @param text 展示文字
     */
    public void loadMoreNoData(String text) {
        this.progressBar.setVisibility(GONE);
        this.textView.setText(TextUtils.isEmpty(text)?"已经全部加载完":text);
        this.isCanLoadMore = false;
        requestLayout();
    }

    /**
     * 设置可以加载更多
     */
    public void setIsCanLoadMore(){
        this.progressBar.setVisibility(VISIBLE);
        this.textView.setVisibility(VISIBLE);
        this.textView.setText("加载中...");
        this.isCanLoadMore = true;
        this.isLoadMore = false;
    }

    /**
     * 设置是否可以加载更多
     * @param flag
     */
    public void setCanLoadMore(boolean flag){
        if(flag)
            this.llLoadMoreView.setVisibility(VISIBLE);
        else
            this.llLoadMoreView.setVisibility(GONE);
    }

    /**
     * 初始化刷新操作
     */
    private void initSwipeRefreshLayout() {
        this.setEnabled(false);
        super.setOnRefreshListener(new android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                if(IRecycleView.this.refreshListener != null) {
                    IRecycleView.this.refreshListener.onRefresh();
                }
            }
        });
        this.setColorSchemeResources(new int[]{R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark});
    }

    /**
     * 初始化RecycleView
     */
    private void initRecyclerView() {
        this.verticalLayoutManager();
        this.recyclerView.setNestedScrollingEnabled(false);
        this.scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                IRecycleView.this.scrollDistance = scrollY;
                int maxScrollAmount = v.getChildAt(0).getMeasuredHeight() - v.getHeight();
                if(IRecycleView.this.scrollDistance >= maxScrollAmount  && !IRecycleView.this.isRefreshing() && !IRecycleView.this.isLoadMore && IRecycleView.this.isCanLoadMore) {
                    if(IRecycleView.this.refreshListener != null) {
                        IRecycleView.this.loadMore(true);
                        IRecycleView.this.refreshListener.onLoadMore();
                    }
                }else if(!isCanLoadMore){
                    textView.setText("已经全部加载完");
                    textView.setVisibility(VISIBLE);
                }
            }
        });
        view_loading.setOnDataStateClickListener(new DataStateLayout.onDataStateClickListener() {
            @Override
            public void onDateStateClickListener(int state) {
                if(state==DataStateLayout.STATE_NO_LOGIN){//未登录
                    // TODO: 2018/3/12 去登录
                }else if(state==DataStateLayout.STATE_NETWORK_ERROR){//网络错误
                    setState(DataStateLayout.STATE_NETWORK_LOADING);
                    if(refreshListener != null) {
                        refreshListener.onRefresh();
                    }
                }else if(state==DataStateLayout.STATE_NODATA){//数据为空
                    setState(DataStateLayout.STATE_NETWORK_LOADING);
                    if(refreshListener != null) {
                        refreshListener.onRefresh();
                    }
                }
            }
        });
    }

    /**
     * 设置刷新
     * @param refreshing
     */
    public void setRefreshing(boolean refreshing) {
        super.setRefreshing(refreshing);
        if(this.refreshListener != null && refreshing) {
            this.refreshListener.onRefresh();
        }
    }

    /**
     * 设置数据和网络及登录状态
     * @param state
     */
    public void setState(int state){
        view_loading.setState(state);
        if(state==DataStateLayout.STATE_NO_LOGIN||state==DataStateLayout.STATE_NETWORK_LOADING)
            this.setEnabled(false);
        else
            setEnabled(true);
    }

    /**
     * 设置刷新监听
     * @param listener
     */
    public void setOnIRecycleListener(IRecycleView.OnIRecycleListener listener) {
        this.refreshListener = listener;
    }

    /**
     * 获取Recycleview
     * @return RecycleView
     */
    public RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    /**
     * 设置为竖直滚动
     * @return
     */
    public RecyclerView verticalLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        this.getRecyclerView().setLayoutManager(manager);
        return this.getRecyclerView();
    }

    /**
     * 设置为水平滚动
     * @return
     */
    public RecyclerView horizontalLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        this.getRecyclerView().setLayoutManager(manager);
        return this.getRecyclerView();
    }

    /**
     * 设置适配器
     * @param adapter
     * @return
     */
    public RecyclerView setAdapter(RecyclerView.Adapter adapter) {
        this.getRecyclerView().setAdapter(adapter);
        return this.getRecyclerView();
    }

    /**
     * 获取NestedScrollView
     * @return NestedScrollView
     */
    public NestedScrollView getScrollView() {
        return this.scrollView;
    }

    /***
     * 监听接口
     */
    public interface OnIRecycleListener {
        void onRefresh();

        void onLoadMore();
    }
}
