package com.gmail.vlad9880;

/**
 * Created by Vlad Herasimov 24.06.2021
 */

public class Main {
    public static void main(String[] args) {

        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(model, view);
        controller.processUser();

    }
}
