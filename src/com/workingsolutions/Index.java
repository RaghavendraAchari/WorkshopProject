package com.workingsolutions;

public abstract class Index {
    public static final String USER = null;
    private long addressInFile;

    public Index(long address){
        this.addressInFile = address;
    }
    public long getAddressInFile() {
        return addressInFile;
    }

    public void setAddressInFile(int addressInFile) {
        this.addressInFile = addressInFile;
    }

    @Override
    public String toString() {
        return String.valueOf(addressInFile);

    }
    public abstract Index getNewObject();
    public abstract String getKey();
}
