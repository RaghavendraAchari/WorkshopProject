package com.workingsolutions;

import java.util.*;

import static java.lang.System.out;

public class Workshop {
    Scanner in = new Scanner(System.in);


    private Address workshopAddress;
    private String workshopName;
    private String userId;
    private String workshopPhone;
    private String price;


    public Workshop(){


    }

    public void printDetails() {
        //TODO: implement this method
    }

    public void printAvailability() {
        //TODO: implement this method
    }

    public void book(String slot) {
        //TODO: implement this method
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public String getPackedData(){
        return userId + ":" + workshopName + ":" + workshopAddress.getPackedData() + ":" + workshopPhone;
    }

    public void getWorkshopDetailsFromUser(){
        userId = "admin";
        out.println("Enter workshop name : ");
        workshopName = in.nextLine();

        out.println("Enter workshop address : ");
        workshopAddress = new Address();
        workshopAddress.getAddressDetailsFromUser();

        out.println("Enter workshop Phone no. : ");
        workshopPhone = in.nextLine();

        out.println("Enter workshop Price For One Day : ");
        price = in.nextLine();
    }

    public static Workshop getUnpackedData(String data){

        StringTokenizer currentWorkshop = new StringTokenizer(data,":");

        Workshop newWorkshop = new Workshop();
        newWorkshop.userId = currentWorkshop.nextToken();

        newWorkshop.workshopName = currentWorkshop.nextToken();

        Address newAddress = new Address();
        newAddress.setStateCode(currentWorkshop.nextToken());
        newAddress.setPinCode(currentWorkshop.nextToken());

        newWorkshop.workshopAddress = newAddress;
        newWorkshop.workshopPhone = currentWorkshop.nextToken();
        newWorkshop.price = currentWorkshop.nextToken();
        return newWorkshop;
    }

    @Override
    public String toString() {
        return "Name : " + workshopName + "\tAddress : " + workshopAddress.toString() +
                "\tPhone : " + workshopPhone + "\tPrice : " + price;
    }
}
