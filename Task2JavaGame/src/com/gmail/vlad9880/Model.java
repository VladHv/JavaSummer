package com.gmail.vlad9880;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private int attempts = 0;
    private int maxNumber;
    private int minNumber;
    private int unknownNumber;

    private List<Integer> allUserNumbers = new ArrayList<>();

    //Tested and Ignored
    public void setUnknownNumber() {
        unknownNumber= (int)Math.ceil(Math.random() *
                (maxNumber - minNumber - 1) + minNumber);
    }

    public void setBorder(int minNumber, int maxNumber) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    public boolean checkNumber(int userNumber) {
        attempts++;
        allUserNumbers.add(userNumber);
        if (userNumber == unknownNumber){
            return false;
        } else if (userNumber < unknownNumber) {
            minNumber = userNumber;
        } else {
            maxNumber = userNumber;
        }
        return true;
    }

    public int getUnknownNumber (){
        return  unknownNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public int getAttempts() {
        return attempts;
    }

    public List<Integer> getAllUserNumbers() {
        return allUserNumbers;
    }
}
