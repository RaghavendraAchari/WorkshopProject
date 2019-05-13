package com.workingsolutions;

import java.io.RandomAccessFile;
import java.util.*;

import static java.lang.System.out;

public class Workshop {
    Scanner in = new Scanner(System.in);


    private Address workshopAddress;
    private String workshopName;
    private String userId;
    private String workshopPhone;
    private String price;
    private Booking[] booking = new Booking[7];


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

        out.println("Please add workshop availability");
        out.println("Please press 1 for 'Available', 2 for 'Booked' and 3 for 'Not Available'");
        int i=0;
        for(String day : Booking.days){
            out.print(day + " : ");
            String input = in.nextLine();
            Booking.BookingStatus status ;
            switch (input){
                case "1" :
                    status = Booking.BookingStatus.AVAILABLE;
                    booking[i] = new Booking(day,status);
                    break;
                case "2" :
                    status = Booking.BookingStatus.BOOKED;
                    booking[i] = new Booking(day,status);
                    break;
                case "3" :
                    status = Booking.BookingStatus.NOT_AVAILABLE;
                    booking[i] = new Booking(day,status);
                    break;
            }
        }

    }
    public void writeBookingDetails(){
        RandomAccessFile file;
        try{
            file = new RandomAccessFile("Databases/Bookings.txt","rw");
            for(Booking b: booking){
                String data = workshopName + ":" + Booking.getPackedData(b)+ "\n";
                file.seek(file.length());
                file.writeBytes(data);
                file.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void printWorkshopBookingDetails(){
        for(Booking b: booking){
            out.println(b.getName() + "\t" + b.getDay() + "\t" + b.getStatus());
        }
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
