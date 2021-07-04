package com.gmail.vlad9880;

/**
 * Created by Vlad Herasimov on 03.07.2021
 */

public class View {

    public static final String INPUT_NAME = "Name: ";
    public static final String INPUT_SURNAME = "Surname: ";
    public static final String INPUT_NICKNAME = "Nickname: ";
    public static final String WRONG_INPUT = "Unacceptable value. Try again!\n";
    public static final String MEMBER_ADDED = " added to your sketchpad!\n";
    public static final String CHOOSE_GROUP = "Choose group ( \"W\" for Work, \"F\" for Family, \"M\" for Mates, \"S\" for Services ):";
    public static final String INPUT_EMAIL = "Email: ";


    public void printMessage(String message) {
        System.out.println(message);
    }

}
