package com.gmail.vlad9880.model.entity;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBSketchpadTest {

    @Test
    public void whenCheckLoginIsSuccessful(){
        Assert.assertTrue(DBSketchpad.checkLogin("johnny_13"));
    }

}