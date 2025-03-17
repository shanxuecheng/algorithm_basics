package com.sxc.dp;

import java.util.Arrays;

public class CoinChange2 {

    public static void main(String[] args) {
        coinChange(new int[]{5, 2, 1, 4}, 8);
    }

    public static int coinChange(int[] coinValues, int amount) {
        int max = amount + 1;
        int[] dp = new int[max];
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
