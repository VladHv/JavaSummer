package com.gmail.vlad9880;

/**
 * Created by Vlad Herasimov on 03.07.2021
 */

import java.util.ArrayList;
import java.util.List;

public class Sketchpad {

    private List<Record> sketchpad = new ArrayList<>();

    public void addToSketchpad (Record newMember){
        sketchpad.add(newMember);
    }

    public String getAllMembersInString() {
        return sketchpad.toString();
    }

}
