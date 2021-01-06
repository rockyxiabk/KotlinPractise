package com.rocky.practise.index;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 利用双指针来计算
 * <p>
 * 在有序数组中找出两个数，使得和为给定的数 S。如果有多对数字的和等于 S，输出两个数的乘积最小的。
 * <p>
 * 使用双指针，一个指针指向元素较小的值，一个指针指向元素较大的值。指向较小元素的指针从头向尾遍历，指向较大元素的指针从尾向头遍历。
 * 如果两个指针指向元素的和 sum == target，那么这两个元素即为所求。
 * 如果 sum > target，移动较大的元素，使 sum 变小一些；
 * 如果 sum < target，移动较小的元素，使 sum 变大一些。
 */
public class FindMinSum {
    public static ArrayList<Integer> findTwoMinNum(int[] num, int target) {
        if (null == num || num.length <= 0)
            return null;
        int i = 0, j = num.length - 1;
        while (i < j) {
            if (num[i] + num[j] > target) {
                j--;
            } else if (num[i] + num[j] < target) {
                i++;
            } else {
                return new ArrayList<>(Arrays.asList(num[i], num[j]));
            }
        }
        return null;
    }
}
