package com.rocky.practise.index;

import java.util.ArrayList;

/**
 * 输出所有和为 S 的连续正数序列。例如和为 100 的连续序列有：
 *
 * [9, 10, 11, 12, 13, 14, 15, 16]
 * [18, 19, 20, 21, 22]。
 */
public class SomeNum {

    public static ArrayList<ArrayList<Integer>> getSomeNum(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int start = 1, end = 2;
        int currentSum = 3;
        while (end < sum) {
            if (currentSum < sum) {
                end++;
                currentSum += end;
            } else if (currentSum > sum) {
                currentSum -= start;
                start++;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                    list.add(i);
                }
                result.add(list);
                currentSum-=start;
                start++;
                end++;
                currentSum+=end;
            }
        }
        return result;
    }
}
