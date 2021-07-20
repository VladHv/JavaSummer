package com.gmail.vlad9880.mapPractice;

import java.util.*;
import java.util.stream.Collectors;

public class CalcEachElement {
    public static void main(String[] args) {
        List<Integer> arrList = new ArrayList<>();
        int[] arr = {4, 5, -6, 4, 5, 3, 4, 2, 4, 5, 7};

        for (int i : arr) {
            arrList.add(i);
        }

        System.out.println(arrList);

//        Collections.sort(arrList);
        System.out.println(arrList);

        Map<Integer, Integer> map = arrList.stream().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));

        map.forEach((k, v) -> System.out.println(k + " - " + v));


    }
}
