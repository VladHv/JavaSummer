package com.gmail.vlad9880;

/**
 * Created by Vlad Herasimov 24.06.2021
 */

import java.util.Scanner;

public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model  = model;
        this.view = view;
    }

    public void processUser() {
        Scanner scanner = new Scanner(System.in);
        model.setWordHello(inputWordHello(scanner));
        model.setWordWorld(inputWordWorld(scanner));
        view.printFinalMessage(View.FINAL_MESSAGE, model.getFinalMessage());
    }

    public String inputWordHello(Scanner scanner){
        view.printMessage(View.INPUT_HELLO);
        while (! scanner.hasNext("Hello")) {
            view.printMessage(View.WRONG_INPUT + View.INPUT_HELLO);
            scanner.next();
        }
        return scanner.next();
    }

    public String inputWordWorld(Scanner scanner){
        view.printMessage(View.INPUT_WORLD);
        while (! scanner.hasNext("world!")) {
            view.printMessage(View.WRONG_INPUT + View.INPUT_WORLD);
            scanner.next();
        }
        return scanner.next();
    }

}
