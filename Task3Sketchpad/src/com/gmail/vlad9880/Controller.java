package com.gmail.vlad9880;

/**
 * Created by Vlad Herasimov on 03.07.2021
 */

import java.util.Scanner;

public class Controller {

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

        record.setName(inputName(sc));
        record.setSurname(inputSurname(sc));
        record.setNickname(inputNickname(sc));
        sketchpad.addToSketchpad(record);
        view.printMessage(record.getName() + View.MEMBER_ADDED);

        view.printMessage(sketchpad.getAllMembersInString());

    }


    //validators
    private String inputName(Scanner sc) {
        String res;
        view.printMessage(View.INPUT_NAME);
        while (!(res = sc.next()).matches("[A-Za-z]+")) {
            view.printMessage(View.WRONG_INPUT + View.INPUT_NAME);
        }
        return res;
    }

    private String inputSurname(Scanner sc) {
        String res;
        view.printMessage(View.INPUT_SURNAME);
        while (!(res = sc.next()).matches("[A-Za-z]+")) {
            view.printMessage(View.WRONG_INPUT + View.INPUT_SURNAME);
        }
        return res;
    }

    private String inputNickname(Scanner sc) {
        String res;
        view.printMessage(View.INPUT_NICKNAME);
        while (!(res = sc.next()).matches("[A-Za-z0-9]+")) {
            view.printMessage(View.WRONG_INPUT + View.INPUT_NICKNAME);
        }
        return res;
    }
}
