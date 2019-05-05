package com.workingsolutions;

public class PrimaryKeyIndex extends Index{
    private String userId;

    PrimaryKeyIndex(String id,long address){
        super(address);
        this.userId = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public PrimaryKeyIndex getNewObject(){
        return null;
    }

    @Override
    public String getKey() {
        return userId ;
    }

    @Override
    public String toString() {
        return userId + " " + getAddressInFile();
    }
}
