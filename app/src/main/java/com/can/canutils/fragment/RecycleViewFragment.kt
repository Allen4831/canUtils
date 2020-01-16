package com.can.canutils.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.can.canutils.R
import com.can.canutils.adapter.RecycleViewAdapter
import com.can.mvp.base.BaseFragment
import com.can.mvp.views.BindView

/**
 * Created by CAN on 2020/1/16.
 */
class RecycleViewFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_recycle_view
    }


    override fun initView(view: View) {
        val mRv = view.findViewById<RecyclerView>(R.id.rv)
        val context = context!!
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mRv?.layoutManager = layoutManager
        val adapter = RecycleViewAdapter(context)
        mRv?.adapter = adapter
        val list = listOf("张三", "李四", "王五","张三", "李四", "王五","张三", "李四", "王五","张三", "李四", "王五","张三", "李四", "王五","张三", "李四", "王五","张三", "李四", "王五","张三", "李四", "王五")
        adapter.setList(list)

        val mRvVertical = view.findViewById<RecyclerView>(R.id.rv_vertical)
        mRvVertical.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val verticalAdapter = RecycleViewAdapter(context)
        mRvVertical.adapter = verticalAdapter
        verticalAdapter.setList(list)
    }
}