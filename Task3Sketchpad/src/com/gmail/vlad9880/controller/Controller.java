package com.gmail.vlad9880.controller;

/**
 * Created by Vlad Herasimov on 03.07.2021
 */

import com.gmail.vlad9880.model.Model;
import com.gmail.vlad9880.model.entity.NotUniqueLoginException;
import com.gmail.vlad9880.model.entity.Sketchpad;
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
        Recorder recorder = new Recorder(view, sc);
        recorder.inputRecord();
        Sketchpad sketchpad = getSketchpad(recorder);
        System.out.println(sketchpad);

    }

    private Sketchpad getSketchpad (Recorder recorder) {
        Sketchpad sketchpad = null;
        while(true) {
            try {
                sketchpad = new Sketchpad(recorder.getFirstName(), recorder.getLogin());
                break;
            } catch (NotUniqueLoginException e) {
                e.printStackTrace();
                System.out.println("Not unique login " + e.getLogin());
                recorder.inputLogin();
            }
        }
        return sketchpad;
    }

}
