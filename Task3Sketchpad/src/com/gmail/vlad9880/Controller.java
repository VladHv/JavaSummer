package com.gmail.vlad9880;

/**
 * Created by Vlad Herasimov on 03.07.2021
 */

import java.util.Scanner;

public class Controller {

    public static final String REGEX_MAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    public static final String REGEX_NAME = "[A-Za-z]+";
    public static final String REGEX_NICKNAME = "[A-Za-z0-9]+";
    public static final String REGEX_GROUP = "[WFMS]";

    View view;
    Record record;
    Sketchpad sketchpad;



    public Controller(View view, Record record, Sketchpad sketchpad) {
        this.view = view;
        this.record = record;
        this.sketchpad = sketchpad;
    }

    public void processUser() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 2; i++) {
            new Record();
            record.setName(inputName(sc));
            record.setSurname(inputSurname(sc));
            record.generateShortName();
            record.setNickname(inputNickname(sc));
            record.setGroup(chooseGroup(sc));
            record.setEmail(inputEmail(sc));
            sketchpad.addToSketchpad(record);
            view.printMessage(record.getName() + View.MEMBER_ADDED);
        }


        view.printMessage(sketchpad.getAllMembersInString());

    }


    //validators
    private String inputName(Scanner sc) {
        String res;
        view.printMessage(View.INPUT_NAME);
        while (!(res = sc.next()).matches(REGEX_NAME)) {
            view.printMessage(View.WRONG_INPUT + View.INPUT_NAME);
        }
        return res;
    }

    private String inputSurname(Scanner sc) {
        String res;
        view.printMessage(View.INPUT_SURNAME);
        while (!(res = sc.next()).matches(REGEX_NAME)) {
            view.printMessage(View.WRONG_INPUT + View.INPUT_SURNAME);
        }
        return res;
    }

    private String inputNickname(Scanner sc) {
        String res;
        view.printMessage(View.INPUT_NICKNAME);
        while (!(res = sc.next()).matches(REGEX_NICKNAME)) {
            view.printMessage(View.WRONG_INPUT + View.INPUT_NICKNAME);
        }
        return res;
    }

    private String chooseGroup(Scanner sc) {
        String res;
        view.printMessage(View.CHOOSE_GROUP);
        while (!(res = sc.next()).matches(REGEX_GROUP)) {
            view.printMessage(View.WRONG_INPUT + View.CHOOSE_GROUP);
        }
        return res;
    }

    private String inputEmail(Scanner sc) {
        String res;
        view.printMessage(View.INPUT_EMAIL);
        while (!(res = sc.next()).matches(REGEX_MAIL)) {
            view.printMessage(View.WRONG_INPUT + View.INPUT_EMAIL);
        }
        return res;
    }

}
