package com.workingsolutions;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class Booking {
    public static String[] days = {
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thusday",
            "Friday",
            "Saturday",
            "Sunday"
    };

    public static String getPackedData(Booking booking) {
        return booking.date + ":" + booking.status.toString();
    }

    public static List<Booking> getAllBookings() {
        RandomAccessFile file;
        List<Booking> list = new ArrayList<>();
        try{
            file = new RandomAccessFile("Databases/Bookings.txt","r");
            String line;
            while ((line = file.readLine())!=null) {
                Booking b = getUnpacked(line);
                list.add(b);
            }
            file.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return wName+ "\t\t" + date + "\t\t" + status.toString();
    }

    private static Booking getUnpacked(String line) {
        StringTokenizer st = new StringTokenizer(line,":");
        String name = st.nextToken();
        Booking b = new Booking(st.nextToken(),Booking.BookingStatus.valueOf(st.nextToken()));
        b.setwName(name);
        return b;
    }

    public String getDay() {
        return date;
    }

    public String getStatus() {
        return status.toString();
    }
    public void setwName(String name){
        this.wName = name;
    }

    public String getName() {
        return wName;
    }

    public static enum BookingStatus{
        BOOKED, AVAILABLE, NOT_AVAILABLE
    }
    private String wName;
    private String date;
    private BookingStatus status;

    Booking(String date, BookingStatus status){
        this.date = date;
        this.status = status;
    }

}
