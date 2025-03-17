package com.sxc.dp;

import java.util.Arrays;

public class CoinChange {

    public static void main(String[] args) {
        int count = coinChange(new int[]{2}, 3);
        System.out.println(count);
    }

    public static int coinChange(int[] coinValues, int amount) {
        int max = amount + 1;
        int[] resultRecorder = new int[amount + 1];
        Arrays.fill(resultRecorder, max);
        return doChange(coinValues, amount, resultRecorder);
    }

    private static int doChange(int[] coinValues, int amount, int[] resultRecorder) {
        if (amount == 0) {
            return 0;
        }
        int minCount = Integer.MAX_VALUE;
        boolean lost = true;
        for (int i = 0; i < coinValues.length; i++) {
            int startValue = coinValues[i];
            int leftAmount = amount - startValue;
            if (leftAmount == 0) {
                return 1;
            } else if (leftAmount < 0) {
                continue;
            }

            int leftCount = resultRecorder[leftAmount];
            if (leftCount > leftAmount) {
                leftCount = doChange(coinValues, leftAmount, resultRecorder);
                resultRecorder[leftAmount] = leftCount;
            }
            if (leftCount == -1) {
                continue;
            }

            int count = 1 + leftCount;
            if (count < minCount) {
                minCount = count;
                lost = false;
            }
        }
        if (lost) {
            return -1;
        }
        return minCount;
    }
}
