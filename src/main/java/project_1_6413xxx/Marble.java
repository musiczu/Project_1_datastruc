/*6413110 Mr.Watcharsak Prommanee
6413112 Mr.Sasit Srirat
6413210 Mr.Kawin Kengkate
6413223 Mr.Ravipol Chayeraksa
UPDATE 17/03/23 15.44 */

package project_1_6413xxx;

import java.util.*;
import java.io.*;

class Marble {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private ArrayList<String> initial = new ArrayList<String>();
    private ArrayList<ArrayList> prt = new ArrayList<ArrayList>();
    private ArrayList<String> temp;
    private int mode = 0; // 0 = manual, 1 = auto
    private int step = 0;
    private int size = 0;

    public Marble() {
    }

    public Marble(int n) {
        size = (2 * n) + 1;
        for (int i = 0; i < n; i++) {
            initial.add("w" + i);
        }
        initial.add("_");
        for (int i = 0; i < n; i++) {
            initial.add("b" + i);
        }
        System.out.printf("\nInitial   >>  ");
        this.showall(initial);
        System.out.printf("\n");
    }

    public void start() {
        manualpilot(initial);
    }

    public boolean manualpilot(ArrayList<String> board) {
        if (puzzleSolved(board)) {
            System.out.printf("\nSUCCESS\n");
            return true;
        }
        Scanner In = new Scanner(System.in);
        // exception in case input invalid maaarble such as we have only w0 w1 but we
        // input w2
        temp = board;
        boolean checkedmode = false; // manual
        while (!checkedmode) {
            System.out.print("\nStep  " + ++step + " >>  Enter marble ID or A to switch to auto mode =     ");
            String input = In.nextLine();
            input = input.toLowerCase();
            switch (input) {
                case "a":
                    checkedmode = true;
                    mode = 1;
                    autopilot(temp);
                    break;
                default:
                    try {
                        int position = board.indexOf(input);
                        if (canMove(board, position) == false) {
                            System.out.println("==>> Cannot move -> "  + ANSI_RED + input + ANSI_RESET + " <<==");
                            manualpilot(board);
                        } else {
                            ArrayList<String> newBoard = makeMove(board, position);
                            System.out.print("            ");
                            showall(newBoard);
                            if (manualpilot(newBoard)) {
                                prt.add(newBoard);
                                return true;
                            }
                        }
                        return false;
                    } catch (Exception e) {
                        System.out.println("==>> We do not have -> " + ANSI_YELLOW + input + ANSI_RESET + " <<==" );
                        step--;
                    }
                    break;
            }
        }
        return false;
    }

    public void autopilot(ArrayList<String> board) {
        System.out.printf("\n");
        if (this.solvable(board)) {
            for (int i = prt.size() - 1; i >= 0; i--) {
                int j = prt.size() - i;
                System.out.printf("\n*** Auto  %d ***", j);
                showall(prt.get(i));
            }
            System.out.printf("\nSUCCESS\n");
        } else {
            System.out.printf("No solution !!!\n\n");
        }
        System.out.printf("================================================================");
    }

    private boolean solvable(ArrayList<String> board) {
        if (puzzleSolved(board)) {
            return true;
        }
        for (int position = 0; position < size; position++) {
            if (canMove(board, position)) {
                ArrayList<String> newBoard = makeMove(board, position);

                if (solvable(newBoard)) {
                    prt.add(newBoard);
                    return true;
                }
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
            if ((position + 2) < size && board.get(position + 2).charAt(0) == '_'
                    && board.get(position + 1).charAt(0) == 'b') {
                return true;
            }
        }

        if (board.get(position).charAt(0) == 'b') {
            if ((position - 1) >= 0 && board.get(position - 1).charAt(0) == '_') {
                return true;
            }

            if ((position - 2) >= 0 && board.get(position - 2).charAt(0) == '_'
                    && board.get(position - 1).charAt(0) == 'w') {
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
                if (mode == 0) {
                    System.out.println("==>> Move right  " + ANSI_GREEN + newBoard.get(position) + ANSI_RESET + " <<==");
                }
                String TEMP = String.valueOf(newBoard.get(position));
                newBoard.set(position, String.valueOf("_"));
                newBoard.set(position + 1, TEMP);
                return newBoard;
            }

            if (newBoard.get(position + 2).charAt(0) == '_' && newBoard.get(position + 1).charAt(0) == 'b') {
                if (mode == 0) {
                    System.out.println("==>> Jump right  " + ANSI_GREEN + newBoard.get(position) + ANSI_RESET + " <<==");
                   // System.out.println(" ==>> Jump right  " + newBoard.get(position) + " <<==");
                }
                String TEMP = newBoard.get(position);
                newBoard.set(position, "_");
                newBoard.set(position + 2, TEMP);
                return newBoard;
            }
        }

        if (newBoard.get(position).charAt(0) == 'b') {
            if (newBoard.get(position - 1).charAt(0) == '_') {
                if (mode == 0) {
                    System.out.println("==>> Move left   " + ANSI_GREEN + newBoard.get(position) + ANSI_RESET + " <<==");
                    //System.out.println(" ==>> Move left   " + newBoard.get(position) + " <<==");
                }
                String TEMP = newBoard.get(position);
                newBoard.set(position, "_");
                newBoard.set(position - 1, TEMP);
                return newBoard;
            }

            if (newBoard.get(position - 2).charAt(0) == '_' && newBoard.get(position - 1).charAt(0) == 'w') {
                if (mode == 0) {
                    System.out.println("==>> Jump left   " + ANSI_GREEN + newBoard.get(position) + ANSI_RESET + " <<==");
                    //System.out.println(" ==>> Jump left   " + newBoard.get(position) + " <<==");
                }
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
        System.out.printf("\n");
        for (int i = 0; i < size * 5; i++) {
            System.out.printf("-");
        }
        System.out.printf("\n");
        for (String a : Board) {
            System.out.printf("| %s ", a);
        }
        System.out.printf("|\n");
        for (int i = 0; i < size * 5; i++) {
            System.out.printf("-");
        }
        System.out.printf("\n");
    }

}
