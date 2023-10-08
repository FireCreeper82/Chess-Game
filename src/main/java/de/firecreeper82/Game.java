package de.firecreeper82;

public class Game {

    public final int[][] board;

    public Game() {
        board = new int[][] {
                {3, 5, 7, 9, 11, 7, 5, 3},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {2, 2, 2, 2, 2, 2, 2, 2},
                {4, 6, 8, 10, 12, 8, 6, 4},
        };

        printBoard(board);


    }

    private static final String reset = "\u001B[0m";
    private static final String black = "\u001B[100m";
    private static final String white = "\u001B[107m";
    private static final String bold = "\u001B[1m";
    private static final String block = "   ";


    private static final String blackForeground = "\u001B[31m";
    private static final String whiteForeground = "\u001B[36m";
    private static final String pawn = " P ";
    private static final String rook = " R ";
    private static final String knight = " N ";
    private static final String bishop = " B ";
    private static final String queen = " Q ";
    private static final String king = " K ";

    private static final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private void printBoard(int[][] board) {

        System.out.print("    ");

        for(int i = 0; i < 8; i++) {
            System.out.print(" " + bold + alphabet[i] + "    ");
        }

        System.out.println();
        System.out.println();

        for (int i = 0; i < board.length; i++) {
            int num = i + 1;

            System.out.print(bold + num + reset + "   ");

            int marker = (i % 2 == 0) ? 1 : 0;

            for (int j = 0; j < board[i].length; j++) {
                int currentPosition = board[i][j];

                String backgroundColor = (marker % 2 == 0) ? black : white;
                System.out.print(backgroundColor + bold);

                String printChar = block;

                String foregroundColor = (currentPosition % 2 != 0) ? blackForeground : whiteForeground;

                switch (currentPosition) {
                    case 1, 2 -> printChar = pawn;
                    case 3, 4 -> printChar = rook;
                    case 5, 6 -> printChar = knight;
                    case 7, 8 -> printChar = bishop;
                    case 9, 10 -> printChar = queen;
                    case 11, 12 -> printChar = king;
                }

                System.out.print(foregroundColor + printChar + reset + "   ");


                marker++;
            }

            System.out.print(bold + num);

            System.out.println();
            System.out.println();
        }

        System.out.print("    ");

        for(int i = 0; i < 8; i++) {
            System.out.print(" " + bold + alphabet[i] + "    ");
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}