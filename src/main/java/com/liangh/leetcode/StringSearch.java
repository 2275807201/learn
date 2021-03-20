package com.liangh.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/03/20 17:00
 */
public class StringSearch {

    public static void main(String[] args) {

        String str = "abcabcbb123456789";
        StringSearch s = new StringSearch();
        int i = s.lengthOfLongestSubstring(str);
        System.out.println(i);
    }

    public int lengthOfLongestSubstring(String s) {

        int max = 0;

        int length = s.length();
        for (int i = 0; i < length; i++) {
            int len = sequenceLength(s.substring(i));
            if (len > max){
                max = len;
            }
        }

        return max;
    }

    /**
     * 查找连续不同最大长度
     * @param s
     * @return
     */
    private int sequenceLength(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            boolean add = set.add(s.charAt(i));
            if(!add){
                break;
            }
        }
        return set.size();
    }

}
