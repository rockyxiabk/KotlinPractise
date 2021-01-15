package com.rocky.practise.dongtaiguihau;

import java.util.Arrays;

public class DemoAlgorithm {
    /**
     * Q:一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * <p>
     * A:可用利用动态规划来做
     * 当n=1 res=1
     * 当n=2 res=2
     * 依次类推上一次的可选方式
     */
    public static int jumpFloor(int n) {
        if (n <= 2) {
            return n;
        }
        int pre1 = 1, pre = 2;
        int res = 0;
        for (int i = 2; i < n; i++) {
            res = pre + pre1;
            pre = pre1;
            pre1 = res;
        }
        return res;
    }

    /**
     * Q:一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级... 它也可以跳上 n 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     */
    public static int jumpFloor1(int target) {
        int[] dp = new int[target];
        Arrays.fill(dp, 1);
        for (int i = 1; i < target; i++)
            for (int j = 0; j < i; j++)
                dp[i] += dp[j];
        return dp[target - 1];
    }

    /**
     * 连续子数组的最大和
     */
    public static int maxSum(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int greatestSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int val : nums) {
            sum = sum <= 0 ? val : sum + val;
            greatestSum = Math.max(greatestSum, sum);
        }
        return greatestSum;
    }

    /**
     * Q:礼物的最大价值 动态规划
     * A:在一个 m*n 的棋盘的每一个格都放有一个礼物，每个礼物都有一定价值（大于 0）。从左上角开始拿礼物，
     * 每次向右或向下移动一格，直到右下角结束。给定一个棋盘，求拿到礼物的最大价值。例如，对于如下棋盘
     * <p>
     * 1    10   3    8
     * 12   2    9    6
     * 5    7    4    11
     * 3    7    16   5
     * <p>
     * 礼物的最大价值为 1+12+5+7+7+16+5=53。
     */
    public static int getMaxGift(int[][] values) {
        if (null == values || values.length <= 0 || values[0].length <= 0) {
            return 0;
        }
        int x = values[0].length;
        int[] dp = new int[x];
        for (int[] value : values) {
            dp[0] += value[0];
            for (int i = 1; i < x; i++) {
                dp[i] = Math.max(dp[i], dp[i - 1]) + value[i];
            }
        }
        return dp[x - 1];
    }
}
