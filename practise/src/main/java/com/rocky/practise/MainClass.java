package com.rocky.practise;

import com.rocky.practise.algorithm.AlgorithmArrayUtil;

class MainClass {
    public static void main(String[] args) {
        int firstShowChar = AlgorithmArrayUtil.findFirstShowChar("120dfgkl094jgf23");
        System.out.println("字符串中出现1次的字符:" + firstShowChar);
        int[] numbers = {1, 4, 5, 6, 3, 2, 7, 4};
        int repeatNumber = AlgorithmArrayUtil.findRepeatNumber(numbers);
        System.out.println("数组中是否有重复的数字出现:" + (repeatNumber > 0 ? "yes->" + repeatNumber : "没有"));
        int[][] matrix = {{1, 2, 3, 4}, {6, 7, 8, 9}, {11, 12, 13, 14}, {16, 17, 18, 19}};
        System.out.println("二维数字中是否包含:" + AlgorithmArrayUtil.hasNumber(matrix, 45));
        System.out.println("顺时针打印矩阵：" + AlgorithmArrayUtil.printMatrix(matrix));
        System.out.println("输出所有和为 S 的连续正数序列。例如和为 100 的连续序列有：" + AlgorithmArrayUtil.findSumNumber(100));
        System.out.println("输出语句反转：" + AlgorithmArrayUtil.englishWordReserves("I am a student."));
    }
}
