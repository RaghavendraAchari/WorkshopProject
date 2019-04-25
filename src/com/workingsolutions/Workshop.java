package com.workingsolutions;

import java.util.ArrayList;
import java.util.List;

public class Workshop {

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

    private Address workshopAddress;
    private String workshopName;
    private String userId;
    private List<TimingDetails> timings = new ArrayList<TimingDetails>();



}
