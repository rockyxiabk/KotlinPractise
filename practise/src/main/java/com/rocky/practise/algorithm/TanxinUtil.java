package com.rocky.practise.algorithm;

class TanxinUtil {

    /**
     * Q:股票的最大利润
     * <p>
     * A:使用贪心策略，假设第 i 轮进行卖出操作，买入操作价格应该在 i 之前并且价格最低。因此在遍历数组时记录当前最低的买入价格，
     * 并且尝试将每个位置都作为卖出价格，取收益最大的即可。
     */
    public static int maxProfit(int[] prices) {
        if (null == prices || prices.length <= 0) {
            return 0;
        }
        int maxProfit = 0;
        int soFarMin = prices[0];
        for (int i = 1; i < prices.length; i++) {
            soFarMin = Math.min(soFarMin, prices[i]);//最小值
            maxProfit = Math.max(maxProfit, prices[i] - soFarMin);//差值最大值
        }
        return maxProfit;
    }
}
