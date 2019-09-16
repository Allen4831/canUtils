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
    if (g.isEmpty() || s.isEmpty())
        return 0
    Arrays.sort(g)
    Arrays.sort(s)
    var child = 0
    var biscuits = 0
    while (child < g.size && biscuits < s.size) {
        if (g[child] <= s[biscuits])
            child++
        biscuits++
    }
    return child
}


/**
 * 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。

每个矩形由其左下顶点和右上顶点坐标表示，如图所示。



示例:

输入: -3, 0, 3, 4, 0, -1, 9, 2
输出: 45
说明: 假设矩形面积不会超出 int 的范围。
 */
fun computeArea(A: Int, B: Int, C: Int, D: Int, E: Int, F: Int, G: Int, H: Int): Int {
    return when {
        E > C || B > H || D < F || A > G -> { //两个矩形不重叠，取各自的面积之和
            (D - B) * (C - A) + (G - E) * (H - F)
        }
        else -> { //两个矩形重叠，取两个矩形之和-矩形的重叠部分
            (D - B) * (C - A) + (G - E) * (H - F) - (Math.min(C, G) - Math.max(A, E)) * (Math.min(D, H) - Math.max(B, F))
        }
    }
}


/**
 * 给定一个整数数组 A ，考虑 A 的所有非空子序列。

对于任意序列 S ，设 S 的宽度是 S 的最大元素和最小元素的差。

返回 A 的所有子序列的宽度之和。

由于答案可能非常大，请返回答案模 10^9+7。
 
示例：
输入：[2,1,3]
输出：6
解释：
子序列为 [1]，[2]，[3]，[2,1]，[2,3]，[1,3]，[2,1,3] 。
相应的宽度是 0，0，0，1，1，2，2 。
这些宽度之和是 6 。
[1,2,3,4]
[1,2] [1,3] [1,4] [2,3] [2,4] [3,4] [1,2,3] [1,2,4] [1,3,4] , [2,3,4] [1,2,3,4]
1  ,  2  ,  3  ,  1  ,  2  ,  1  ,   2    ,  3  ,    3   ,     2   ,    3
和为23
提示：
1 <= A.length <= 20000
1 <= A[i] <= 20000

todo 暂时无解

 */
fun sumSubseqWidths(A: IntArray): Int {
    val mod = 100000000 + 7
    Arrays.sort(A)
    var widths = 0
    var b: Int
    var t = 2
    val length = A.size
    for (i in 0 until length) {
        b = ((A[i] - A[length - i - 1]) * (t - 1)) % mod
        t = (t * 2) % mod
        widths = (widths + b) % mod
    }
    return widths
}


/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:

输入: 123
输出: 321
 示例 2:

输入: -123
输出: -321
示例 3:

输入: 120
输出: 21
注意:

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
fun reverse(x: Int): Int {
    var temp: Long = 0
    var n = x
    while (n != 0) {
        temp = temp * 10 + n % 10
        n /= 10
    }
    if (temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE)
        return 0
    return temp.toInt()
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

/****
 *  给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

示例 1:

输入:
Tree 1                     Tree 2
1                         2
/ \                       / \
3   2                     1   3
/                           \   \
5                             4   7
输出:
合并后的树:
3
/ \
4   5
/ \   \
5   4   7
注意: 合并必须从两个树的根节点开始。
 */
fun mergeTrees(t1: TreeNode?, t2: TreeNode?): TreeNode? {
    if (t1 == null) return t2
    if (t2 == null) return t1
    t1.`val` += t2.`val`
    if (t1.left != null && t2.left != null)
        mergeTrees(t1.left, t2.left)
    else if (t1.left == null && t2.left != null)
        t1.left = t2.left
    if (t1.right != null && t2.right != null)
        mergeTrees(t1.right, t2.right)
    else if (t1.right == null && t2.right != null)
        t1.right = t2.right
    return t1
}

/**
 *  给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。

J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。

示例 1:

输入: J = "aA", S = "aAAbbbb"
输出: 3
示例 2:

输入: J = "z", S = "ZZ"
输出: 0
注意:

S 和 J 最多含有50个字母。
 J 中的字符不重复。
 */
fun numJewelsInStones(J: String, S: String): Int {
    var count = 0
    J.forEach {
        S.forEach { ch ->
            if (it == ch)
                count++
        }
    }
    return count
}


/**
 *编写一个程序判断给定的数是否为丑数。

丑数就是只包含质因数 2, 3, 5 的正整数。

示例 1:

输入: 6
输出: true
解释: 6 = 2 × 3
示例 2:

输入: 8
输出: true
解释: 8 = 2 × 2 × 2
示例 3:

输入: 14
输出: false
解释: 14 不是丑数，因为它包含了另外一个质因数 7。
说明：

1 是丑数。
输入不会超过 32 位有符号整数的范围: [−231,  231 − 1]。
 */
fun isUgly(num: Int): Boolean {
    if (num <= 0) return false
    var n = num
    while (n % 2 == 0) {
        n /= 2
    }
    while (n % 3 == 0) {
        n /= 3
    }
    while (n % 5 == 0) {
        n /= 5
    }
    if (n == 1)
        return true
    return false
}

/**
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。

示例 1:

输入: 27
输出: true
示例 2:

输入: 0
输出: false
示例 3:

输入: 9
输出: true
示例 4:

输入: 45
输出: false
进阶：
你能不使用循环或者递归来完成本题吗？
 */
fun isPowerOfThree(n: Int): Boolean {
    if (n == 0) return false
    var num = n
    while (num % 3 == 0) {
        num /= 3
    }
    return num == 1
}


/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,3,2]
输出: 3
示例 2:

输入: [0,1,0,1,0,1,99]
输出: 99
 */
fun singleNumber(nums: IntArray): Int {
    var a = 0
    var b = 0
    for (num in nums) {
        a = a xor num and b.inv()
        b = b xor num and a.inv()
    }
    return a
}

/**
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。

如果有两个中间结点，则返回第二个中间结点。

 

示例 1：

输入：[1,2,3,4,5]
输出：此列表中的结点 3 (序列化形式：[3,4,5])
返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
注意，我们返回了一个 ListNode 类型的对象 ans，这样：
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
示例 2：

输入：[1,2,3,4,5,6]
输出：此列表中的结点 4 (序列化形式：[4,5,6])
由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 

提示：

给定链表的结点数介于 1 和 100 之间。
 */
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun middleNode(head: ListNode?): ListNode? {

    return head
}