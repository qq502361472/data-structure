package com.hjrpc;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        System.out.println(map.get(1)==map.get(1));
    }
}
