package com.gmail.vlad9880;

/**
 * Created by Vlad Herasimov 24.06.2021
 */

public class Model {

    private String wordHello;
    private String wordWorld;


    public void setWordHello(String wordHello) {
        this.wordHello = wordHello;
    }

    public void setWordWorld(String wordWorld) {
        this.wordWorld = wordWorld;
    }

    public String getFinalMessage() {
        return wordHello + " " + wordWorld;
    }

}
