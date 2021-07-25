package com.gmail.vlad9880;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int [] array = {3, 115, 0, -5, 3, 0, -23};

        List<Integer> list = Arrays.stream(array).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        double averageArray = Arrays.stream(array).average().getAsDouble();
        System.out.println("Average: " + averageArray);

        int min = Arrays.stream(array).min().getAsInt();
        int minIndex = IntStream.range(0, list.size()).boxed().
                min(Comparator.comparingInt(list::get)).get();
        System.out.println("Min number: " + min + " with index " + minIndex);

        long numberOfZero = Arrays.stream(array).
                filter(x -> x == 0).
                count();
        System.out.println("Zero(s) in array: " + numberOfZero);

        long numberOfPositive = Arrays.stream(array).
                filter(x -> x > 0).count();
        System.out.println("Amount of positive numbers: " + numberOfPositive);

        int[] doubledArray  = Arrays.stream(array).
                map(x -> x * 2).
                toArray();
        System.out.println(Arrays.toString(doubledArray));

    }
}
