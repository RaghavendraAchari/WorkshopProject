package com.workingsolutions;

public class Index {
    private String userId;
    private long addressInFile;

    public Index(String id, long address){
        this.userId = id;
        this.addressInFile = address;
     }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getAddressInFile() {
        return addressInFile;
    }

    public void setAddressInFile(int addressInFile) {
        this.addressInFile = addressInFile;
    }

    @Override
    public String toString() {
        return userId + " " + addressInFile;
    }
}
