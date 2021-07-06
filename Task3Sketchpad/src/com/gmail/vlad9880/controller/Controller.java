package com.gmail.vlad9880.controller;

/**
 * Created by Vlad Herasimov on 03.07.2021
 */

import com.gmail.vlad9880.model.Model;
import com.gmail.vlad9880.view.View;

import java.util.Scanner;

public class Controller {

    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processUser() {
        Scanner sc = new Scanner(System.in);
        InputRecord inputRecord = new InputRecord(view, sc);
        inputRecord.inputRecord();

    }

}
