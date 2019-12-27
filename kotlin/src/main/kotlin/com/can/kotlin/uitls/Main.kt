package com.can.kotlin.uitls

import kotlinx.coroutines.runBlocking
import java.util.*

/**
 * Created by CAN on 2019/12/23.
 */
private const val nearLight = "近光灯"
private const val farBeam = "远光灯"
private const val doubleFlash = "双闪"
private const val indicatorLamp = "示宽灯"

private val mItemBank = mapOf("将要进行夜间行驶" to nearLight
        , "夜间通过急弯" to doubleFlash, "夜间通过坡路" to doubleFlash, "夜间通过拱桥" to doubleFlash,
        "夜间通过人行横道" to doubleFlash, "进入照明良好道路" to nearLight, "进入照明不良道路" to farBeam,
        "夜间在路边临时停车" to indicatorLamp, "夜间与机动车会车" to nearLight, "夜间超越前方车辆" to doubleFlash,
        "夜间通过路口" to nearLight, "同方向近距离跟车行驶" to nearLight)

private val mQuestion = arrayOf("将要进行夜间行驶", "夜间通过急弯", "夜间通过坡路", "夜间通过拱桥", "夜间通过人行横道"
        , "进入照明良好道路", "进入照明不良道路", "夜间在路边临时停车", "夜间与机动车会车", "夜间超越前方车辆", "夜间通过路口", "同方向近距离跟车行驶")

fun main() {
    runBlocking {
        while (true) {
            val position = Random().nextInt(mQuestion.size)
            val question = mQuestion[position]
            println(question)
            val scanner = Scanner(System.`in`)
            print("输入你的答案:")

            when (scanner.next()) {
                "exit" -> return@runBlocking
                mItemBank[question] -> println("回答正确")
                else -> println("回答错误，正确答案是:${mItemBank[question]}")
            }
            println()
        }
    }
}