package com.can.canutils.ui

import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.can.canutils.R
import com.can.canutils.utils.bubblingAlgorithm
import com.can.mvp.base.BaseActivity

/**
 * Created by CAN on 2019/8/8.
 */
class AlgorithmActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_algorithm
    }

    private var mEtInput: EditText? = null
    private var mTvResult: TextView? = null

    private val choiceItems = arrayOf("冒泡排序", "归并排序")


    override fun initView(view: View?) {
        mEtInput = findViewById(R.id.et_input)
        mTvResult = findViewById(R.id.tv_result)

        findViewById<Button>(R.id.btn_algorithm).setOnClickListener {
            val alertBuilder = AlertDialog.Builder(this)


            var choosePosition = 0

            alertBuilder.setSingleChoiceItems(choiceItems, 0) { _, position ->
                choosePosition = position
            }.setTitle("请选择算法").setPositiveButton("确认") { _, _ ->
                transformation(choosePosition)
            }.setNegativeButton("取消") { _, _ ->
                transformation(choosePosition)
            }
            val dialog = alertBuilder.create()
            dialog.show()
        }
    }

    //转换
    private fun transformation(position: Int) {

        val inputText = mEtInput?.text?.toString()

        val numberArray = mutableListOf<Int>()

        inputText?.let { text ->
            val string = text.split(",")
            string.forEach {
                if (it.isNotEmpty()) {
                    numberArray.add(it.toInt())
                }
            }
        }

        when (position) {
            0 -> { //冒泡排序
                bubblingAlgorithm(numberArray)
            }

            1 -> {

            }
        }

        mTvResult?.text = position.toString()
    }
}