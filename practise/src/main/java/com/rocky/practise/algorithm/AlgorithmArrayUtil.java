package com.rocky.practise.algorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;

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
     * 将一个字符串中的空格替换成 "%20"。
     * <p>
     * Input:
     * "A B"
     * <p>
     * Output:
     * "A%20B"
     */
    public static String replaceSpace(StringBuffer str) {
        int P1 = str.length() - 1;
        for (int i = 0; i <= P1; i++)
            if (str.charAt(i) == ' ')
                str.append("  ");

        int P2 = str.length() - 1;
        while (P1 >= 0 && P2 > P1) {
            char c = str.charAt(P1--);
            if (c == ' ') {
                str.setCharAt(P2--, '0');
                str.setCharAt(P2--, '2');
                str.setCharAt(P2--, '%');
            } else {
                str.setCharAt(P2--, c);
            }
        }
        return str.toString();
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

    public static int findFirstShowChar2(String str) {
        BitSet bs1 = new BitSet(128);
        BitSet bs2 = new BitSet(128);
        for (char c : str.toCharArray()) {
            if (!bs1.get(c) && !bs2.get(c))
                bs1.set(c);     // 0 0 -> 0 1
            else if (bs1.get(c) && !bs2.get(c))
                bs2.set(c);     // 0 1 -> 1 1
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (bs1.get(c) && !bs2.get(c))  // 0 1
                return i;
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
     * <p>
     * A：先翻转每个单词，再翻转整个字符串。
     * <p>
     * 题目应该有一个隐含条件，就是不能用额外的空间。虽然 Java 的题目输入参数为 String 类型，
     * 需要先创建一个字符数组使得空间复杂度为 O(N)，但是正确的参数类型应该和原书一样，为字符数组，并且只能使用该字符数组的空间。
     * 任何使用了额外空间的解法在面试时都会大打折扣，包括递归解法。
     */
    public static String englishWordReserves(String str) {
        int n = str.length();
        char[] chars = str.toCharArray();
        int i = 0, j = 0;
        while (j <= n) {
            if (j == n || chars[j] == ' ') {
                reserve(chars, i, j - 1);
                i = j + 1;
            }
            j++;
        }
        reserve(chars, 0, n - 1);
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

    /**
     * 将字符串 S 从第 K 位置分隔成两个子字符串，并交换这两个子字符串的位置。
     * <p>
     * Input:
     * S="abcXYZdef"
     * K=3
     * <p>
     * Output:
     * "XYZdefabc"
     */
    public static String reserveStr(String str, int len) {
        if (null == str || str.length() <= len) {
            return str;
        }
        char[] chars = str.toCharArray();
        reserve(chars, 0, len - 1);
        reserve(chars, len, chars.length - 1);
        reserve(chars, 0, chars.length - 1);
        return new String(chars);
    }

    /**
     * 0:
     * 1: 2: 3: 4: 5:
     * 6: 7: 8 9:	 10:
     * <p>
     * 14: 15:
     * 16: 17: 18: 19: 20:
     * 21: 22: 23: 24: 25:
     * 26: 27: 28: 29: 30:
     * 31: 32:  33:! 34:" 35:#
     * 36:$ 37:% 38:& 39:' 40:(
     * 41:) 42:* 43:+ 44:, 45:-
     * 46:. 47:/ 48:0 49:1 50:2
     * 51:3 52:4 53:5 54:6 55:7
     * 56:8 57:9 58:: 59:; 60:<
     * 61:= 62:> 63:? 64:@ 65:A
     * 66:B 67:C 68:D 69:E 70:F
     * 71:G 72:H 73:I 74:J 75:K
     * 76:L 77:M 78:N 79:O 80:P
     * 81:Q 82:R 83:S 84:T 85:U
     * 86:V 87:W 88:X 89:Y 90:Z
     * 91:[ 92:\ 93:] 94:^ 95:_
     * 96:` 97:a 98:b 99:c 100:d
     * 101:e 102:f 103:g 104:h 105:i
     * 106:j 107:k 108:l 109:m 110:n
     * 111:o 112:p 113:q 114:r 115:s
     * 116:t 117:u 118:v 119:w 120:x
     * 121:y 122:z 123:{ 124:| 125:}
     * 126:~ 127:
     */
    public static void getChar() {
        for (int i = 0; i < 128; i++) {
            System.out.print(" " + i + ":" + ((char) i));
            if (i % 5 == 0) {
                System.out.println();
            }
        }
    }

    /**
     * |=
     关于 |= 运算符：|= 运算符和 += 这一类的运算符一样，拆解开就是 a = a | b；
     代码如下：
     复制代码
     public static strictfp void main(String[] args) {
     int a = 5; // 0000 0101
     int b = 3; // 0000 0011
     a |= b;   // 0000 00111
     System.out.println(a);
     }
     复制代码
     具体规则为：两个二进制对应位为0时该位为0，否则为1。拿5的二进制 0000 0101 和 3的二进制 0000 0011 进行|运算，后三位的的对应位都不是同时等于0，所以最终结果为 0000 0111 也就是7的二进制。
     &=
     &= 和 |= 基本是一样的，只不过对于比较同位方式不同。
     代码如下：
     复制代码
     public static strictfp void main(String[] args) {
     int a = 5; // 0000 0101
     int b = 3; // 0000 0011
     a &= b;    // 0000 0001
     System.out.println(a);
     }
     复制代码
     具体规则为：两个二进制对应位都为1时，结果为1，否则结果为都0。拿5的二进制 0000 0101 和 3的二进制 0000 0011 进行&运算，只有最后一位都为1，则最终结果为 0000 0001 也就是1的二进制。
     ^=
     运算规则还是同上。
     代码如下：
     public static strictfp void main(String[] args) {
     int a = 5; // 0000 0101
     int b = 3; // 0000 0011
     a ^= b;    // 0000 0110
     System.out.println(a);
     }
     具体规则为：两个二进制对应位相同时，结果为0，否则结果为1。拿5的二进制 0000 0101 和 3的二进制 0000 0011 进行^运算，1-5位对应位都是0所以1-5位都为0，第8位都为1所以第8位也为0，其他的对应位都不相等所以为1，则最终结果为 0000 0110 也就是6的二进制。
     */

    /**
     * 输入一个整数，输出该数二进制表示中 1 的个数。
     *
     * @param number 整数
     * @return 1的个数
     */
    public static int sumOfIntOfOne(int number) {
        System.out.println(number + ":--二进制--" + new BigInteger(number + "").toString(2));
        int sum = 0;
        while (number != 0) {
            sum++;
            number &= (number - 1);
        }
        return sum;
    }
}
