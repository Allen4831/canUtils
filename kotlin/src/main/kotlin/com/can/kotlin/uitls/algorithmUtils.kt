package com.can.kotlin.uitls

import java.util.*

/**
 * Created by CAN on 2019/9/11.
 */

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
满足要求的三元组集合为：
[
[-1, 0, 1],
[-1, -1, 2]
]
 */
fun threeSum(nums: IntArray): List<List<Int>> {
    val list = arrayListOf<List<Int>>()
    //排序nums
    Arrays.sort(nums)
    val length = nums.size
    for (i in 0 until length) {
        if (i > 0 && nums[i] == nums[i - 1]) continue
        var left = i + 1
        var right = length - 1

        while (left < right) {
            val sum = nums[i] + nums[left] + nums[right]
            when {
                sum == 0 -> {
                    list.add(arrayListOf(nums[i], nums[left], nums[right]))
                    while (left < right && nums[left] == nums[left + 1]) left++
                    while (left < right && nums[right] == nums[right - 1]) right--
                    left++
                    right--

                }
                sum < 0 -> {
                    left++
                }
                else -> {
                    right--
                }
            }
        }

    }

    return list
}


/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
示例 1 :
输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
说明 :
数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
fun subarraySum(nums: IntArray, k: Int): Int {
    var count = 0
    val length = nums.size
    for (i in 0 until length) {
        var sum = 0
        for (j in i until length) {
            sum += nums[j]
            if (sum == k)
                count++
        }
    }
    return count
}


/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
示例 1：
输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]
示例 2：
输入：["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]
 */
fun reverseString(s: CharArray): Unit {
    val length = s.size
    for (i in 0 until length / 2) {
        val temp = s[i]
        s[i] = s[length - i - 1]
        s[length - i - 1] = temp
    }
}


/**
 * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False
示例：
输入: 28
输出: True
解释: 28 = 1 + 2 + 4 + 7 + 14
 */
fun checkPerfectNumber(num: Int): Boolean {
    if (num == 1) return false
    var count = 1
    var i = 2
    var max = num / 2
    while (i < max) {
        if (num % i == 0) {
            count += i + num / i
            max = num / i
        }
        i++
    }
    return count == num
}


/**
 * 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。

例如：

[2,3,4]，中位数是 3

[2,3]，中位数是 (2 + 3) / 2 = 2.5

给出一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。

例如：

给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。

窗口位置                      中位数
---------------               -----
[1  3  -1] -3  5  3  6  7       1
1 [3  -1  -3] 5  3  6  7       -1
1  3 [-1  -3  5] 3  6  7       -1
1  3  -1 [-3  5  3] 6  7       3
1  3  -1  -3 [5  3  6] 7       5
1  3  -1  -3  5 [3  6  7]      6
 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 */
fun medianSlidingWindow(nums: IntArray, k: Int): DoubleArray {
    if (k == 0 || nums.isEmpty()) return doubleArrayOf()
    val length = nums.size - k + 1
    val resultArray = DoubleArray(length)
    val s = k % 2 == 0
    for ((index, i) in (0 until length).withIndex()) {
        if (s) {
            val copyNums = nums.copyOfRange(i, i + k)
            Arrays.sort(copyNums)
            resultArray[index] = (copyNums[k / 2 - 1].toDouble() + copyNums[k / 2].toDouble()) / 2.toDouble()
        } else {
            val copyNums = nums.copyOfRange(i, i + k)
            Arrays.sort(copyNums)
            resultArray[index] = copyNums[k / 2].toDouble()
        }
    }
    return resultArray
}


/**
 *假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。

注意：

你可以假设胃口值为正。
一个小朋友最多只能拥有一块饼干。

示例 1:

输入: [1,2,3], [1,1]

输出: 1

解释:
你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
所以你应该输出1。
示例 2:

输入: [1,2], [1,2,3]

输出: 2

解释:
你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
你拥有的饼干数量和尺寸都足以让所有孩子满足。
所以你应该输出2.
 */
fun findContentChildren(g: IntArray, s: IntArray): Int {

}