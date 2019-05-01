package com.workingsolutions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import static java.lang.System.*;

public class Main {
    private static User currentUser = new User();
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        currentUser.setName("ramesh");
        currentUser.setAddress(null);
        currentUser.setPhone("987987987");
        currentUser.setUserId("user1");
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
                    login();
                    break;

                case "6" :
                    return ;

                default:
                    out.println("Wrong Choice");
            }
        }

    }

    private static void showMenu() {
        out.println("Main Menu");
        out.println("1. Show Workshops");
        out.println("2. Book A Workshop");
        out.println("3. Rent Your Workshop");
        out.println("4. Register Yourself");
        out.println("5. Login");
        out.println("6. Exit");
    }

    private static void login() {
        out.println("Enter User Id : ");
        String userId = in.nextLine();

        try{
            String name = FileIO.search("Users.txt", userId);
            if(name==null){
                out.println("No match found");
                currentUser = null;
                return;
            }else {
                currentUser = new User();
            }
        }catch (Exception e){
            out.println("in login");
        }
    }

    public static void book(){

    }
    public static void register(){
        out.println("Enter your details : ");
        User newUser = new User();
        newUser.getUserDetails();
        if(currentUser==null){
            currentUser = newUser;
        }

        if(FileIO.writeUserData(newUser)){
            out.flush();
            out.println("Successfully Registered");
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

        }

    }



}
