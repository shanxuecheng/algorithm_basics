package com.sxc.dp;

import java.util.Arrays;

/**
 * leetcode 5
 *
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 *
 * 对于一个子串而言，如果它是回文串，并且长度大于 2，那么将它首尾的两个字母去除之后，它仍然是个回文串。
 * 用 s[i:j] 表示字符串 s 的第 i 到 j 个字符组成的串，P(i,j) 表示该串是否为回文串，Si 表示第 i 个字符。
 *
 * 动态规划的状态转移方程：P(i, j) = P(i+1, j-1) AND Si = Sj
 *
 * 边界情况：
 * 1. 字符串长度小于 2，一定时回文串，直接返回该串
 * 2. 字符串长度等于 2，比较两个字符是否相等
 */
public class LongestPalindrome_lc5 {

    public static void main(String[] args) {

        String s = "1234321";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        char[] charArr = s.toCharArray();

        // 最长回文串的起始位置和长度
        int begin = 0;
        int maxLen = 1;

        // 记录从字符串每个索引开始，各个长度的子串是否为回文串
        boolean[][] dp = new boolean[len][len + 1];
        // 长度为 1 的子串一定是回文串，初始化为 true
        for (int i = 0; i < len; i++) {
            dp[i][1] = true;
        }

        // 根据状态转移方程，需要遍历的是子串可能的长度，从长度 2 开始遍历
        for (int i = 2; i <= len; i++) {
            // 计算从索引 j 开始，长度为 i 的子串是都为回文串
            for (int j = 0; j <= len - i; j++) {
                // 首尾两个字符不相等，子串一定不是回文串
                if (charArr[j] != charArr[j + i -1]) {
                    dp[j][i] = false;
                    continue;
                }
                // 首尾两个字符相等时，分两种情况
                if (i == 2) {
                    // 长度为 2 时，是回文串
                    dp[j][i] = true;
                } else {
                    // 长度大于 2 时，取决于去除首尾字符后的子串是否为回文串
                    dp[j][i] = dp[j + 1][i - 2];
                }
                // 记录当前最长回文子串的长度和起始位置
                if (dp[j][i] && i > maxLen) {
                    maxLen = i;
                    begin = j;
                }
            }
        }

        return new String(Arrays.copyOfRange(charArr, begin, begin + maxLen));
    }
}
