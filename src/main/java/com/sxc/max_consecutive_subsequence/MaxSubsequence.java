package com.sxc.max_consecutive_subsequence;

public class MaxSubsequence {

    public static class Subsequence {
        private int start = 0;
        private int length = 0;
        private int sum = 0;

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public void incrLength() {
            this.length++;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }
    }

    public static Subsequence getMaxSubsequence(int[] a) {
        Subsequence globalMax = new Subsequence();
        Subsequence suffixMax = new Subsequence();
        for (int i = 0; i < a.length; i++) {
            if (a[i] + suffixMax.getSum() > globalMax.getSum()) {
                suffixMax.setSum(a[i] + suffixMax.getSum());
                suffixMax.incrLength();
                globalMax.setStart(suffixMax.getStart());
                globalMax.setLength(suffixMax.getLength());
                globalMax.setSum(suffixMax.getSum());
            } else if (a[i] + suffixMax.getSum() > 0) {
                suffixMax.setSum(a[i] + suffixMax.getSum());
                suffixMax.incrLength();
            } else {
                suffixMax.setStart(i + 1);
                suffixMax.setLength(0);
                suffixMax.setSum(0);
            }
        }
        return globalMax;
    }

    public static void main(String[] args) {
//        int[] a = new int[] { -1, 1, -1, 2, -1, 3, -1 };
        int[] a = new int[] {5, -1, -2, -3, 4};
        Subsequence maxSubSequence = getMaxSubsequence(a);
        System.out.println(String.format("start:%d, length:%d, sum:%d", maxSubSequence.getStart(),
                maxSubSequence.getLength(), maxSubSequence.getSum()));
    }
}
