package ru.senkot.model;

import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
@Named
public class EventBean {

    private String string = "some text...";
    private int count;
    private String serialNumber;

    public EventBean() {
    }

    public EventBean(String string, int count, String serialNumber) {
        this.string = string;
        this.count = count;
        this.serialNumber = serialNumber;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "The string is : " + string + " ;" +
                "the count is : " + count + " ;" +
                "the serial number is : " + serialNumber + ".";
    }

}
