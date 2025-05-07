package com.sxc.binarySearch;

/**
 * leetcode 33
 *
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你旋转后的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * ----------------------------------------------------
 * 对于有序数组，可以使用二分查找的方法查找元素。
 *
 * 但是这道题中，数组本身不是有序的，进行旋转后只保证了数组的局部是有序的，这还能进行二分查找吗？答案是可以的。
 *
 * 可以发现的是，我们将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。
 * 拿示例来看，我们从 6 这个位置分开以后数组变成了 [4, 5, 6] 和 [7, 0, 1, 2] 两个部分，其中左边 [4, 5, 6] 这个部分的数组是有序的，其他也是如此。
 *
 * 解题的关键在于认识到以下三点：
 * 1. 只有在顺序区间内才可以通过区间两端的数值判断target是否在其中。
 * 2. 每次二分都会至少存在一个顺序区间
 * 3. 判断顺序区间还是乱序区间，只需要对比 left 和 right 是否是顺序对即可，left <= right，顺序区间，否则乱序区间。
 */
public class SearchRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 1, 3};
        System.out.println(search(nums, 0));
    }

    public static int search(int[] nums, int target) {
        if (null == nums || nums.length == 0) {
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            int midValue = nums[mid];
            if (target == midValue) {
                return mid;
            }
            int leftValue = nums[l];
            int rightValue = nums[r];
            // 注意边界条件，区间长度可能为 1，要用 <=
            if (leftValue <= midValue) {
                // 左侧有序，注意边界条件，与 leftValue 比较时要用 >=
                if (target >= leftValue && target < midValue) {
                    // target 在左侧，搜索左侧
                    r = mid - 1;
                } else {
                    // 否则搜索右侧
                    l = mid + 1;
                }
            } else {
                // 右侧有序，注意边界条件，与 rightValue 比较时要用 <=
                if (target > midValue && target <= rightValue) {
                    // target 在右侧，搜索右侧
                    l = mid + 1;
                } else {
                    // 否则搜索左侧
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

}
