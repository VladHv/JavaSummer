package com.gmail.vlad9880;

public class View {

    public static final String GUESS_NUMBER = "Guess the number from ";
    public static final String WRONG_INPUT = "Unacceptable value!! Try again!\n";
    public static final String GUESSED = "Hooray! My hidden number is ";
    public static final String ATTEMPTS = "Amount of attempts: ";
    public static final String USER_NUMBERS = "Your number(s) - ";
    public static final String TO = " to ";

    public void printMessage (String message) {
        System.out.println(message);
    }

    public String concatenationString (String... message){
        StringBuilder concatString = new StringBuilder();
        for (String v : message){
            concatString = concatString.append(v);
        }
        return new String(concatString);
    }



}
