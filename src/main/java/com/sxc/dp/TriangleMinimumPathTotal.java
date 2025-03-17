package com.sxc.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 120
 *
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 *
 * 如果已知三角形第 i 行中每个元素到最底部一行的最短路径和。
 * 则容易计算出第 i-1 行中每个元素到最底部一行的最短路径和。取其中的最小值，即为第 i-1 行到最底部一行的最短路径和。
 *
 * 因此可以从最底部一行向上动态规划。最底部一行每个元素到最底部的最短路径和就是每个元素自身。
 * 观察到最底部一行最长，所以可以自定义一个动态规划数组 dp，长为最底部一行的长度，也是三角形的行数。
 * 每向上计算一层，就可以更新 dp 数组中对应位置的值，即该位置到三角形底部的最短路径和。
 * 所以只需要一个数组 dp，最终从顶部到底部的最短路径和就是 dp[0]。
 */
public class TriangleMinimumPathTotal {

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));

        System.out.println(minimumTotal(triangle));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        // 三角形的高度，也是最底部一层的长度
        int n = triangle.size();
        // 动态规划数组
        int[] dp = new int[n];
        // 初始化 dp 为最后一行的值，即最后一行到三角形底部的最短路径和为其自身
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n-1).get(i);
        }
        // 从倒数第二行开始计算
        for (int i = n - 2; i >= 0; i--) {
            // 第 i 行数据
            List<Integer> row = triangle.get(i);
            // 遍历第 i 行的每个位置
            for (int j = 0; j <= i; j++) {
                // 每个位置到底部的最短路径和为：
                // 当前位置的值加上下一行位置 j 与位置 j + 1 到底部最短路径和中的较小值
                dp[j] = Math.min(dp[j], dp[j + 1]) + row.get(j);
            }
        }
        return dp[0];
    }

}
