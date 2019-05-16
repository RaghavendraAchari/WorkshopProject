package com.workingsolutions;

import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.util.*;

import static java.lang.System.*;

public class FileIO {
    public static String WORKSHOP = "Databases/Workshops.txt";

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

    public static boolean writeUserData(User newUser,List<PrimaryKeyIndex> primaryKeyIndexListist,List<SecondaryKeyIndex> secondaryKeyIndexList)  {
        try{
            //File file = new File("Databases/Users.txt");
            RandomAccessFile f = new RandomAccessFile("Databases/Users.txt","rw");
            //BufferedWriter br = new BufferedWriter(new FileWriter(file));
            //br.write(newUser.getPackedData());
            f.seek(f.length());

            f.writeBytes(newUser.getPackedData());

            PrimaryKeyIndex primaryKeyIndex = new PrimaryKeyIndex(newUser.getUserId(),f.getFilePointer());
            SecondaryKeyIndex secondaryKeyIndex = new SecondaryKeyIndex(newUser.getName(),f.getFilePointer());

            primaryKeyIndexListist.add(primaryKeyIndex);
            secondaryKeyIndexList.add(secondaryKeyIndex);

            sortIndex(primaryKeyIndexListist);
            sortIndex(secondaryKeyIndexList);
            f.close();
            return true;
        }catch (Exception e){
            out.println("Error");
            return false;
        }
    }

    public static boolean writeWorkshopData(Workshop workshop){
        try{
            workshop.writeBookingDetails();
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

    public static List<Workshop> getWorkshops() {
        try {
            List<Workshop> list = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("Databases/Workshops.txt"));
            String data ;
            while ((data = br.readLine())!=null){
                Workshop workshop = Workshop.getUnpackedData(data);
                list.add(workshop);
            }

            return list;

        }catch (Exception e){

        }
        return null;
    }

    public static void createUserIndex(List<PrimaryKeyIndex> primaryKeyIndexList, List<SecondaryKeyIndex> secondaryKeyIndexList) {
        RandomAccessFile file = null;
        try{
            file = new RandomAccessFile("Databases/Users.txt","r");
            String line ;
            long addressInFile = file.getFilePointer();
            while ((line=file.readLine())!=null){
                User user = User.getUnpackedData(line);
                PrimaryKeyIndex primaryKeyIndex = new PrimaryKeyIndex(user.getUserId(),addressInFile);
                SecondaryKeyIndex secondaryKeyIndex = new SecondaryKeyIndex(user.getName(),addressInFile);
                primaryKeyIndexList.add(primaryKeyIndex);
                secondaryKeyIndexList.add(secondaryKeyIndex);
                addressInFile = file.getFilePointer();
            }
            file.close();
            sortIndex(primaryKeyIndexList);
            sortIndex(secondaryKeyIndexList);

        }catch (FileNotFoundException e){
            out.println(e.getStackTrace());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createWorkshopIndex(List<SecondaryKeyIndex> secondaryKeyIndexList) {
        RandomAccessFile file = null;


        try{
            file = new RandomAccessFile("Databases/Workshops.txt","r");
            String line ;
            long addressInFile = file.getFilePointer();
            while ((line=file.readLine())!=null){
                Workshop workshop = Workshop.getUnpackedData(line);
                SecondaryKeyIndex secondaryKeyIndex = new SecondaryKeyIndex(workshop.getWorkshopName(),addressInFile);
                secondaryKeyIndexList.add(secondaryKeyIndex);
                addressInFile = file.getFilePointer();
            }
            file.close();
            sortIndex(secondaryKeyIndexList);

        }catch (FileNotFoundException e){
            secondaryKeyIndexList = null;
            out.println("No Workshops");
        } catch (IOException e) {
            secondaryKeyIndexList = null;
            out.println("No Workshops");
        }catch (Exception e){
            secondaryKeyIndexList = null;
            out.println("No Workshops");
        }

    }

    public static void sortIndex(List<? extends Index> list){
        list.sort(new Comparator<Index>() {
            @Override
            public int compare(Index o1, Index o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
    }

    public static Workshop getWorkshop(String workshopName,List<SecondaryKeyIndex> list) {
        for (SecondaryKeyIndex w :list) {
            if(w.getKey().toLowerCase().equals(workshopName.toLowerCase())){
                try{
                    RandomAccessFile file = new RandomAccessFile("Databases/Workshops.txt","r");
                    file.seek(w.getAddressInFile());
                    String line = file.readLine();
                    Workshop workshop = Workshop.getUnpackedData(line);
                    file.close();
                    return workshop;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static boolean deleteWorkshop(String searchKey,List<SecondaryKeyIndex> list) {
        try{
            RandomAccessFile file = new RandomAccessFile(WORKSHOP,"rw");
            for (SecondaryKeyIndex w:list) {
                if(w.getKey().toLowerCase().equals(searchKey.toLowerCase())){
                    file.seek(w.getAddressInFile());
                    file.write('*');

                    list.remove(w);
                }
                file.close();
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void writeUserIndex(List<PrimaryKeyIndex> list1, List<SecondaryKeyIndex> list2){
        PrintWriter file;
        try{
            file = new PrintWriter("Databases/UserIndex.txt");
            for(PrimaryKeyIndex p:list1){
                String buffer = p.getKey() + ":" + p.getAddressInFile() +"\n" ;
                file.write(buffer);
            }
            file.close();
            file = new PrintWriter("Databases/UserSecIndex.txt");
            for(SecondaryKeyIndex s:list2){
                String buffer2 = s.getKey()+":"+s.getAddressInFile()+"\n";
                file.write(buffer2);
            }
            file.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
