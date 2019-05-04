package com.workingsolutions;

import java.util.Date;

public class Booking {
    private enum BookingStatus{
        BOOKED, AVAILABLE, NOT_AVAILABLE
    }
    private Date date;
    private BookingStatus status;

    Booking(Date date, BookingStatus status){
        this.date = date;
        this.status = status;
    }

}
