package com.gmail.vlad9880;

/**
 * Created by Vlad Herasimov on 03.07.2021
 */

import java.util.ArrayList;
import java.util.List;

public class Sketchpad {

    private List<SketchpadMember> members = new ArrayList<>();

    public void addToSketchpad (SketchpadMember newMember){
        members.add(newMember);
    }

    public String getAllMembersInString() {
        return members.toString();
    }

}
