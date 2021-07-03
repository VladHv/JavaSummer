package com.gmail.vlad9880;

/**
 * Created by Vlad Herasimov on 03.07.2021
 */

public class Main {
    public static void main(String[] args) {
        View view = new View();
        SketchpadMember sketchpadMember = new SketchpadMember();
        Sketchpad sketchpad = new Sketchpad();
        Controller controller = new Controller(view, sketchpadMember, sketchpad);

        controller.processUser();
    }
}
