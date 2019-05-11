package com.workingsolutions;

import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.*;

public class User {
    private String userId;
    private String name;
    private Address address;
    private String phone;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void getUserDetailsFromUser(){
        Scanner in = new Scanner(System.in);
        out.print("User Id : ");
        userId = in.nextLine();


        out.print("Name : ");
        name = in.nextLine();

        address = new Address();
        out.println("Address : ");
        address.getAddressDetailsFromUser();

        out.print("Phone : ");
        phone = in.nextLine();
    }

    @Override
    public String toString() {
        return "userId : " + userId + "\tName : " + name + "\tAddress : " + address + "\tPhone : " + phone;
    }

    public String getPackedData(){
        return userId + ":" + name + ":" + address.getPackedData() + ":" + phone + "\n";
    }

    public static User getUnpackedData(String packedData){
        StringTokenizer details = new StringTokenizer(packedData,":");
        User user = new User();
        user.userId = details.nextToken();
        user.name = details.nextToken();
        Address newAddress = new Address();
        newAddress.setStateCode(details.nextToken());
        newAddress.setPinCode(details.nextToken());
        user.address = newAddress;
        user.phone = details.nextToken();
        return user;
    }

    public String getUserId() {
        return userId;
    }
}
