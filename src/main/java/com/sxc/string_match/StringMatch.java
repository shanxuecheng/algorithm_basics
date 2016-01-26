package com.sxc.string_match;

public class StringMatch {

    public static void main(String[] args) {
        char[] str = "abcabcab".toCharArray();
        char[] pattern = "cabc".toCharArray();
        int start = match(str, pattern);
        System.out.println(start);
    }

    public static int match(char[] str, char[] pattern) {
        int n = str.length;
        int m = pattern.length;
        int i = 0;
        int j = 0;
        int start = -1;
        if (m > n) {
            return start;
        }
        int[] next = next(pattern);
        while (start < 0 && i < n) {
            if (str[i] == pattern[j]) {
                i++;
                j++;
            } else {
                j = next[j];
                if (j == -1) {
                    j = 0;
                    i++;
                }
            }
            if (j == m) {
                start = i - m;
            }
        }
        return start;
    }

    public static int[] next(char[] pattern) {
        int len = pattern.length;
        int[] next = new int[len];
        if (len >= 2) {
            next[0] = -1;
            next[1] = 0;
        } else if (len == 1) {
            next[0] = -1;
        }
        for (int i = 2; i < len; i++) {
            int j = next[i - 1];
            while (j >= 0 && pattern[i - 1] != pattern[j]) {
                j = next[j];
            }
            next[i] = j + 1;
        }
        return next;
    }
}
