package project_1_6413xxx;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Project_1_6413xxx {

    public static void main(String[] args) {
        boolean correctinput = false;
        while(!correctinput)
        try{
        System.out.print("Enter number of white marbles :     ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if(n<=0){
            throw new Minusexception("input an positive integer");
        }
        Marble m = new Marble(n);
        correctinput = true;
        m.start();
        }catch(Minusexception e){
                System.out.printf("input an positive integer !!! \n");
        }
        catch(Exception e){
           System.out.printf("Input an integer !!! \n");
                   
        }
    }
    }
