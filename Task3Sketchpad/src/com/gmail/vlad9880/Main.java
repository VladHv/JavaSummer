package com.gmail.vlad9880;

import com.gmail.vlad9880.controller.Controller;
import com.gmail.vlad9880.model.Model;
import com.gmail.vlad9880.view.View;

/**
 * Created by Vlad Herasimov on 03.07.2021
 */

public class Main {
    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(model, view);

        controller.processUser();
    }
}
