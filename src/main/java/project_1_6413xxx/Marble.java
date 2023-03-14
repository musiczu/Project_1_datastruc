package project_1_6413xxx;

import java.util.*;
import java.io.*;

class Marble {

    //private ArrayList<String> Rungsi = new ArrayList<String>();
    //private ArrayList<String> newRungsi = new ArrayList<String>();
    private int size = 0;

    public Marble() {
    }

    public Marble(int n) {
        size = n;
        ArrayList<String> Rungsi = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            Rungsi.add("w" + i);
        }
        Rungsi.add("_");
        for (int i = 0; i < n; i++) {
            Rungsi.add("b" + i);
        }
        this.showall(Rungsi);
        
        this.solvable(Rungsi);
    }
    
    private boolean solvable(ArrayList<String> Rungsi) {
        if (puzzleSolved(Rungsi)) {
            return true;
        }
        this.showall(Rungsi);
        for (int position = 0; position < size; position++) {
            if (canMove(Rungsi,position)) {
                ArrayList<String> newRungsi = makeMove(Rungsi,position);

                if (solvable(newRungsi)) {
                    return true;
                }

                //System.out.println("NOT SOLVABLE: ");
                //System.out.println("Position: " + position);
                //printBoard1(board);
            }
        }
        return false;
    }
    private void printBoard1(ArrayList<String> newRungsi) {
        for (String i : newRungsi) {
            if (i.charAt(0) == 'b') {
                System.out.print(" ");
            } else if(i.charAt(0) == 'w') {
                System.out.print("BLACK ");
            } else {
                System.out.print("___ ");
            }
        }

        System.out.println();
    }

    private boolean canMove(ArrayList<String> Rungsi, int position) {
        if (Rungsi.get(position).charAt(0) == '_') {
            return false;
        }

        if (Rungsi.get(position).charAt(0) == 'w') {
            if ((position + 1) < size  &&  Rungsi.get(position + 1).charAt(0) == '_') {
                return true;
            }

            if ((position + 2) < size  &&  Rungsi.get(position + 2).charAt(0) == '_'   &&  Rungsi.get(position + 1).charAt(0) == 'b') {
                return true;
            }
        }

        if (Rungsi.get(position).charAt(0) == 'b') {
            if ((position - 1) >= 0  &&  Rungsi.get(position - 1).charAt(0) == '_') {
                return true;
            }

            if ((position - 2) >= 0  &&  Rungsi.get(position - 2).charAt(0) == '_'  &&  Rungsi.get(position - 1).charAt(0) == 'w') {
                return true;
            }
        }

        return false;
    }

    private ArrayList<String> makeMove(ArrayList<String> Rungsi, int position) {
        ArrayList<String> temp = new ArrayList<String>();

        System.arraycopy(Rungsi, 0, temp, 0, size);


        if (temp.get(position).charAt(0) == '_') {
            throw new IllegalStateException();
        }

        if (temp.get(position).charAt(0) == 'w') {
            if (temp.get(position + 1).charAt(0) == '_') {
                String TEMP = temp.get(position);
                temp.get(position).equals("_");
                temp.get(position+1).equals(TEMP);
                return temp;
            }

            if (temp.get(position + 2).charAt(0) == '_'  &&  temp.get(position + 1).charAt(0) == 'b') {
                String TEMP = temp.get(position+2);
                temp.get(position).equals("_");
                temp.get(position + 2).equals(TEMP);
                return temp;
            }
        }

        if (temp.get(position).charAt(0) == 'b') {
            if (temp.get(position - 1) == "_") {
                String TEMP = temp.get(position);
                temp.get(position).equals("_");
                temp.get(position - 1).equals(TEMP);
                return temp;
            }

            if (temp.get(position - 2).charAt(0) == '_'  &&  temp.get(position - 1).charAt(0) == 'w') {
                String TEMP =  temp.get(position);
                temp.get(position).equals("_");
                temp.get(position - 2).equals(TEMP);
                return temp;
            }
        }

        return temp;
    }

    private boolean puzzleSolved(ArrayList<String> Rungsi) {
        int half = size / 2;

        for (int i = 0; i < half; i++) {
            if (Rungsi.get(i).charAt(0) != 'b') {
                return false;
            }
        }

        for (int i = half + 1; i < size; i++) {
            if (Rungsi.get(i).charAt(0) != 'w') {
                return false;
            }
        }
        //this.showall(Rungsi);
        return true;
    }
    
    public void showall(ArrayList<String> Rungsi) {
        for (String a : Rungsi) {
            System.out.printf("%s ", a);
        }
        System.out.printf("\n");
    }
}
