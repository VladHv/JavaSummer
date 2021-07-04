package com.gmail.vlad9880;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecordTest {

    Record record = new Record();

    @Test
    public void whenGenerateShortNameIsSuccessful () {
        record.setName("Vlad");
        record.setSurname("Herasimov");
        record.generateShortName();
        assertEquals("Herasimov V.", record.getShortName());
    }

    @Test
    public void whenSetGroupWorkIsSuccessful () {
        record.setGroup("W");
        assertSame(record.getGroup(), Group.WORK);
    }

    @Test
    public void whenSetGroupFamilyIsSuccessful () {
        record.setGroup("F");
        assertSame(record.getGroup(), Group.FAMILY);
    }

    @Test
    public void whenSetGroupMatesIsSuccessful () {
        record.setGroup("M");
        assertSame(record.getGroup(), Group.MATES);
    }

    @Test
    public void whenSetGroupServicesIsSuccessful () {
        record.setGroup("S");
        assertSame(record.getGroup(), Group.SERVICES);
    }
}