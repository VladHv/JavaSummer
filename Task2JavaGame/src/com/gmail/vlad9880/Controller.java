package com.gmail.vlad9880;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.Scanner;

public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processUser() {
        Scanner sc = new Scanner(System.in);
        model.setBorder(GlobalConstants.MIN_NUMBER,
                        GlobalConstants.MAX_NUMBER);
        model.setUnknownNumber();

        while(model.checkNumber(inputIntValueWithScannerAndDiapason(sc)));

        view.printMessage(View.GUESSED + model.getUnknownNumber());
        view.printMessage(View.USER_NUMBERS + model.getAllUserNumbers());
        view.printMessage(View.ATTEMPTS + model.getAttempts());
        }


    //validator
    private int inputIntValueWithScannerAndDiapason (Scanner sc) {
        int res = 0;
        view.printMessage(getInputIntValue());
        while (true) {
            while (! sc.hasNextInt()) {
                view.printMessage(View.WRONG_INPUT + getInputIntValue());
                sc.next();
            }
            if ((res = sc.nextInt()) <= model.getMinNumber() || res >= model.getMaxNumber()) {
                view.printMessage(View.WRONG_INPUT + getInputIntValue());
                continue;
            } break;
        }
        return res;
    }

    public String getInputIntValue(){
        return view.concatenationString(View.GUESS_NUMBER,
                                        String.valueOf(model.getMinNumber()),
                                        View.TO,
                                        String.valueOf(model.getMaxNumber()));
    }

}
