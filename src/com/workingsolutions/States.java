package com.workingsolutions;

import java.util.ArrayList;
import java.util.List;

public class States{
    private class State{
        private String stateName;
        private int code;
    }

    State[] states = new State[29];

    public static String[] stateList = {
            "andra aradesh",
            "arunachal pradesh",
            "assam",
            "bihar",
            "chhattisgarh",
            "goa",
            "gujarat",
            "haryana",
            "himachal pradesh",
            "jammu and kashmir",
            "jharkhand",
            "karnataka",
            "kerala",
            "madya pradesh",
            "maharashtra",
            "manipur",
            "meghalaya",
            "mizoram",
            "nagaland",
            "orissa",
            "punjab",
            "rajasthan",
            "sikkim",
            "tamil nadu",
            "telagana",
            "tripura",
            "uttaranchal",
            "uttar pradesh",
            "west bengal"
    };
    public static int getCode(String state){
        for (int i=0; i<stateList.length; i++){
            if(stateList[i].equals(state)){
                return i;
            }
        }
        return -1;
    }
    public static String getStateByCode(String code){
        int i;
        try{
            i=Integer.parseInt(code);
            return stateList[i];
        }catch (Exception e){
            return code;
        }
    }

}
