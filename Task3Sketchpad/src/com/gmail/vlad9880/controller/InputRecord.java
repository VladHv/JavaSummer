package com.gmail.vlad9880.controller;

import com.gmail.vlad9880.view.View;

import java.util.Scanner;

import static com.gmail.vlad9880.controller.RegexContainer.*;
import static com.gmail.vlad9880.view.TextConstant.*;

/**
 * Created by Vlad Herasimov on 03.07.2021
 */

public class InputRecord {

    private View view;
    private Scanner sc;

    private String firstName;
    private String login;

    public InputRecord(View view, Scanner sc) {
        this.view = view;
        this.sc = sc;
    }

    public void inputRecord() {
        UtilityController utilityController = new UtilityController(sc, view);
        String regex = (String.valueOf(View.bundle.getLocale()).equals("ua"))
                ? REGEX_NAME_UKR : REGEX_NAME_LAT;

        this.firstName =
                utilityController.inputStringValueWithScanner(FIRST_NAME, regex);

        this.login =
                utilityController.inputStringValueWithScanner(LOGIN_DATA, REGEX_LOGIN);
    }
}
