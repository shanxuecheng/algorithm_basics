package com.sxc.dp;

import java.util.Arrays;

/**
 * leetcode 322
 *
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 自下而上动态规划:
 * 定义 F(i) 为组成金额 i 所需最少的硬币数量，假设在计算 F(i) 之前，我们已经计算出 F(0) − F(i−1) 的答案。
 * 则 F(i) 对应的转移方程应为：F(i) = min ( F(i-C1), F(i-C1) ... F(i-Cn) ) + 1。C1, C2 ... Cn 分别为 n 个硬币的面值。
 *
 */
public class CoinChange2 {

    public static void main(String[] args) {
        coinChange(new int[]{5, 2, 1, 4}, 8);
    }

    public static int coinChange(int[] coinValues, int amount) {
        int max = amount + 1;
        // amount 为金额，定义长度为 amount+1 的数组，用于记录凑成从金额 1 到金额 amount 的每个金额需要的最少硬币个数。
        int[] dp = new int[max];
        // 凑成每个金额需要的硬币数量不可能大于 amount + 1，用 amount + 1 初始化数组，可用于最终判断能否凑成该金额。
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coinValues.length; j++) {
                if (coinValues[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coinValues[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
