package com.gmail.vlad9880.immutableArrayList;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> list = new ImmutableArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Tree");

        System.out.println(list.size());
        System.out.println("Standard: " + list);


        try {
            list.remove(1);
        } catch (UnsupportedOperationException ex) {
            System.err.println("You can't remove or update!");
        }

        System.out.println("After removing: " + list);



    }
}
