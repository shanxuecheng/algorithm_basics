package com.sxc.dp;

/**
 * leetcode 1143
 *
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。如果不存在公共子序列 ，返回 0 。
 *
 * 子序列中字符的相对顺序与原字符串一致，但不要求连续。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 *
 * 题解：
 * 最长公共子序列问题是典型的二维动态规划问题。
 *
 * 假设字符串 text1 和 text2 的长度分别为 m 和 n。
 * text1[0:i] 表示 text1 的长度为 i 的前缀，text2[0:j] 表示 text2 的长度为 j 的前缀。
 *
 * 创建 m+1 行 n+1 列的二维数组 dp。其中 dp[i][j] 表示 text1[0:i] 和 text2[0:j] 的最长公共子序列的长度。
 *
 * 动态规划的边界情况是：当 i=0 或 j=0 时，dp[i][j]=0，因为空字符串和任何字符串的最长公共子序列的长度都是 0。
 * 因此对任意 0≤j≤n，有 dp[0][j]=0；对任意 0≤i≤m，有 dp[i][0]=0。
 *
 * 当 i>0 且 j>0 时，考虑 dp[i][j] 的计算。
 * 可以根据 text1[i-1] 和 text2[j-1] （分别表示 text1 和 text2 的第 i 和第 j 个字符）是否相同分为两种情况：
 *
 * 1. 当 text1[i−1] = text2[j−1] 时，将这两个相同的字符称为公共字符。
 * 考虑 text1[0:i−1] 和 text2[0:j−1] 的最长公共子序列，
 * 再增加一个字符（即公共字符）即可得到 text 1 [0:i] 和 text 2 [0:j] 的最长公共子序列，因此 dp[i][j]=dp[i−1][j−1]+1。
 *
 * 2.当 text1[i−1] != text2[j−1] 时，考虑以下两项：
 * 1) text1[0:i-1] 和 text2[0:j] 的最长公共子序列
 * 2) text1[0:i] 和 text2[0:j-1] 的最长公共子序列
 * 要得到 text1[0:i] 和 text2[0:j] 的最长公共子序列，应当取两项中长度较大的一项，因此 dp[i][j]=max(dp[i−1][j],dp[i][j−1])。
 */
public class LongestCommonSubsequence_lc1143 {

    public static void main(String[] args) {
        String text1 = "a1b2c3d4e5f6";
        String text2 = "123456";
        System.out.println(longestCommonSubsequence(text1, text2));
    }

    private static int longestCommonSubsequence(String text1, String text2) {
        int length1 = text1.length();
        int length2 = text2.length();

        // int 数组元素初始值都为 0，无需初始化dp[0][j] 和 dp[i][0]
        int[][] dp = new int[length1 + 1][length2 + 1];

        for (int i = 1; i <= length1; i++) {
            char char1 = text1.charAt(i-1);
            for (int j = 1; j <= length2; j++) {
                char char2 = text2.charAt(j-1);
                if (char1 == char2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[length1][length2];
    }
}
