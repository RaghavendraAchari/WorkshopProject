package com.workingsolutions;

import java.util.Scanner;

import static java.lang.System.out;

public class Address{
    private String state;
    private String pinCode;

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
        try{
            int i = Integer.parseInt(stateCode);
            state = States.getStateByCode(String.valueOf(i));
        }catch (Exception e){
            state = stateCode;
        }
    }

    private String stateCode;

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
        if(compress()==-1){
            out.println("State not matched.We will keep your state as it is.");
            stateCode = state;
        }else {
            stateCode = String.valueOf(compress());
        }

        out.print("Pincode : ");
        setPinCode(in.nextLine());
    }

    private int compress() {
        return States.getCode(state);
    }

    public String getPackedData() {
        return stateCode + ":" + pinCode;
    }

}
