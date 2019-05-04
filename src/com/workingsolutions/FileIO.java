package com.workingsolutions;

import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.util.*;

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


    public static boolean writeUserData(User newUser,List<Index> list)  {
        try{
            //File file = new File("Databases/Users.txt");
            RandomAccessFile f = new RandomAccessFile("Databases/Users.txt","rw");
            //BufferedWriter br = new BufferedWriter(new FileWriter(file));
            //br.write(newUser.getPackedData());
            f.seek(f.length());
            Index index = new Index(newUser.getUserId(),f.getFilePointer());
            f.writeBytes(newUser.getPackedData());
            list.add(index);
            sortIndex(list);
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

    public static List<Index> createIndex() {
        RandomAccessFile file = null;
        List<Index> list = new ArrayList<>();

        try{
            file = new RandomAccessFile("Databases/Users.txt","r");
            String line ;
            long addressInFile;
            while ((line=file.readLine())!=null){
                addressInFile = file.getFilePointer();
                User user = User.getUnpackedData(line);
                Index index = new Index(user.getUserId(),addressInFile);
                list.add(index);
            }
            file.close();
            sortIndex(list);
            return list;
        }catch (FileNotFoundException e){
            out.println(e.getStackTrace());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sortIndex(List<Index> list){
        list.sort(new Comparator<Index>() {
            @Override
            public int compare(Index o1, Index o2) {
                return o1.getUserId().compareTo(o2.getUserId());
            }
        });
    }


}
