package com.gmail.vlad9880;

/**
 * Created by Vlad Herasimov 24.06.2021
 */

public class View {

    public static final String INPUT_HELLO = "Input word \"Hello\" :";
    public static final String INPUT_WORLD = "Input word \"world!\" :";
    public static final String WRONG_INPUT = "Wrong! Try again. ";
    public static final String FINAL_MESSAGE = "It works!!! ";

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printFinalMessage(String message, String finalMessage){
        System.out.println(message + finalMessage);
    }

}
