package com.workingsolutions;

import java.io.RandomAccessFile;
import java.util.*;

import static com.workingsolutions.Booking.*;
import static java.lang.System.out;

public class Workshop {
    private Address workshopAddress;
    private String workshopName;
    private String userId;
    private String workshopPhone;
    private String price;
    private Booking[] booking = new Booking[7];
    public static List<BookingIndex> bookingIndex = new ArrayList<>();
    Scanner in = new Scanner(System.in);

    public class BookingIndex{
        long address;
        String name;
        String day;
    }

    public Address getWorkshopAddress() {
        return workshopAddress;
    }

    public String getWorkshopPhone() {
        return workshopPhone;
    }

    public String getPrice() {
        return price;
    }public Workshop(){


    }

    public void createIndex(){
        bookingIndex = new ArrayList<>();
        try{
            RandomAccessFile file = new RandomAccessFile( "Databases/Bookings.txt","rw");
            long addressInFile = file.getFilePointer();
            String line;
            while ((line = file.readLine())!=null){
                Booking b = Booking.getUnpacked(line);
                if(b.getName().equals(workshopName)){
                    BookingIndex bi = new BookingIndex();
                    bi.address = addressInFile;
                    bi.day = b.getDay();
                    bi.name = b.getName();
                    bookingIndex.add(bi);
                }
                addressInFile = file.getFilePointer();
            }
            file.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printAvailability() {
        int i=0;
        createIndex();

        out.format("%-10s %-10s %-10s %-10s","No.","workshop","Day","Status");
        out.println();
        out.println("--------- --------- --------- ---------");
        for(Booking b: booking){
            if(b.getStatus().equals(BookingStatus.AVAILABLE.toString())) {
                out.format("%-10d %-10s %-10s %-10s",(i+1),b.getName() ,b.getDay(),b.getStatus());
                out.println();
            }
            i++;
        }
        out.println("Enter the slot number to book (ex: 1 for 1st and 1,2,3.. for multiple) ");
        String selection = in.nextLine();
        StringTokenizer selected = new StringTokenizer(selection,",");
        while (selected.hasMoreTokens()){
            book(selected.nextToken());
        }
    }

    public void book(String slot) {
        switch (slot){
            case "1" :
                out.println(booking[0].getStatus());
                if(booking[0].getStatus().equals(BookingStatus.AVAILABLE.toString())){
                    booking[0].setStatus(BookingStatus.BOOKED);
                    if(changeStatus(0,booking[0]))
                        out.println("Successfully Booked");
                }
                break;

            case "2" :
                if(booking[1].getStatus().equals(BookingStatus.AVAILABLE.toString())){

                    booking[1].setStatus(BookingStatus.BOOKED);
                    if(changeStatus(1,booking[1]))
                        out.println("Successfully Booked");
                }
                break;

            case "3" :
                if(booking[2].getStatus().equals(BookingStatus.AVAILABLE.toString())){
                    booking[2].setStatus(BookingStatus.BOOKED);
                    if(changeStatus(2,booking[2]))
                        out.println("Successfully Booked");
                }
                break;

            case "4" :
                if(booking[3].getStatus().equals(BookingStatus.AVAILABLE.toString())){
                    booking[3].setStatus(BookingStatus.BOOKED);
                    if(changeStatus(3,booking[3]))
                        out.println("Successfully Booked");
                }
                break;

            case "5" :
                if(booking[4].getStatus().equals(BookingStatus.AVAILABLE.toString())){
                    booking[4].setStatus(BookingStatus.BOOKED);
                    if(changeStatus(4,booking[4]))
                        out.println("Successfully Booked");
                }
                break;

            case "6" :
                if(booking[5].getStatus().equals(BookingStatus.AVAILABLE.toString())){
                    booking[5].setStatus(BookingStatus.BOOKED);
                    if(changeStatus(5,booking[5]))
                        out.println("Successfully Booked");
                }
                break;

            case "7" :
                if(booking[6].getStatus().equals(BookingStatus.AVAILABLE.toString())){
                    booking[6].setStatus(BookingStatus.BOOKED);
                    if(changeStatus(6,booking[6]))
                        out.println("Successfully Booked");
                }
                break;
            default:
                out.println("Selected slot is not available");
        }
    }

    private boolean changeStatus( int pos, Booking b) {
        double gst = 0.13;
        try{
            RandomAccessFile file = new RandomAccessFile("Databases/Bookings.txt","rw");
            long address = -1;

            for(BookingIndex bi : bookingIndex){
                if(b.getDay().equals(bi.day)){
                    address = bi.address;
                    break;
                }
            }
            if(address == -1)
                throw new Exception("Address not found");
            file.seek(address);
            file.write('*');
            file.seek(file.length());
            b.bookedUser = Main.currentUser.getUserId();

            double totalPrice = (Integer.parseInt(price) * gst) + Integer.parseInt(price);

            b.price = new String(String.valueOf(totalPrice));
            String line = workshopName +":"+ Booking.getPackedData(b)+"\n";
            file.writeBytes(line);
            file.close();
            out.println("Booking Details : ");

            out.format("%-10s %-10s %-10s %-10s","User","workshop","Day","Price");
            out.println();
            out.println("--------- --------- --------- ----------");
            out.format("%-10s %-10s %-10s %-10s",b.bookedUser,b.getName() ,b.getDay(),totalPrice);
            out.println();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public String getPackedData(){
        return userId + ":" + workshopName + ":" + workshopAddress.getPackedData() + ":" + workshopPhone +":" + price;
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
        for(String day : days){
            out.print(day + " : ");
            String input = in.nextLine();
            BookingStatus status ;
            switch (input){
                case "1" :
                    status = BookingStatus.AVAILABLE;
                    booking[i] = new Booking(day,status);
                    break;
                case "2" :
                    status = BookingStatus.BOOKED;
                    booking[i] = new Booking(day,status);
                    break;
                case "3" :
                    status = BookingStatus.NOT_AVAILABLE;
                    booking[i] = new Booking(day,status);
                    break;

            }
            i++;
        }

    }

    public void writeBookingDetails(){
        RandomAccessFile file;
        try{
            file = new RandomAccessFile("Databases/Bookings.txt","rw");
            for(Booking b: booking){
                b.bookedUser = "None";
                b.price="NA";
                String data = workshopName + ":" + Booking.getPackedData(b)+ "\n";
                file.seek(file.length());
                file.writeBytes(data);

            }
            file.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printWorkshopBookingDetails(){
        List<Booking> list = getBookings(workshopName);
        int i=0;
        for (Booking b:list) {
            for(int j=0;j<days.length;j++){
                if(b.getDay().equals(days[j])){
                    booking[j] = b;
                    break;
                }
            }
            i++;
        }
        out.format("%-10s %-10s %-10s","Name","Day","Status");
        out.println();
        out.println("--------- --------- ----------");
        for(Booking b: booking){
            out.format("%-10s %-10s %-10s",b.getName(), b.getDay(),b.getStatus());
            out.println();
        }
        out.println();
    }

    private List<Booking> getBookings(String workshopName) {
        RandomAccessFile file;
        List<Booking> list = new ArrayList<>();
        try{
            file = new RandomAccessFile("Databases/Bookings.txt","r");
            String line;
            while ((line = file.readLine())!=null) {
                Booking b = getUnpacked(line);
                if(b.getName().equals(workshopName))
                    list.add(b);
            }
            file.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
