package com.rocky.practise.stack;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class HeapUtil {

    /**
     * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
     * 例如，如果输入数组 {2, 3, 4, 2, 6, 2, 5, 1} 及滑动窗口的大小 3，那么一共存在 6 个滑动窗口，他们的最大值分别为 {4, 4, 6, 6, 6, 5}。
     */
    public static ArrayList<Integer> getMaxList(int[] num, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        if (size > num.length || size < 1) {
            return result;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < size; i++)
            heap.add(num[i]);
        result.add(heap.peek());
        for (int i = 0, j = i + size; j < num.length; i++, j++) {
            heap.remove(num[i]);
            heap.add(num[j]);
            result.add(heap.peek());
        }
        return result;
    }
}
