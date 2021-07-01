package com.gmail.vlad9880;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestModel {

    private Model model = new Model();

    @Ignore
    @Test
    public void whenSetUnknownNumberIsSuccessful() {
        model.setBorder(0, 100);
        for (int i = 0; i < 100000; i++) {
            model.setUnknownNumber();
            Assert.assertTrue(model.getUnknownNumber() > 0 && model.getUnknownNumber() < 100);
        }

    }

    @Test
    public void whenCheckNumberForCorrectIsSuccessful () {
        model.setUnknownNumber();
        int userNumberCorrect = model.getUnknownNumber();
        Assert.assertFalse(model.checkNumber(userNumberCorrect));
    }

    @Test
    public void whenCheckNumberForLessIsSuccessful () {
        model.setUnknownNumber();
        int userNumberLess = model.getUnknownNumber() - 1;
        Assert.assertTrue(model.checkNumber(userNumberLess));
    }

    @Test
    public void whenCheckNumberForGreaterIsSuccessful () {
        model.setUnknownNumber();
        int userNumberGreater = model.getUnknownNumber() + 1;
        Assert.assertTrue(model.checkNumber(userNumberGreater));
    }


}

