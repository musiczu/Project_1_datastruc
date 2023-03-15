package project_1_6413xxx;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Project_1_6413xxx {

        public static void main(String[] args) {
            System.out.print("Enter number of white marbles :     ");
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();
            Marble m = new Marble(n);
            m.start();
        }
    }
