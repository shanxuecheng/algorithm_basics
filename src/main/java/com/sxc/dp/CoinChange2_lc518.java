package com.sxc.dp;

/**
 * leetcode 518
 *
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 要求计算金额之和等于 amount 的硬币组合数。其中，coins 的每个元素可以选取多次，且不考虑选取元素的顺序。
 * 如果任何硬币组合都无法凑出总金额，返回 0 。
 *
 * 动态规划方法理解：
 *
 * 用 dp[x] 表示金额之和等于 x 的硬币组合数，目标是求 dp[amount]。
 *
 * 假设硬币面额数组 coins 的长度为 n。
 * 从第一个硬币面额 coins[0] 开始遍历，即假设当前只有第一种硬币面额，计算出 dp[1], dp[2] ... dp[amount]。
 * 用 i 表示金额，只需要考虑 coins[0] ≤ i ≤ amount 的情况。
 * 1. 如果 i - coin[0] = 0，说明存在 1 种凑出金额 i 的硬币组合，即 1 个面额为 coins[0] 的硬币。
 * 2. 如果 i > coin[0]，则可以查看 dp[i-coins[0]]，即金额 i - coins[0] 是否能被凑出。
 *    如果能，则 dp[i] = dp [i-coins[0]] = 1，否则 dp[i] 与 dp [i-coins[0]] 一样，都为0。
 * 根据以上两种情况，可以考虑初始化 dp[1], dp[2] ... dp[amount] 为 0。
 * 因为 i - coin[0] = 0 说明可以凑出，所以可以初始化 dp[0] = 1，可以得出 dp[i] = dp[0] = 1。
 *
 * 推广到更一般的情况，当 coin ≤ i≤ amount 时，如果存在 n 种硬币组合的金额之和等于 i−coin，
 * 则在每种硬币组合中增加一个面额为 coin 的硬币，即可得到 n 种金额之和等于 i 的硬币组合。所以可以把 dp[i-coin] 加到 dp[i]。
 *
 *
 * 动态规划的解法：
 * 1. 初始化 dp[0] = 1
 * 2. 遍历硬币面额数组 coins，对其中每个面额 coin 做如下操作：
 *    遍历 i 从 coin 到 amount，将 dp[i−coin] 的值加到 dp[i]
 * 3. 最终得到 dp[amount] 的值即为答案。
 *
 * 因为遍历的是面额数组，所以每次遍历到新面额后，得到的组合一定是新组合，不会出现重复计算组合的情况。
 */
public class CoinChange2_lc518 {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        System.out.println(change(11, coins));
    }

    public static int change(int amount, int[] coins) {
        // 注意，虽然结果保证在 32 位带符号整数范围内，但当最后结果为 0 时，中间计算过程可能会溢出。
        // 所以，我们可以先使用相同的动态规划方法求出是否有有效解，如果没有则直接返回。
        boolean[] valid = new boolean[amount + 1];
        valid[0] = true;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                valid[i] |= valid[i - coin];
            }
        }
        if(!valid[amount]) return 0;

        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = dp[i] + dp[i - coin];
            }
        }
        return dp[amount];
    }

}
