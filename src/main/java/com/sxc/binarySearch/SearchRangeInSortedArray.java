package com.sxc.binarySearch;


/**
 * leetcode 34
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 解法：
 * 两次二分查找，分别找出 target 第一次出现和最后一次出现的位置
 */
public class SearchRangeInSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int[] range = searchRange(nums, 8);
        System.out.println(range[0] + " - " + range[1]);
    }

    public static int[] searchRange(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return new int[]{-1, -1};
        }
        // 找 target 第一次出现的位置
        int first = -1;
        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) / 2;
        while (left <= right) {
            if (nums[mid] == target) {
                first = mid; // 记录 first
                right = mid - 1; // 重要：找到 target 后，继续向左侧搜索，直到找到第一次出现的位置
                mid = (left + right) / 2;
            } else if (nums[mid] > target) {
                right = mid - 1;
                mid = (left + right) / 2;
            } else {
                left = mid + 1;
                mid = (left + right) / 2;
            }
        }

        // 找到 target 最后一次出现的位置
        int last = -1;
        left = 0;
        right = nums.length - 1;
        mid = (left + right) / 2;
        while (left <= right) {
            if (nums[mid] == target) {
                last = mid; // 记录 last
                left = mid + 1; // 重要：找到 target 后，继续向右侧搜索，直到找到最后一次出现的位置
                mid = (left + right) / 2;
            } else if (nums[mid] > target) {
                right = mid - 1;
                mid = (left + right) / 2;
            } else {
                left = mid + 1;
                mid = (left + right) / 2;
            }
        }

        return new int[] {first,last};
    }

}
