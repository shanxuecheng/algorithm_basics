package com.sxc.selection;

public class Selection {

    public static void main(String[] args) {
        int[] s = new int[]{3, 5, -2, 4, 1};
        System.out.println(selection(s, 2));
    }

    public static int selection(int[] s, int k) {
        if (k < 1 || k > s.length) {
            throw new RuntimeException("error");
        }
        return select(s, 0, s.length - 1, k);
    }

    private static int select(int[] s, int left, int right, int k) {
        if (left == right) {
            return s[left];
        }
        int mid = partition(s, left, right);
        if (mid - left + 1 >= k) {
            return select(s, left, mid, k);
        } else {
            return select(s, mid + 1, right, k - (mid - left + 1));
        }
    }

    private static int partition(int[] s, int left, int right) {
        if (left >= right) {
            return left;
        }
        int i = left;
        int j = right;
        int p = s[i];
        while (i < j) {
            while (i <= right && s[i] <= p) {
                i++;
            }
            while (j >= left && s[j] > p) {
                j--;
            }
            if (i < j) {
                int tmp = s[i];
                s[i] = s[j];
                s[j] = tmp;
            }
        }
        int mid = j;
        s[left] = s[mid];
        s[mid] = p;
        return mid;
    }
}