package project_1_6413xxx;

import java.util.*;
import java.io.*;

class Marble {

    private ArrayList<String> Rungsi = new ArrayList<String>();
    //private ArrayList<String> newRungsi = new ArrayList<String>();
    private int size = 0;

    public Marble() {
    }

    public Marble(int n) {
        size = (2*n)+1;
        Rungsi = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            Rungsi.add("w" + i);
        }
        Rungsi.add("_");
        for (int i = 0; i < n; i++) {
            Rungsi.add("b" + i);
        }
        this.showall(Rungsi);
        System.out.println("1");
    }
    
    public void start(){
        showall(Rungsi);
        System.out.println("1.5");
        if(this.solvable(Rungsi))
            System.out.printf("Kawin\n");
        else System.out.printf("Newyear\n");
    }
    
    private boolean solvable(ArrayList<String> board) {
        if (puzzleSolved(board)) {
            return true;
        }
        for (int position = 0; position < size; position++) {
            if (canMove(board, position)) {
                showall(board);
                System.out.println("1.75");
                ArrayList<String> newBoard = makeMove(board, position);
                showall(newBoard);
                System.out.println("2");

                if (solvable(newBoard)) {
                    showall(newBoard);
                    System.out.println("3");
                    //printBoard1(newBoard);
                    return true;
                }

                System.out.println("NOT SOLVABLE: ");
                System.out.println("Position: " + position);
                showall(newBoard);
                System.out.println("5");
                //printBoard1(board);
            }
        }
        return false;
    }

    /*private void printBoard1(ArrayList<String> newBoard) {
        for (String i : newBoard) {
            if (i == BLACK) {
                System.out.print("WHITE ");
            } else if(i == WHITE) {
                System.out.print("BLACK ");
            } else {
                System.out.print("___ ");
            }
        }

        System.out.println();
    }*/

    private boolean canMove(ArrayList<String> board, int position) {
        
        System.out.println(board.get(position));
        if (board.get(position).charAt(0) == '_') {
            return false;
        }

        if (board.get(position).charAt(0) == 'w') {
            if ((position + 1) < size &&  board.get(position + 1).charAt(0) == '_') {
                return true;
            }

            if ((position + 2) < size  &&  board.get(position + 2).charAt(0) == '_'  &&  board.get(position + 1).charAt(0) == 'b') {
                return true;
            }
        }

        if (board.get(position).charAt(0) == 'b') {
            if ((position - 1) >= 0  &&  board.get(position - 1).charAt(0) == '_') {
                return true;
            }

            if ((position - 2) >= 0  &&  board.get(position - 2).charAt(0) == '_'  &&  board.get(position).charAt(0) == 'w') {
                return true;
            }
        }

        return false;
    }

    private ArrayList<String> makeMove(ArrayList<String> board, int position) {
        ArrayList<String> newBoard = (ArrayList) board.clone();

        if (newBoard.get(position).charAt(0) == '_') {
            throw new IllegalStateException();
        }

        if (newBoard.get(position).charAt(0) == 'w') {
            if (newBoard.get(position + 1).charAt(0) == '_') {
                String TEMP = String.valueOf(newBoard.get(position));
                newBoard.get(position).equals(String.valueOf("_"));
                newBoard.get(position + 1).equals(TEMP);
                return newBoard;
            }  

            if (newBoard.get(position + 2).charAt(0) == '_'  &&  newBoard.get(position + 1).charAt(0) == 'b') {
                String TEMP = newBoard.get(position);
                newBoard.get(position).equals("_");
                newBoard.get(position + 2).equals(TEMP);
                return newBoard;
            }
        }

        if (newBoard.get(position).charAt(0) == 'b') {
            if (newBoard.get(position - 1).charAt(0) == '_') {
                String TEMP = newBoard.get(position);
                newBoard.get(position).equals("_");
                newBoard.get(position - 1).equals(TEMP);
                return newBoard;
            }

            if (newBoard.get(position - 2).charAt(0) == '_'  &&  newBoard.get(position - 1).charAt(0) == 'w') {
                String TEMP = newBoard.get(position);
                newBoard.get(position).equals("_");
                newBoard.get(position - 2).equals(TEMP);
                return newBoard;
            }
        }
        return newBoard;
    }

    private boolean puzzleSolved(ArrayList<String> board) {
        int half = size / 2;

        for (int i = 0; i < half; i++) {
            if (board.get(i).charAt(0) != 'b') {
                return false;
            }
        }

        for (int i = half + 1; i < size; i++) {
            if (board.get(i).charAt(0) != 'w') {
                return false;
            }
        }
        
        showall(board);
        System.out.println("4");

        return true;
    }
    
    public void showall(ArrayList<String> Board) {
        for (String a : Board) {
            System.out.printf("%s ", a);
        }
        System.out.printf("\n");
    }
}
