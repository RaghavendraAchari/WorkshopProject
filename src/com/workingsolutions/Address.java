package com.workingsolutions;

public class Address{
    private String State;
    private String pinCode;

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return getState() + " - " + getPinCode();
    }
}
