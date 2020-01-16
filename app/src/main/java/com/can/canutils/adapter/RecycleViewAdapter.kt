package com.can.canutils.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.can.canutils.R
import com.can.mvp.adapter.BaseRefreshAdapter

/**
 * Created by CAN on 2020/1/16.
 */
class RecycleViewAdapter(context: Context) : BaseRefreshAdapter<String>(context) {
    override fun getViewHolder(view: View, type: Int): RecyclerView.ViewHolder {
        return ViewHolder(view)
    }

    override fun getLayoutId(type: Int): Int {
        return R.layout.item_recycle_view
    }

    override fun setView(viewHolder: RecyclerView.ViewHolder, t: String, position: Int) {
        (viewHolder as ViewHolder).tv.text = t
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv: TextView = itemView.findViewById(R.id.tv)
    }

}