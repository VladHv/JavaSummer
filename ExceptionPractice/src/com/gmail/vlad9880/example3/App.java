package com.gmail.vlad9880.example3;

public class App {
    public static void main(String[] args) {

        System.err.println(f());
    }
    public static int f() {
        try {
            return 0;
        } finally {
            return 1;
        }
    }
}
