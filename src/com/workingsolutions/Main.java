package com.workingsolutions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {
    public static User currentUser = null;
    private static boolean adminLoggedIn = false;
    private static Scanner in = new Scanner(System.in);
    private static List<PrimaryKeyIndex> primaryKeyIndexList = new ArrayList<>() ;
    private static List<SecondaryKeyIndex> secondaryKeyIndexList = new ArrayList<>() ;
    private static List<SecondaryKeyIndex> secondaryKeyIndexListOfWorkshop = new ArrayList<>() ;
    private static boolean toBeDelete = false;


    public static void main(String[] args) {
        // create a list of Index objects from file and sort it
        FileIO.createUserIndex(primaryKeyIndexList,secondaryKeyIndexList);
        FileIO.createWorkshopIndex(secondaryKeyIndexListOfWorkshop);
        FileIO.writeUserIndex(primaryKeyIndexList,secondaryKeyIndexList);

        String choice;

        while (true){
            if(adminLoggedIn){
                showAdminMenu();
                choice = in.nextLine();
                switch (choice){
                    case "1" :
                        showWorkshops();
                        break;
                    case "2":
                        rent();
                        break;
                    case "3":
                        showBookingDetails();
                        break;
                    case "4":
                        toBeDelete = true;
                        deleteWorkshop();
                        break;
                    case "5":
                        logout();
                        break;
                    case "6":
                        return;
                    default:
                        out.println("Wrong Choice");
                }
            }else {
                showMenu();
                choice = in.nextLine();
                switch (choice){
                    case "1" :
                        showWorkshops();
                        break;

                    case "2" :
                        book();
                        break;

                    case "3" :
                        if(currentUser!=null)
                            logout();
                        else
                            login();
                        break;

                    case "4" :
                        if(currentUser==null)
                            register();
                        else
                            out.println(currentUser.toString());
                        break;
                    case "5" :
                        return ;

                    case "6":
                        printUserIndex();
                        printWorkshopIndex();
                        break;

                    default:
                        out.println("Wrong Choice");
                }
            }
        }
    }

    private static void deleteWorkshop() {
        showWorkshops();
    }

    private static void showBookingDetails() {
        List<Booking> list;
        list = Booking.getAllBookings();
        out.println("Name\t\tDay\t\tBooking status");
        for(Booking b:list){
            out.println(b.toString());
        }
    }

    private static void showAdminMenu() {
        String userName ;

        userName = (currentUser!=null) ? currentUser.getName() : "None" ;
        out.println("Main Menu ----------- User : " + userName);
        out.println("1. Show All Workshops");
        out.println("2. Add New Workshop");
        out.println("3. Show Booking Details");
        out.println("4. Remove Workshop");
        out.println("5. Logout");
        out.println("6. Exit");
    }

    private static void printUserIndex() {
        if(primaryKeyIndexList!=null && secondaryKeyIndexList != null){
            out.println("Primary Index : ");
            out.println("UserId: \t Address");
            for (PrimaryKeyIndex i : primaryKeyIndexList){
                out.println(i.toString());
            }
            out.println();
            out.println("Secondary Index : ");
            out.println("UserName: \t Address");
            for (SecondaryKeyIndex i : secondaryKeyIndexList){
                out.println(i.toString());
            }
        }
    }

    private static void printWorkshopIndex(){
        if(secondaryKeyIndexListOfWorkshop != null){
            out.println();
            out.println("Secondary Index Of Workshop : ");
            out.println("Name : \t Address");
            for (SecondaryKeyIndex i : secondaryKeyIndexListOfWorkshop){
                out.println(i.toString());
            }
        }
    }

    private static void showMenu() {
        String userName ;

        userName = (currentUser!=null) ? currentUser.getName() : "None" ;
        out.println("Main Menu ----------- User : " + userName);
        out.println("1. Show All Workshops");
        out.println("2. Book A Workshop");
        if(currentUser!=null)
            out.println("3. Logout");
        else
            out.println("3. Login");

        if(currentUser==null)
            out.println("4. Register Yourself");
        else
            out.println("4. Show My Details");
        out.println("5. Exit");
        out.println("6. Show Index");


    }

    private static void login() {
        out.println("Enter User Id : ");
        String userId = in.nextLine();

        try{
            User user = FileIO.getUser(userId);
            if(user==null){
                out.println("No match found");
                currentUser = null;
                return;
            }else {
                currentUser = user;
                if(currentUser.getUserId().equals("admin")) {
                    adminLoggedIn = true;
                }
            }
        }catch (Exception e){
            out.println("Something went wrong in login");
        }
    }

    public static void logout(){
        if(currentUser!=null)
            currentUser = null;
        adminLoggedIn = false;
    }

    public static void book(){
        if(currentUser == null) {
            out.println("Please login.");
            login();
        }
        showWorkshops();
        out.print("Please enter workshop name to select the workshop : ");
        String workshopId = in.nextLine();
        Workshop workshop;
        if(!workshopId.isEmpty()) {
             workshop = FileIO.getWorkshop(workshopId, secondaryKeyIndexListOfWorkshop);
            out.println("Below are the details of the workshop.");
            out.println("----------------------------------------");
            workshop.toString();
            workshop.printWorkshopBookingDetails();
            out.println("Available slots");
            out.println("----------------------------");
            workshop.printAvailability();
        }else {
            out.println("Entered Wrong choice");
            return;
        }
    }

    public static void register(){
        out.println("Enter your details : ");
        User newUser = new User();
        newUser.getUserDetailsFromUser();


        if(FileIO.writeUserData(newUser, primaryKeyIndexList,secondaryKeyIndexList)){
            out.println("Successfully Registered");
        }
        currentUser = newUser;
    }

    public static void rent(){
        Workshop newWorkshop = new Workshop();
        newWorkshop.getWorkshopDetailsFromUser();
        if(FileIO.writeWorkshopData(newWorkshop))
            out.println("Workshop added successfully");
        else
            out.println("Something went wrong");
    }


    public static void showWorkshops(){
        if(currentUser == null){
            out.println("Please login : ");
            try{
                login();
            }catch (Exception e){
                out.println(e.getMessage());
            }
        }

        try {
            List<Workshop> list ;
            list = FileIO.getWorkshops();
            out.format("%-10s %-15s %-10s %-10s %-10s","Name","Address: State","Pin","Phone","Price");
            out.println();
            out.println("---------------------------------------------------------------");

            for (Workshop w:list) {
                out.format("%-10s %-15s %-10s %-10s %-10s",w.getWorkshopName(),w.getWorkshopAddress().getState(),
                        w.getWorkshopAddress().getPinCode(),w.getWorkshopPhone(),w.getPrice());
                out.println();
                //out.println(w.toString());

            }
        } catch (Exception e) {
            out.println("No Workshops");
        }
        String searchKey;
        Workshop workshop;
        out.println();
        out.println("Enter workshop name to search specific workshop else press enter to proceed further");
        searchKey = in.nextLine();
        if(searchKey.isEmpty() && !adminLoggedIn)
            return;
        else if(!searchKey.isEmpty()) {
            workshop = FileIO.getWorkshop(searchKey,secondaryKeyIndexListOfWorkshop);
            if(workshop!=null) {
                out.format("%-10s %-15s %-10s %-10s %-10s","Name","Address: State","Pin","Phone","Price");
                out.println();
                out.println("---------------------------------------------------------------");
                out.format("%-10s %-15s %-10s %-10s %-10s",workshop.getWorkshopName(),workshop.getWorkshopAddress().getState(),
                        workshop.getWorkshopAddress().getPinCode(),workshop.getWorkshopPhone(),workshop.getPrice());
                out.println();
            }
            else
                out.println("No Workshop Found");
        }
        if(adminLoggedIn && toBeDelete){
            out.println("Enter Workshop Name To Delete Workshop");
            searchKey = in.nextLine();
            if (searchKey.isEmpty()){
                toBeDelete = false;
                return;
            }

            if(FileIO.deleteWorkshop(searchKey,secondaryKeyIndexListOfWorkshop)){
                out.println("Deleted Successfully");
            }else {
                out.println("Error");
            }
            toBeDelete = false;
        }
    }

}
