package com.workingsolutions;

public class EStates {
    public static enum States{

        KARNATAKA(29);

        private int code;

        States(int code){
            this.code = code;
        }
        private int getCode(){
            return code;
        }

    }
    public EStates(String name, int code){
        stateName = name;
        this.code = code;
    }
    public String stateName;
    public int code;

}

