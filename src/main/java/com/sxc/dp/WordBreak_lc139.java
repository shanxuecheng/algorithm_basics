package com.sxc.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * leetcode 139
 *
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 * 动态规划解法：
 * 定义 dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i−1] 是否能被空格拆分成若干个字典中出现的单词。
 *
 * 枚举 s[0..i−1] 中任意分割点 j，看分割后的两部分字符串 s[0..j−1]（记为 s1） 和 s[j..i−1]（记为 s2） 是否都合法。
 * 如果两部分字符串都合法，那么按照定义 s[0..i−1] 也合法。
 *
 * 由于计算到 dp[i] 时我们已经计算出了 dp[0..i−1] 的值，因此字符串 s1 是否合法可以直接由 dp[j] 得知，剩下的我们只需要看 s2
 * 是否合法即可，因此我们可以得出如下转移方程：
 *   dp[i] = dp[j] && check(s[j..i−1])
 *   其中 check(s[j..i−1]) 表示子串 s[j..i−1] 是否出现在字典中。
 *   在 0 - i 的范围内枚举 j，一旦得出 dp[i] 为true，就停止枚举。
 *
 *   如果 j = 0 时 check(s[j..i−1]) 为 true，即 s[0..i−1] 出现在字典中。
 *   所以应初始化 dp[0] 为 true，使 dp[0] && check(s[0..i−1]) 仍然成立。可以理解为空串也合法。
 *
 */
public class WordBreak_lc139 {

    public static void main(String[] args) {
        List<String> wordDict = Arrays.asList("leet", "code");
        String s = "leetcode";
        System.out.println(wordBreak(s,wordDict));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        int length = s.length();
        if (length == 0) {
            return true;
        }

        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        Set<String> wordSet = new HashSet<>(wordDict);

        // i 表示前 i 个字符，从 1 开始遍历
        for (int i = 1; i <= length; i++) {
            // j 表示分割点，即索引，从 0 开始遍历
            for (int j = 0; j < i; j++) {
                // j = 0 时，即检查整个前 i 个字符组成的子串是否在字典中
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }

        }
        return dp[length];
    }
}
