package com.workingsolutions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {
    private static User currentUser = new User();
    private static Scanner in = new Scanner(System.in);
    private static List<PrimaryKeyIndex> primaryKeyIndexList = new ArrayList<>() ;
    private static List<SecondaryKeyIndex> secondaryKeyIndexList = new ArrayList<>() ;
    public static void main(String[] args) {
        currentUser.setName("ramesh");
        currentUser.setAddress(null);
        currentUser.setPhone("987987987");
        currentUser.setUserId("user1");

        // create a list of Index objects from file and sort it
        FileIO.createIndex(primaryKeyIndexList,secondaryKeyIndexList);

        while (true){
            showMenu();
            String choice = in.nextLine();
            switch (choice){
                case "1" :
                    showWorkshops();
                    break;

                case "2" :
                    book();
                    break;

                case "3" :
                    rent();
                    break;

                case "4" :
                    register();
                    break;

                case "5" :
                    if(currentUser!=null)
                        logout();
                    else
                        login();
                    break;

                case "6" :
                    return ;
                case "7":
                    printIndex();
                    break;

                default:
                    out.println("Wrong Choice");
            }
        }

    }

    private static void printIndex() {
        if(primaryKeyIndexList!=null && secondaryKeyIndexList != null){
            out.println("Primary Index : ");
            for (PrimaryKeyIndex i : primaryKeyIndexList){
                out.println(i.toString());
            }

            out.println("Secondary Index : ");
            for (SecondaryKeyIndex i : secondaryKeyIndexList){
                out.println(i.toString());
            }
        }
    }

    private static void showMenu() {
        String userName ;

        userName = (currentUser!=null) ? currentUser.getName() : "None" ;
        out.println("Main Menu ----------- User : " + userName);
        out.println("1. Show My Workshops");
        out.println("2. Book A Workshop");
        out.println("3. Rent Your Workshop");
        out.println("4. Register Yourself");
        if(currentUser!=null)
            out.println("5. Logout");
        else
            out.println("5. Login");
        out.println("6. Exit");
        out.println("7. Show Index");
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
            }
        }catch (Exception e){
            out.println("in login");
        }
    }

    public static void logout(){
        if(currentUser!=null)
            currentUser = null;
    }

    public static void book(){
        if(currentUser == null) {
            out.println("Please login.");
            login();
        }
        FileIO.showAllWorkshops();
        out.print("Please enter workshop id to select the workshop : ");
        String workshopId = in.nextLine();

        Workshop workshop = FileIO.getWorkshop(workshopId);

        out.println("Below are the details of the workshop.");
        workshop.printDetails();
        workshop.printAvailability();

        out.println("Enter a slot number to book the workshop");
        String slot = in.nextLine();
        try {
            workshop.book(slot);
            out.println("Thanks for using our service.");
        }catch (Exception e){
            e.getMessage();
        }

    }
    public static void register(){
        out.println("Enter your details : ");
        User newUser = new User();
        newUser.getUserDetailsFromUser();


        if(FileIO.writeUserData(newUser, primaryKeyIndexList,secondaryKeyIndexList)){
            out.println("Successfully Registered");
        }
        if(currentUser==null){
            currentUser = newUser;
        }
    }
    public static void rent(){

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
            File file = new File("Databases/Workshops.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<Workshop> list = new ArrayList<>();
            list = FileIO.getUserWorkshops(currentUser);
            for (Workshop w:list) {
                out.println(w.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
