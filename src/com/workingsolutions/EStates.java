package com.workingsolutions;

public class EStates {
    public static enum States{
        KARNATAKA(47);

        private int code;

        States(int code){
            this.code = code;
        }
        private int getCode(){
            return code;
        }

    }
}
