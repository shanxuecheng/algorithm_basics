package com.sxc.dp;

/**
 * leetcode 53
 */
public class MaxSubArray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));

        int[] maxInfo = maxSubArrayInfo(new int[] {-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(String.format("start index: %d, end index: %d, sum: %d", maxInfo[0], maxInfo[1], maxInfo[2]));
    }

    public static int maxSubArray(int[] array) {
        int maxSum = array[0], maxSumEndedByCurrentIndex = array[0];

        for (int i = 1; i < array.length; i++) {
            int valueOfIndex = array[i];
            maxSumEndedByCurrentIndex = Math.max(maxSumEndedByCurrentIndex + valueOfIndex, valueOfIndex);
            maxSum = Math.max(maxSumEndedByCurrentIndex, maxSum);
        }
        return maxSum;
    }

    // return array info: start index, end index, sum
    public static int[] maxSubArrayInfo(int[] array) {
        int maxSum = array[0];
        int[] maxSumRange = new int[] {0, 0};

        int maxSumEndedByCurrentIndex = array[0];
        int[] maxSumRangeEndedByCurrentIndex = new int[] {0, 0};
        for (int i = 1; i < array.length; i++) {
            int valueOfIndex = array[i];
            int tmpMaxForCheck = maxSumEndedByCurrentIndex + valueOfIndex;
            if (tmpMaxForCheck > valueOfIndex) {
                maxSumEndedByCurrentIndex = tmpMaxForCheck;
                maxSumRangeEndedByCurrentIndex[1] = i;
            } else {
                maxSumEndedByCurrentIndex = valueOfIndex;
                maxSumRangeEndedByCurrentIndex[0] = i;
                maxSumRangeEndedByCurrentIndex[1] = i;
            }

            if (maxSumEndedByCurrentIndex > maxSum) {
                maxSum = maxSumEndedByCurrentIndex;
                maxSumRange[0] = maxSumRangeEndedByCurrentIndex[0];
                maxSumRange[1] = maxSumRangeEndedByCurrentIndex[1];
            }
        }
        return new int[] {maxSumRange[0], maxSumRange[1], maxSum};
    }
}
