package com.gmail.vlad9880.example4;

public class App {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
        } catch(Error e) {
            System.err.print(" 2");
        } finally {
            System.err.println(" 3");
        }
        System.err.print(" 4");

        //есть исключение, но нет подходящего catch - out: 0 3 Exception...
    }
}
