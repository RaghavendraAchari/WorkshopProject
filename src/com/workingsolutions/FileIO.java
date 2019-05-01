package com.workingsolutions;

import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.*;

public class FileIO {
    public static String search(String fileName,String name){
        try{
            RandomAccessFile file = new RandomAccessFile(fileName, "r");

            String line;
            out.println(file.getFilePointer());
            while ((line=file.readLine())!=null){
                if(line.equals(name)){
                    out.println(file.getFilePointer());
                    return line;
                }
            }
        }catch (Exception e){
            out.println(e);
        }
        return null;
    }
    public static User getUser(String userId) throws Exception{
        File file = new File("Databases/Users.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String currentUser ;
        while ((currentUser=br.readLine())!= null){
            User cur = User.getUnpackedData(currentUser);
            if(userId.equals(cur.getUserId())){
                return cur;
            }
        }


        return null;
    }

    public static boolean writeData(){

        return true;
    }


    public static boolean writeUserData(User newUser)  {
        try{
            //File file = new File("Databases/Users.txt");
            RandomAccessFile f = new RandomAccessFile("Databases/Users.txt","rw");
            //BufferedWriter br = new BufferedWriter(new FileWriter(file));
            //br.write(newUser.getPackedData());
            f.seek(f.length());
            f.writeBytes(newUser.getPackedData());
            f.close();
            return true;
        }catch (Exception e){
            out.println("Error");
            return false;
        }
    }

    public static boolean writeWorkshopData(Workshop workshop){
        try{
            RandomAccessFile file = new RandomAccessFile("Databases/Workshops.txt","rw");
            file.seek(file.length());
            file.writeBytes(workshop.getPackedData());
            file.close();
            return true;
        }catch (Exception e){
            out.println(e.getMessage());
            return false;
        }
    }

    public static List<Workshop> getUserWorkshops(User currentUser) {
        try {
            List<Workshop> list = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("Databases/Workshops.txt"));
            String data ;
            while ((data = br.readLine())!=null){
                Workshop workshop = Workshop.getUnpackedData(data);
                if(workshop.getUserId().equals(currentUser.getUserId())){
                    list.add(workshop);
                }
            }
            return list;

        }catch (Exception e){

        }
        return null;
    }
}
