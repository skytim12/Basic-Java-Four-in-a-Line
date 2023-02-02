/*
   Filename:	Assignment.java
   Programmer:	Tsang Shun Tin
   Class:       IT114105 - 1C
   Student Number: 220014863
   Description:	The Four-in-a-Line game will be run in console mode.  At the beginning, 
                the program uses '0' to represent the space of the grid and shows a seven-column,
                six-row grid.  
                Then it asks players to select a column to drop their discs.
                Check which player win or the game is drawn.
*/

import java.util.*;

public class Assignment {
    static int mTable[][] = new int[6][7]; // array setting
    static int player1in, player2in; // Player input
    static boolean end = false; // End the game
    static Scanner kb = new Scanner(System.in); // Scanner
    static int count = 0; // if even, run player1, if odd, run player2

    public static void main(String[] args) { // run the code

        while (end != true) {
            drawGrid();
            checkdraw();
            if (count % 2 == 0 && end != true) { // if even, run player1, if odd, run player2
                Player1input();
                count++;
            } else if (count % 2 != 0 && end != true) {
                Player2input();
                count++;
            }
        }

    }

    public static void drawGrid() { // DrawGrid
        for (int x = 5; x >= 0; x--) { // DrawGrid rows
            System.out.print(x + " |");
            for (int y = 0; y <= 6; y++) { // DrawGrid columns
                System.out.print(" " + mTable[x][y] + "");

            }
            System.out.println();
        }
        System.out.println("-----------------");
        System.out.print("   "); // DrawGrid Outside columns
        for (int k = 0; k < 7; k++) {
            System.out.print(" " + k + "");
        }
    }

    public static void getError() { // output error message
        if (count % 2 == 0) {
            if (player1in < 0 || player1in > 6) { // getError, input only between 0 to 6
                if (player1in != 9)
                    System.out.print("\nRange of column should be 0 to 6! ");
            } else if (mTable[5][player1in] == 1 || mTable[5][player1in] == 2) { // check the columns is not full
                System.out.print("\nColumn " + player1in + " is full!");
            }
        } else {
            if (player2in < 0 || player2in > 6) { // getError, input only between 0 to 6
                if (player2in != 9)
                    System.out.print("\nRange of column should be 0 to 6! ");
            } else if (mTable[5][player2in] == 1 || mTable[5][player2in] == 2) { // check the columns is not full
                System.out.print("\nColumn " + player2in + " is full!");
            }
        }
    }

    private static void checkdraw() { // check it is not full
        if ((mTable[5][0] == 1 || mTable[5][0] == 2) && (mTable[5][1] == 1 || mTable[5][1] == 2)
                && (mTable[5][3] == 1 || mTable[5][3] == 2) && (mTable[5][4] == 1 || mTable[5][4] == 2)
                && (mTable[5][5] == 1 || mTable[5][5] == 2) && (mTable[5][6] == 1 || mTable[5][6] == 2)) {
            System.out.print("\nThe game is a draw!");
            end = true;

        }
    }

    public static void Player1input() { // Player1 input the value
        do { // Player1 input

            System.out.print("\nPlayer 1 type a column (0-6) or 9 to quit current game: ");
            player1in = kb.nextInt();

            getError(); // getError, input only between 0 to 6

            if (player1in == 9) { // Exit the game
                System.out.print("Bye Bye! ");
                end = true;

            }

        } while ((player1in < 0 || player1in > 6 || mTable[5][player1in] == 1 || mTable[5][player1in] == 2)
                && end != true);

        if (end != true) {
            int check = 0; // check the 2D array is not overlapping
            while (mTable[check][player1in] == 1 || mTable[check][player1in] == 2) {
                check += 1;
            }

            mTable[check][player1in] = 1;// Player1 input x

            checkWin();
        }
    }

    public static void Player2input() { // Playerw input the value
        do { // Player2 input
            System.out.print("\nPlayer 2 type a column (0-6) or 9 to quit current game: ");
            player2in = kb.nextInt();

            getError(); // getError, input only between 0 to 6

            if (player2in == 9) { // Exit the game
                System.out.print("Bye Bye! ");
                end = true;
            }

        } while ((player2in < 0 || player2in > 6 || mTable[5][player2in] == 1 || mTable[5][player2in] == 2)
                && end != true);
        if (end != true) {
            int check = 0; // check the 2D array is not overlapping
            while (mTable[check][player2in] == 1 || mTable[check][player2in] == 2) {
                check += 1;
            }

            mTable[check][player2in] = 2; // Player2 input x

            checkWin();
        }
    }

    public static void checkWin() { // check player is not win

        for (int x = 0; x <= 5; x++) { // check rows
            for (int y = 0; y <= 3; y++) {
                if (mTable[x][y] == 1 && mTable[x][y + 1] == 1 && mTable[x][y + 2] == 1 && mTable[x][y + 3] == 1) {
                    drawGrid();
                    System.out.println("\nPlayer 1 win this game!");
                    end = true;

                } else if (mTable[x][y] == 2 && mTable[x][y + 1] == 2 && mTable[x][y + 2] == 2
                        && mTable[x][y + 3] == 2) {
                    drawGrid();
                    System.out.println("\nPlayer 2 win this game!");
                    end = true;

                }
            }
        }
        for (int x = 0; x <= 6; x++) { // check cloumns
            for (int y = 0; y <= 2; y++) {
                if (mTable[y][x] == 1 && mTable[y + 1][x] == 1 && mTable[y + 2][x] == 1 && mTable[y + 3][x] == 1) {
                    drawGrid();
                    System.out.println("\nPlayer 1 win this game!");
                    end = true;

                } else if (mTable[y][x] == 2 && mTable[y + 1][x] == 2 && mTable[y + 2][x] == 2
                        && mTable[y + 3][x] == 2) {
                    drawGrid();
                    System.out.println("\nPlayer 2 win this game!");
                    end = true;

                }
            }
        }

        for (int x = 3; x <= 5; x++) { // check slash
            for (int y = 0; y <= 3; y++) {
                if (mTable[x][y] == 1 && mTable[x - 1][y + 1] == 1 && mTable[x - 2][y + 2] == 1
                        && mTable[x - 3][y + 3] == 1) {
                    drawGrid();
                    System.out.println("\nPlayer 1 win this game!");
                    end = true;

                } else if (mTable[x][y] == 2 && mTable[x - 1][y + 1] == 2 && mTable[x - 2][y + 2] == 2
                        && mTable[x - 3][y + 3] == 2) {
                    drawGrid();
                    System.out.println("\nPlayer 2 win this game!");
                    end = true;

                }
            }
        }
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 3; y++) {
                if (mTable[x][y] == 1 && mTable[x + 1][y + 1] == 1 && mTable[x + 2][y + 2] == 1
                        && mTable[x + 3][y + 3] == 1) {
                    drawGrid();
                    System.out.println("\nPlayer 1 win this game!");
                    end = true;

                } else if (mTable[x][y] == 2 && mTable[x + 1][y + 1] == 2 && mTable[x + 2][y + 2] == 2
                        && mTable[x + 3][y + 3] == 2) {
                    drawGrid();
                    System.out.println("\nPlayer 2 win this game!");
                    end = true;

                }
            }

        }
    }

}
