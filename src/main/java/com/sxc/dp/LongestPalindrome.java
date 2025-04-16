package com.sxc.dp;

import java.util.Arrays;

/**
 * leetcode 5
 */
public class LongestPalindrome {

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
        int begin = 0;
        int maxLen = 1;
        boolean[][] dp = new boolean[len][len + 1];
        for (int i = 0; i < len; i++) {
            dp[i][1] = true;
        }

        for (int i = 2; i <= len; i++) {
            for (int j = 0; j <= len - i; j++) {
                if (charArr[j] != charArr[j + i -1]) {
                    dp[j][i] = false;
                    continue;
                }

                if (i == 2) {
                    dp[j][i] = true;
                } else {
                    dp[j][i] = dp[j + 1][i - 2];
                }
                if (dp[j][i] && i > maxLen) {
                    maxLen = i;
                    begin = j;
                }
            }
        }

        return new String(Arrays.copyOfRange(charArr, begin, begin + maxLen));
    }
}
