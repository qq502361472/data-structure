package com.hjrpc.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 贪心算法,每次都选择最优解,获取最终的最优解,结果不一定是整体的最优解,但是是非常接近最优解的
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        //初始化广播电台数据
        Map<String, Set<String>> broadcasts = initTestBroadcasts();

        //获取所有城市
        Set<String> allCities = getAllCities(broadcasts);

        Set<String> bestMatch = getBestMatch(broadcasts, allCities);

        System.out.println(bestMatch);
    }

    private static Set<String> getBestMatch(Map<String, Set<String>> broadcasts, Set<String> allCities) {
        //用来记录结果中的电台组合
        Set<String> result = new HashSet<>();
        while (!allCities.isEmpty()) {
            //匹配长度最长的key
            String maxKey = null;
            int maxSize = 0;
            //临时变量,用于取交集
            Set<String> tmp = new HashSet<>();

            //处理电台数据,获取交集的长度
            for (Map.Entry<String, Set<String>> entry : broadcasts.entrySet()) {
                //把剩余的所有城市添加到临时集合中
                tmp.addAll(allCities);
                //获取临时集合和当前元素的交集,并赋值给tmp
                tmp.retainAll(entry.getValue());
                //如果交集不为空,且交集的长度大于当前最长长度
                if (tmp.size() > 0 && tmp.size() > maxSize) {
                    maxKey = entry.getKey();
                    maxSize = tmp.size();
                }
            }

            if (maxKey != null) {
                result.add(maxKey);
                //这里需要移除maxKey对应的城市
                allCities.removeAll(broadcasts.get(maxKey));
            }
        }

        return result;
    }

    /**
     * 获取并集
     *
     * @param broadcasts
     * @return
     */
    private static Set<String> getAllCities(Map<String, Set<String>> broadcasts) {
        Set<String> result = new HashSet<>();
        for (Map.Entry<String, Set<String>> entry : broadcasts.entrySet()) {
            Set<String> value = entry.getValue();
            result.addAll(value);
        }
        return result;
    }

    /**
     * 初始化广播电台
     * K1 {北京,上海,天津}
     * K2 {广州,北京,深圳}
     * K3 {成都,上海,杭州}
     * K4 {上海,天津}
     * K5 {杭州,大连}
     */
    private static Map<String, Set<String>> initTestBroadcasts() {
        Map<String, Set<String>> result = new HashMap<>();
        Set<String> k1 = new HashSet<>();
        k1.add("北京");
        k1.add("上海");
        k1.add("天津");
        result.put("k1", k1);

        Set<String> k2 = new HashSet<>();
        k2.add("广州");
        k2.add("北京");
        k2.add("深圳");
        result.put("k2", k2);

        Set<String> k3 = new HashSet<>();
        k3.add("成都");
        k3.add("上海");
        k3.add("杭州");
        result.put("k3", k3);

        Set<String> k4 = new HashSet<>();
        k4.add("上海");
        k4.add("天津");
        result.put("k4", k4);

        Set<String> k5 = new HashSet<>();
        k5.add("杭州");
        k5.add("大连");
        result.put("k5", k5);

        return result;
    }
}
