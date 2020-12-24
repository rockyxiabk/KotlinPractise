package com.rocky.practise.algorithm;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;

public class AlgorithmArrayUtil {
    /**
     * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，
     * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * Input:
     * {2, 3, 1, 0, 2, 5}
     * Output:
     * 2
     */
    public static int findRepeatNumber(int[] arrays) {
        if (null == arrays || arrays.length == 0) {
            return -1;
        }
        int length = arrays.length - 1;
        int tempNumber = -1;
        for (int i = 0; i < length; i++) {
            while (arrays[i] != i) {
                if (arrays[i] == arrays[arrays[i]]) {
                    tempNumber = arrays[i];
                    return tempNumber;
                }
                swap(arrays, i, arrays[i]);
            }
        }
        return -1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中。
     * <p>
     * Consider the following matrix:
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * <p>
     * Given target = 5, return true.
     * Given target = 20, return false.
     */

    public static boolean hasNumber(int[][] matrix, int number) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int right = 0, top = cols - 1;
        while (right <= rows - 1 && top >= 0) {
            if (number == matrix[right][top]) {
                return true;
            } else if (number > matrix[right][top]) {
                right++;
            } else {
                top--;
            }
        }
        return false;
    }

    /**
     * 按顺时针的方向，从外到里打印矩阵的值。下图的矩阵
     * * [1,   2,  3, 4]
     * * [5,   6,  7, 8]
     * * [9,  10, 11, 12]
     * * [13, 14, 15, 16]
     * 打印结果为：1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10
     */
    public static String printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        int topLeft = 0, topRight = matrix.length - 1, boLeft = 0, boRight = matrix[0].length - 1;
        while (topLeft <= topRight && boLeft <= boRight) {
            //top
            for (int i = topLeft; i <= boRight; i++) {
                list.add(matrix[topLeft][i]);
            }
            //bottom
            for (int i = topLeft + 1; i <= topRight; i++) {
                list.add(matrix[i][topRight]);
            }
            if (topLeft != topRight)
                //right-left
                for (int i = boRight - 1; i >= boLeft; i--) {
                    list.add(matrix[topRight][i]);
                }
            if (boLeft != boRight)
                //bo-top
                for (int i = topRight - 1; i > topLeft; i--) {
                    list.add(matrix[i][topLeft]);
                }
            topLeft++;
            boLeft++;
            topRight--;
            boRight--;
        }
        String str = list.toString();
        return str;
    }

    /**
     * 在一个字符串中找到第一个只出现一次的字符，并返回它的位置。字符串只包含 ASCII 码字符。
     * <p>
     * Input: abacc
     * Output: b
     * 最直观的解法是使用 HashMap 对出现次数进行统计：字符做为 key，出现次数作为 value，遍历字符串每次都将 key 对应的 value 加 1。
     * 最后再遍历这个 HashMap 就可以找出出现次数为 1 的字符。
     * <p>
     * 考虑到要统计的字符范围有限，也可以使用整型数组代替 HashMap。ASCII 码只有 128 个字符，因此可以使用长度为 128 的整型数组来存储每个字符出现的次数。
     */
    public static int findFirstShowChar(String str) {
        int[] index = new int[128];
        for (int i = 0; i < str.length(); i++) {
            index[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (index[str.charAt(i)] == 1) {
                return index[str.charAt(i)];
            }
        }
        return -1;
    }

    /**
     * 输出所有和为 S 的连续正数序列。例如和为 100 的连续序列有：
     * <p>
     * [9, 10, 11, 12, 13, 14, 15, 16]
     * [18, 19, 20, 21, 22]。
     */
    public static ArrayList<ArrayList<Integer>> findSumNumber(int total) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int start = 1, end = 2, current_sum = 3;
        while (end < total) {
            if (current_sum < total) {
                end++;
                current_sum += end;
            } else if (current_sum > total) {
                current_sum -= start;
                start++;
            } else {
                ArrayList<Integer> curr = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                    curr.add(i);
                }
                res.add(curr);
                current_sum -= start;
                start++;
                end++;
                current_sum += end;
            }
        }
        return res;
    }

    /**
     * Input:
     * "I am a student."
     * <p>
     * Output:
     * "student. a am I"
     */
    public static String englishWordReserves(String str) {
        int n = str.length();
        char[] chars = str.toCharArray();
        int i = 0, j = 0;
        while (j <= n) {
            if (j == n || chars[j] == ' ') {
                reserve(chars, i, j - 1);
                i=j+1;
            }
            j++;
        }
        reserve(chars,0,n-1);
        return new String(chars);
    }

    private static void reserve(char[] chars, int i, int j) {
        while (i < j) {
            swapStr(chars, i++, j--);
        }
    }

    private static void swapStr(char[] chars, int i, int j) {
        char c = chars[i];
        chars[i] = chars[j];
        chars[j] = c;
    }
}
