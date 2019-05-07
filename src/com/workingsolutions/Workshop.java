package com.workingsolutions;

import java.util.*;

import static java.lang.System.out;

public class Workshop {
    Scanner in = new Scanner(System.in);


    private Address workshopAddress;
    private String workshopName;
    private String userId;
    private String workshopPhone;


    public Workshop(){
        Date today = new Date();

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

    public String getUserId() {
        return userId;
    }

    public String getPackedData(){
        return userId + ":" + workshopName + ":" + workshopAddress.getPackedData() + ":" + workshopPhone;
    }

    public void getWorkshopDetailsFromUser(){
        out.println("Enter user Id : ");
        userId = in.nextLine();

        out.println("Enter workshop name : ");
        workshopName = in.nextLine();

        out.println("Enter workshop address : ");
        workshopAddress = new Address();
        workshopAddress.getAddressDetailsFromUser();
        out.println("Enter workshop Phone no. : ");
        workshopPhone = in.nextLine();
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
        return newWorkshop;
    }

    @Override
    public String toString() {
        return userId + " " + workshopName + " " + workshopAddress.toString() + " " + workshopPhone;
    }
}
