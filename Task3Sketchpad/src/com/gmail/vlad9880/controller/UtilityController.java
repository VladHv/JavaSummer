package com.gmail.vlad9880.controller;

import com.gmail.vlad9880.view.View;

import java.util.Scanner;

/**
 * Created by Vlad Herasimov on 06.07.2021
 */

public class UtilityController {

    private Scanner sc;
    private View view;

    public UtilityController(Scanner sc, View view) {
        this.sc = sc;
        this.view = view;
    }

    String inputStringValueWithScanner(String message, String regex) {
        String res;
        view.printStringInput(message);
        while (!(sc.hasNext() && (res = sc.next()).matches(regex))) {
            view.printWrongStringInput(message);
        }
        return res;
    }
}
