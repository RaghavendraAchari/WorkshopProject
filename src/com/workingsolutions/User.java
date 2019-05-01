package com.workingsolutions;

import java.util.Scanner;

import static java.lang.System.*;

public class User {
    private String userId;
    private String name;
    private Address address;
    private String phone;

    public void getUserDetails(){
        Scanner in = new Scanner(System.in);
        out.println("User Id :");
        userId = in.nextLine();


        out.println("Name :");
        name = in.nextLine();

        address = new Address();
        out.println("Address :");
        address.getAddressDetails();

        out.println("Phone :");
        phone = in.nextLine();
    }

    @Override
    public String toString() {
        return userId + " " + name + " " + address + " " + phone;
    }

    public String getPackedData(){
        return userId + ":" + name + ":" + address.getPackedData() + ":" + phone + "\n";
    }
}
