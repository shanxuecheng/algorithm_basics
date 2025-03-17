package com.sxc.dp;

/**
 * leetcode 53
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 假设数组长度为 n, f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，那么很显然我们要求的答案就是：max( f(0), f(1) ... f(n-1) )
 * 因此我们只需要求出每个位置的 f(i)，然后返回 f 数组中的最大值即可。
 *
 * 那么我们如何求 f(i) 呢？我们可以考虑 nums[i] 单独成为一段，还是加入 f(i−1) 对应的那一段，这取决于 nums[i] 和 f(i−1)+nums[i] 的大小。
 * 于是可以写出这样的动态规划转移方程：
 * f(i)=max{ f(i−1)+nums[i], nums[i] }
 *
 */
public class MaxSubArray_lc53 {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));

        int[] maxInfo = maxSubArrayInfo(new int[] {-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(String.format("start index: %d, end index: %d, sum: %d", maxInfo[0], maxInfo[1], maxInfo[2]));
    }

    // 仅返回最大的子数组和
    public static int maxSubArray(int[] array) {
        // maxSum : 全局的「连续子数组的最大和」
        // maxSumEndedByCurrentIndex：以当前 index 结尾的「连续子数组的最大和」
        // 从索引 0 开始考察，因 f(0) = array(0)，所以 maxSum 和 maxSumEndedByCurrentIndex 都初始化为 array[0]
        int maxSum = array[0], maxSumEndedByCurrentIndex = array[0];

        // 遍历索引 1 至 array.length -1，根据 f(i)=max{ f(i−1)+nums[i], nums[i] } 计算每一个 f(i)，同时记录最大的 f(i)
        for (int i = 1; i < array.length; i++) {
            int valueOfIndex = array[i];
            maxSumEndedByCurrentIndex = Math.max(maxSumEndedByCurrentIndex + valueOfIndex, valueOfIndex);
            maxSum = Math.max(maxSumEndedByCurrentIndex, maxSum);
        }
        return maxSum;
    }

    // 返回最大的子数组和，以及对应的子数组的位置
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
