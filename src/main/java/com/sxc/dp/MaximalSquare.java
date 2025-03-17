package com.sxc.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode 221
 *
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * 用 dp(i,j) 表示以 (i,j) 为右下角，且只包含 1 的正方形的边长最大值。
 * 从第一行开始遍历，考虑如何利用已经计算出的 dp(i - x, j - y) 推断出 dp(i, j)。
 *
 * 对于每个位置 (i,j)，检查在矩阵中该位置的值：
 * 1. 如果该位置的值是 0，则 dp(i,j)=0，因为当前位置不可能在由 1 组成的正方形中；
 * 2. 如果该位置的值是 1，则 dp(i,j) 的值由其上方、左方和左上方的三个相邻位置的 dp 值决定。具体而言，当前位置的元素值等于三个相邻位置的元素中的最小值加 1，状态转移方程如下：
 *      dp(i,j) = min( dp(i−1,j), dp(i−1,j−1) ,dp(i,j−1) ) + 1
 */
public class MaximalSquare {

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                 {'1','0','1','0','0'},
                 {'1','0','1','1','1'},
                 {'1','1','1','1','1'},
                 {'1','0','1','1','1'}
        };
        System.out.println(maximalSquare(matrix));
    }


    public static int maximalSquare(char[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int height = matrix.length;
        int width = matrix[0].length;
        if (height == 0 || width == 0) {
            return 0;
        }

        int[][] dp = new int[height][width];

        int maxLen = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    }
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }

        return maxLen * maxLen;
    }
}
