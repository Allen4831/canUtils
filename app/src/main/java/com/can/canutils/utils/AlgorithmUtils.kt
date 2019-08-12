package com.can.canutils.utils

/**
 * Created by CAN on 2019/8/8.
 * 算法
 */

//冒泡排序算法
fun bubblingAlgorithm(array: MutableList<Int>?): String {
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
        return getArrayResult(it)
    }
    return ""
}

//归并排序算法
fun mergeAlgorithm(array: MutableList<Int>?): String {
    array?.let {
        mergeSort(it, 0, it.size - 1)
        return getArrayResult(array)
    }
    return ""
}

//数组之间添加，
private fun getArrayResult(array: MutableList<Int>): String {
    var result = ""
    array.forEach {
        if (array.indexOf(it) != array.size - 1) {
            result += "$it,"
        } else {
            result += it
        }
    }
    return result
}

//合并、排序
private fun mergeSort(array: MutableList<Int>, min: Int, max: Int) {
    if (min < max) {
        val mid = (min + max) / 2
        mergeSort(array, min, mid)
        mergeSort(array, mid + 1, max)
        merge(array, min, mid, max)
    }
}

//合并
private fun merge(array: MutableList<Int>, min: Int, mid: Int, max: Int) {
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
fun fastAlgorithm(array: MutableList<Int>?): String {
    array?.let {
        fast(it, 0, it.size - 1)
        return getArrayResult(it)
    }
    return ""
}

//快速排序
private fun fast(array: MutableList<Int>, min: Int, max: Int) {
    if (min < max) {
        var left = min //最左边下标
        var right = max //最右边下标
        val baseNumber = array[left] //基准数，以最左边的数为准
        while (left < right) {
            while (left < right && array[right] >= baseNumber)  //从右向左,找到第一个比基准数小的数
                right--

            if (left < right) //找到后，放到最左边
                array[left] = array[right]

            while (left < right && array[left] <= baseNumber)  //从左向右,找到第一个比基准数大的数
                left++

            if (left < right) //找到后，放到最右边
                array[right] = array[left]
        }
        array[left] = baseNumber

        fast(array, min, left - 1)
        fast(array, left + 1, max)
    }
}