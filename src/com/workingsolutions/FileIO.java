package com.workingsolutions;

import java.io.*;
import java.util.Scanner;

public class FileIO {
    public static String search(String fileName,String name){
        try{
            File file = new File(fileName);
            System.out.println(file.getAbsolutePath());
            System.out.println(file);

            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line;
            while ((line=br.readLine())!=null){
                if(line.equals(name)){
                    return line;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
