package com.can.canutils.utils

/**
 * Created by CAN on 2019/8/8.
 */

//冒泡排序算法
fun bubblingAlgorithm(array: MutableList<Int>?): String {

    var result = ""

    array?.let {
        for (i in 0 until it.size - 1) {
            for (j in i + 1 until it.size) {
                if (it[i] > it[j]) {
                    val temp = it[i]
                    it[i] = it[j]
                    it[j] = temp
                }
            }
        }

        it.forEach { index ->
            if (it.indexOf(index) != it.size - 1) {
                result += "$index,"
            } else {
                result += index
            }
        }

    }

    return result
}

//归并排序算法
fun mergeAlgorithm(array: MutableList<Int>?): String {

    var result = ""

    array?.let {
        mergeSort(it, 0, it.size - 1)

        it.forEach { index ->
            if (it.indexOf(index) != it.size - 1) {
                result += "$index,"
            } else {
                result += index
            }
        }

    }

    return result

}

//合并、排序
fun mergeSort(array: MutableList<Int>, min: Int, max: Int) {
    if (min < max) {
        val mid = (min + max) / 2
        mergeSort(array, min, mid)
        mergeSort(array, mid + 1, max)
        merge(array, min, mid, max)
    }
}

//合并
fun merge(array: MutableList<Int>, min: Int, mid: Int, max: Int) {
    val temp = IntArray(max - min + 1) //定义长度固定的数组
    var i = min
    var j = mid + 1
    var k = 0
    while (i <= mid && j <= max)
        temp[k++] = if (array[i] < array[j]) array[i++] else array[j++]
    while (i <= mid)
        temp[k++] = array[i++]
    while (j <= max)
        temp[k++] = array[j++]
    for (t in temp.indices)
        array[t + min] = temp[t]
}

//快速排序算法
fun fastAlgorithm(array: MutableList<Int>?) : String {
    var result = ""

    array?.let {

    }

    return result
}