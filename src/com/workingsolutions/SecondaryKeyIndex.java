package com.workingsolutions;

public class SecondaryKeyIndex extends Index {



    private String name;

    SecondaryKeyIndex(String name, long address){
        super(address);
        this.name = name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public SecondaryKeyIndex getNewObject(){
        return  null;
    }

    @Override
    public String getKey() {
        return name ;
    }
    @Override
    public String toString() {
        return name + " \t " + getAddressInFile();
    }
}
