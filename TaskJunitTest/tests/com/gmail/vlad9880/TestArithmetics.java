package com.gmail.vlad9880;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TestArithmetics {

    private static Arithmetics a;

    @Rule
    public final ExpectedException exp = ExpectedException.none();

    @Rule
    public Timeout time = new Timeout(1000, TimeUnit.MILLISECONDS);

    @BeforeClass
    public static void runT () {
        a = new Arithmetics();
    }

    @Test
    public void testAdd () {
        double res = a.add(7, 3);
        Assert.assertEquals(res, 10.0, 10e-8);
    }

    @Ignore
    @Test
    public void testMulti () {
        double res = a.multi(3, 7);
        Assert.assertEquals(res, 21.0, 10e-8);
    }

    @Test
    public void testDeduct () {
        double res = a.deduct(3, 7);
        Assert.assertEquals(res, -4.0, 10e-8);
    }

    @Test
    public void testDiv () {
        double res = a.div(21, 3);
        Assert.assertEquals(res, 7.0, 10e-8);
    }

    //@Test(expected = ArithmeticException.class)
    @Test
    public void testDivException() {
        exp.expect(ArithmeticException.class);
        a.div(10.0, 0.0);
    }

    @Ignore
    @Test
    public void testN () {
        while (true) {
        }
    }

}