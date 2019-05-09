package Connect4;
import java.util.Scanner;
import java.util.Arrays;

public class Connect4 {
    public static boolean isFinished = false;
    public static char currPlayer = 'x';
    public static char[][] table = new char[6][7];
    
    public static void main(String[] args){
        for (int i = 0; i < table.length; i++) { // fill table with blanks
            Arrays.fill(table[i], ' ');
        }
        int previousColumn;
        
        while(!isFinished) {
            printBoard();
            System.out.println(currPlayer + ": Which column? (1-7)");
            int column = 0;
            while(column == 0) {
                try {
                    Scanner input = new Scanner(System.in);
                    if(input.hasNextInt()) {
                        column = input.nextInt();
                    } else {
                        if(input.next().trim().toLowerCase().equals("u")) {
                            // undo
                        } else {
                            throw new ArithmeticException("Unacceptable Input");
                        }
                    }
                    if(column > 7 || column < 1) {
                        throw new ArithmeticException("Not in range");
                    }
                    try {
                        boolean acceptable = false;
                        for(int i = table.length - 1; i >= 0; i--) {
                            if(table[i][column-1] == ' ') {
                                table[i][column-1] = currPlayer;
                                acceptable = true;
                                break;
                            }
                        }
                        if(acceptable == false) {
                            throw new ArithmeticException("Does not fit");
                        }
                    } catch (Exception e) {
                        System.out.println(currPlayer + ": sorry! Column is full. Please choose a different one.");
                        column = 0;
                    }
                } catch (Exception e) {
                    System.out.println(currPlayer + ": Please enter a row from 1-7. Try again.");
                    column = 0;
                }
            }
            previousColumn = column;
            if(currPlayer == 'x') {
                currPlayer = 'o';
            } else {
                currPlayer = 'x';
            }
            
            char winner = ' ', check = ' ';
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[0].length; j++) {
                    if(table[i][j] != ' ') {
                        if(j < table[0].length - 3) { // rows
                            if(table[i][j] == table[i][j+1] && table[i][j+1] == table[i][j+2] && table[i][j+2] == table[i][j+3] && j < table[0].length-3) {
                                winner = table[i][j];
                                break;
                            }
                        }
                        if(i < table.length - 3) { // columns
                            if(table[i][j] == table[i+1][j] && table[i+1][j] == table[i+2][j] && table[i+2][j] == table[i+3][j]) {
                                winner = table[i][j];
                                break;
                            }
                        }
                        if(i < table.length - 3 && j < table[0].length - 3) { // RH Diagonals
                            if(table[i][j] == table[i+1][j+1] && table[i+1][j+1] == table[i+2][j+2] && table[i+2][j+2] == table[i+3][j+3]) {
                                winner = table[i][j];
                                break;
                            }
                        }
                        if(i > 2 && j < table[0].length - 3) { // LH Diagonals
                            if(table[i][j] == table[i-1][j+1] && table[i-1][j+1] == table[i-2][j+2] && table[i-2][j+2] == table[i-3][j+3]) {
                                winner = table[i][j];
                                break;
                            }
                        }
                    }
                }
            }
            if(winner != ' ') {
                isFinished = true;
                printBoard();
                System.out.println("The winner is: " + winner);
            }
        }
    }
    
    public static void printBoard() {
        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table[i].length; j++) {
                System.out.print("[" + table[i][j] + "] ");
            }
            System.out.print("\n");
        }
    }
}
