package com.workingsolutions;

import java.util.Scanner;

import static java.lang.System.out;

public class Address{
    private String state;
    private String pinCode;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public void getAddressDetailsFromUser() {
        Scanner in =new Scanner(System.in);

        out.print("Your state : ");
        setState(in.nextLine());

        out.print("Pincode : ");
        setPinCode(in.nextLine());
    }

    public String getPackedData() {
        return getState() + ":" + getPinCode();
    }
}
