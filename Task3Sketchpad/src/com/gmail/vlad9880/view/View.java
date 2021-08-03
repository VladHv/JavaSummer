package com.gmail.vlad9880.view;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.gmail.vlad9880.view.TextConstant.INPUT_STRING_DATA;
import static com.gmail.vlad9880.view.TextConstant.WRONG_INPUT_DATA;

/**
 * Created by Vlad Herasimov on 03.07.2021
 */

public class View {

    static String MESSAGES_BUNDLE_NAME = "messages";
    public static final ResourceBundle bundle =
            ResourceBundle.getBundle(
                    MESSAGES_BUNDLE_NAME,
                    new Locale("ua", "UA"));  //Ukr
                    //new Locale("en"));       //Eng


    public void printMessage(String message) {
        System.out.println(message);
    }

    public String concatenationString(String... message) {
        StringBuilder concatString = new StringBuilder();
        for (String v: message) {
            concatString = concatString.append(v);
        }
        return new String(concatString);
    }

    public void printStringInput(String message) {
        printMessage(concatenationString(
                bundle.getString(INPUT_STRING_DATA),
                bundle.getString(message)));
    }

    public void printWrongStringInput(String message) {
        printMessage(concatenationString(
                bundle.getString(WRONG_INPUT_DATA),
                bundle.getString(INPUT_STRING_DATA),
                bundle.getString(message)));
    }

}
