package com.sxc.dp;

/**
 * leetcode 300
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。(子序列不要求连续)
 *
 * 动态规划解法：
 *
 * 定义 dp[i] 为考虑前 i 个元素，以第 i 个数字结尾的最长上升子序列的长度，注意 nums[i] 必须被选取。
 * 我们从小到大计算 dp 数组的值，在计算 dp[i] 之前，我们已经计算出 dp[0…i−1] 的值，则状态转移方程为：
 *   dp[i] = max(dp[j]) + 1,其中 0≤j<i 且 num[j]<num[i]
 *
 * 即考虑往 dp[0…i−1] 中最长的上升子序列后面再加一个 nums[i]。
 * 由于 dp[j] 代表 nums[0…j] 中以 nums[j] 结尾的最长上升子序列，所以如果能从 dp[j] 这个状态转移过来，
 * 那么 nums[i] 必然要大于 nums[j]，才能将 nums[i] 放在 nums[j] 后面以形成更长的上升子序列。
 *
 * 最后，整个数组的最长上升子序列即所有 dp[i] 中的最大值。
 *
 */
public class LongestIncreasingSubsequence_lc300 {

    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int longestLen = 1;

        for (int i = 1; i < nums.length; i++) {
            int end = nums[i];
            // dp[i] 为以 nums[i] 结尾的最长上升子序列的长度，初始化为最小值 1，由内层循环遍历更新
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // 只有 nums[i] > nums[j] 时才需要更新
               if (end > nums[j]) {
                   dp[i] = Math.max(dp[i], dp[j] + 1);
               }
            }
            // 记录当前最长上升子序列
            longestLen = Math.max(longestLen, dp[i]);
        }
        return longestLen;
    }

}
