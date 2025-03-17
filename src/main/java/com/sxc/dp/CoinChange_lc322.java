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
 * 则 F(i) 对应的转移方程应为：F(i) = min { F(i-C1), F(i-C1) ... F(i-Cn) } + 1。C1, C2 ... Cn 分别为 n 个硬币的面值。
 *
 */
public class CoinChange_lc322 {

    public static void main(String[] args) {
        int count = coinChange(new int[]{5, 2, 1, 4}, 8);
        System.out.println(count);
    }

    public static int coinChange(int[] coinValues, int amount) {
        int max = amount + 1;
        // amount 为金额，定义长度为 amount+1 的数组，用于记录凑成从金额 1 到金额 amount 的每个金额需要的最少硬币个数。
        int[] dp = new int[max];
        // 凑成每个金额需要的硬币数量不可能大于 amount + 1，用 amount + 1 初始化数组，可用于判断能否凑成该金额。
        Arrays.fill(dp, max);
        // 处理边界情况，初始化 dp[0] 为 0，amount 为 0 时返回 0；另外，用于下面第二层循环中计算 dp[0] + 1  = 1
        dp[0] = 0;
        // 遍历 1 - amount 的每个金额，作为目标金额，计算凑成各个金额需要的最少硬币数量
        for (int i = 1; i <= amount; i++) {
            // 遍历每个硬币面额，比较 dp[i] 和 dp[i - coinValues[j]] + 1
            for (int j = 0; j < coinValues.length; j++) {
                // 硬币面额小于目标金额时才需要计算
                if (coinValues[j] <= i) {
                    // dp[i - coinValues[j]] 为凑成金额 i - coinValues[j] 需要的最少硬币数量
                    // 而 i - coinValues[j] 可能为 0，所以需要将 dp[0] 初始化为 0，此时凑成金额 i 需要的硬币数量为 dp[0] + 1，即 1
                    // 初始化 dp[i] 为 amount + 1（不可能达到的数量），方便这里调用 Match.min
                    dp[i] = Math.min(dp[i], dp[i - coinValues[j]] + 1);
                }
            }
        }
        // dp[amount] > amount 即 dp[amount] 仍为初始值 amount + 1，说明无法凑成该金额
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     *  递归计算 F(i-C1), F(i-C1) ... F(i-Cn)，借助数组记录凑成不同金额需要的最少硬币数量，避免重复计算
     */
    public static int coinChangeRecursion(int[] coinValues, int amount) {
        int max = amount + 1;
        // 记录不同金额需要的最少硬币数量，避免重复计算
        int[] resultRecorder = new int[amount + 1];
        Arrays.fill(resultRecorder, max);
        return doChange(coinValues, amount, resultRecorder);
    }

    private static int doChange(int[] coinValues, int amount, int[] resultRecorder) {
        if (amount == 0) {
            return 0;
        }
        int minCount = amount + 1;
        for (int i = 0; i < coinValues.length; i++) {
            int coinValue = coinValues[i];
            int leftAmount = amount - coinValue;
            if (leftAmount == 0) {
                return 1;
            } else if (leftAmount < 0) {
                continue;
            }

            // resultRecorder[leftAmount] 大于 amount，说明未计算凑成 leftAmount 的最少硬币数量
            if (resultRecorder[leftAmount] > amount) {
                // 递归计算，并记录结果
                resultRecorder[leftAmount] = doChange(coinValues, leftAmount, resultRecorder);
            }
            // 凑不成 leftAmount
            if (resultRecorder[leftAmount] == -1) {
                continue;
            }
            // 能凑成 leftAmount，加 1 为凑成 amount 需要的数量
            int count = resultRecorder[leftAmount] + 1;
            // 检查并记录当前最少数量
            if (count < minCount) {
                minCount = count;
            }
        }
        // 遍历完全部硬币面额，仍不能凑成金额 amount，记录并返回 -1
        if (minCount > amount) {
            resultRecorder[amount] = -1;
            return -1;
        }
        return minCount;
    }
}
