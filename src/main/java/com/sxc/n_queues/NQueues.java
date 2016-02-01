package com.sxc.n_queues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueues {

    public static final int N = 8;

    private static boolean[][] matrix = new boolean[N + 1][N + 1];

    private static int[] result = new int[N + 1];

    private static List<int[]> allResults = new ArrayList<int[]>();

    public static void main(String[] args) {
        trail(1);
        System.out.print(allResults.size());
    }

    private static boolean isLegal(int i, int j) {
        for (int m = 1; m <= i - 1; m++) {
            int n = result[m];
            if (n == j || Math.abs(i - m) == Math.abs(j - n)) {
                return false;
            }
        }
        return true;
    }

    public static void trail(int i) {
        if (i > N) {
            allResults.add(Arrays.copyOfRange(result, 1, N));
        } else {
            for (int j = 1; j <= N; j++) {
                matrix[i][j] = true;
                if (isLegal(i, j)) {
                    result[i] = j;
                    trail(i + 1);
                }
                matrix[i][j] = false;
            }
        }
    }
}