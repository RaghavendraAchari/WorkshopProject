package com.workingsolutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class Workshop {
    Scanner in = new Scanner(System.in);


    private Address workshopAddress;
    private String workshopName;
    private String userId;

    private class WorkshopDetails{
        public int basicTools;
        public int advancedTools;
        public boolean transportationAvailable;
    }

    private enum Booking{
        BOOKED, AVAILABLE, NOT_AVAILABLE
    }

    private class TimingDetails{
        private String day;
        private Booking morning;
        private Booking noon;
        private Booking evening;

    }

    public String getUserId() {
        return userId;
    }

    public String getPackedData(){
        return userId + ":" + workshopName + ":" + workshopAddress.getPackedData();
    }

    public void getWorkshopDetailsFromUser(){
        out.println("Enter user Id : ");
        userId = in.nextLine();

        out.println("Enter workshop name : ");
        workshopName = in.nextLine();

        out.println("Enter workshop address : ");
        workshopAddress = new Address();
        workshopAddress.getAddressDetailsFromUser();
    }

    public static Workshop getUnpackedData(String data){

        StringTokenizer currentWorkshop = new StringTokenizer(data,":");

        Workshop newWorkshop = new Workshop();
        newWorkshop.userId = currentWorkshop.nextToken();

        newWorkshop.workshopName = currentWorkshop.nextToken();

        Address newAddress = new Address();
        newAddress.setState(currentWorkshop.nextToken());
        newAddress.setPinCode(currentWorkshop.nextToken());

        newWorkshop.workshopAddress = newAddress;
        return newWorkshop;
    }

    @Override
    public String toString() {
        return userId + " " + workshopName + " " + workshopAddress.toString();
    }
}
