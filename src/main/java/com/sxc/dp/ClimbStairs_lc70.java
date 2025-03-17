package com.sxc.dp;

/**
 * leetcode 70
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 假设 f(i) 为总阶数为 i 时的方法数，则 f(1) = 1，f(2) = 2。
 * 当 i>2 时，f(i) = f(i-2) + f(i-1)
 */
public class ClimbStairs_lc70 {

    public static void main(String[] args) {

    }

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++){
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n];
    }
}
