package com.workingsolutions;

import java.io.*;
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

    public static boolean writeData(){

        return true;
    }


    public static boolean writeUserData(User newUser)  {
        try{
            File file = new File("Databases/Users.txt");
            //RandomAccessFile f = new RandomAccessFile("Databases/Users.txt","w");
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            br.write(newUser.getPackedData());
            return true;
        }catch (Exception e){
            out.println("Error");
            return false;
        }
    }
}
