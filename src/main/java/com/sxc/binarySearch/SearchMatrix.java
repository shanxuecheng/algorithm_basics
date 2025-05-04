package com.sxc.binarySearch;

/**
 * leetcode 74
 *
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 *
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 */
public class SearchMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {1, 3, 5},
                {7, 9, 11},
                {13, 15, 17}
        };

        System.out.println(searchMatrix(matrix, 9));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (null == matrix) {
            return false;
        }
        int height = matrix.length;
        int width = matrix[0].length;
        if (height == 0 || width == 0) {
            return false;
        }

        int rowIndex= -1;

        // 二分查找，定位行
        int t = 0, b = height - 1;
        while (t <= b) {
            int mid = (t + b) / 2;
            int[] midRow = matrix[mid];
            if (target < midRow[0]) {
                b = mid - 1;
            } else if (target > midRow[width - 1]) {
                t = mid + 1;
            } else {
                rowIndex = mid;
                break;
            }
        }

        if (rowIndex < 0) {
            return false;
        }
        // 二分查找，定位列
        int[] targetRow = matrix[rowIndex];
        int l = 0, r = width - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            int midValue = targetRow[mid];
            if (target < midValue) {
                r = mid - 1;
            } else if (target > midValue) {
                l = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
