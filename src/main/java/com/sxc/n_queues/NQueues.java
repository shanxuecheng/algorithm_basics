package com.sxc.n_queues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueues {

    public static final int N = 8;

    private static int[] result = new int[N + 1];

    private static List<int[]> allResults = new ArrayList<int[]>();

    public static void main(String[] args) {
        trail(1);
        System.out.println(allResults.size());
        System.out.println("all results:");
        for (int[] result : allResults) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < N; i++) {
                stringBuilder.append(result[i]);
                if (i < N - 1) {
                    stringBuilder.append(", ");
                }
            }
            System.out.println(stringBuilder);
        }
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
            allResults.add(Arrays.copyOfRange(result, 1, N + 1));
        } else {
            for (int j = 1; j <= N; j++) {
                if (isLegal(i, j)) {
                    result[i] = j;
                    trail(i + 1);
                }
            }
        }
    }
}