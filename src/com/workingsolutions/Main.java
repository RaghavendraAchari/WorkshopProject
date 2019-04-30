package com.workingsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import static java.lang.System.*;

public class Main {
    private static User currentUser = null;
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        showMenu();

        String s = FileIO.search("Users.txt","raghav");
        out.println(s);
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
        byte[] buffer = new byte[1024];
        try{

            String name = FileIO.search("Users.txt", userId);
            if(name==null){
                out.println("No match found");
                return;
            }

        }catch (Exception e){
            out.println("in login");
        }
    }

    public static void book(){

    }
    public static void register(){

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
        }else {
            try {
                File usersFile = new File("Users.txt");
                out.println(new FileInputStream(usersFile));
            } catch (Exception e) {

            }
        }
    }



}
