package project_1_6413xxx;

import java.util.*;
import java.io.*;

class Marble {

    private ArrayList<String> Rungsi = new ArrayList<String>();
    private ArrayList<ArrayList> prt = new ArrayList<ArrayList>();
    private int mode = 0; //0 = manual 1 = auto
    private int size = 0;

    public Marble() {
    }

    public Marble(int n) {
        size = (2 * n) + 1;
        Rungsi = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            Rungsi.add("w" + i);
        }
        Rungsi.add("_");
        for (int i = 0; i < n; i++) {
            Rungsi.add("b" + i);
        }
        System.out.printf("\nInitial   >>  ");
        this.showall(Rungsi);
        System.out.printf("\n");
    }

    public void start() {
        if (this.solvable(Rungsi)) {
            for (int i = prt.size() - 1; i >= 0; i--) {
                int j = prt.size() - i;
                System.out.printf("Auto  %d  >>  ", j);
                showall(prt.get(i));
            }
            System.out.printf("\nSUCCESS\n");
        } else {
            System.out.printf("No solution !!!\n\n");
        }
        System.out.printf("================================");
    }

    private boolean solvable(ArrayList<String> board) {
        if (puzzleSolved(board)) {
            return true;
        }
        for (int position = 0; position < size; position++) {
            if (canMove(board, position)) {
                ArrayList<String> newBoard = makeMove(board, position);
                //showall(newBoard);

                if (solvable(newBoard)) {
                    prt.add(newBoard);
                    //showall(newBoard);
                    return true;
                }

                /*System.out.println("NOT SOLVABLE: ");
                System.out.println("Position: " + position);
                showall(newBoard);*/
                //showall(newBoard);
            }
        }
        return false;
    }

    private boolean canMove(ArrayList<String> board, int position) {

        if (board.get(position).charAt(0) == '_') {
            return false;
        }

        if (board.get(position).charAt(0) == 'w') {
            if ((position + 1) < size && board.get(position + 1).charAt(0) == '_') {
                return true;
            }

            if ((position + 2) < size && board.get(position + 2).charAt(0) == '_' && board.get(position + 1).charAt(0) == 'b') {
                return true;
            }
        }

        if (board.get(position).charAt(0) == 'b') {
            if ((position - 1) >= 0 && board.get(position - 1).charAt(0) == '_') {
                return true;
            }

            if ((position - 2) >= 0 && board.get(position - 2).charAt(0) == '_' && board.get(position - 1).charAt(0) == 'w') {
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
                newBoard.set(position, String.valueOf("_"));
                newBoard.set(position + 1, TEMP);
                return newBoard;
            }

            if (newBoard.get(position + 2).charAt(0) == '_' && newBoard.get(position + 1).charAt(0) == 'b') {
                String TEMP = newBoard.get(position);
                newBoard.set(position, "_");
                newBoard.set(position + 2, TEMP);
                return newBoard;
            }
        }

        if (newBoard.get(position).charAt(0) == 'b') {
            if (newBoard.get(position - 1).charAt(0) == '_') {
                String TEMP = newBoard.get(position);
                newBoard.set(position, "_");
                newBoard.set(position - 1, TEMP);
                return newBoard;
            }

            if (newBoard.get(position - 2).charAt(0) == '_' && newBoard.get(position - 1).charAt(0) == 'w') {
                String TEMP = newBoard.get(position);
                newBoard.set(position, "_");
                newBoard.set(position - 2, TEMP);
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
        return true;
    }

    public void showall(ArrayList<String> Board) {
        for (String a : Board) {
            System.out.printf("%s ", a);
        }
        System.out.printf("\n");
    }
}
