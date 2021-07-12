package com.gmail.vlad9880.example2;

public class App {
    public static void main(String[] args) {
        try {
            System.err.print(" 0");

            if (true) {throw new RuntimeException();}
            System.err.print(" 1");

        } catch (RuntimeException e) {
            System.err.print(" 2.1");

            try {
                System.err.print(" 2.2");

                if (true) {throw new Error();}
                System.err.print(" 2.3");

            } catch (Throwable t) {
                System.err.print(" 2.4");

            }

            System.err.print(" 2.5");

        } catch (Error e) {
            System.err.print(" 3");

        }
        System.err.println(" 4");
    }
}
