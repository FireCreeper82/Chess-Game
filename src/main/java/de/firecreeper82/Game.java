package de.firecreeper82;

import java.util.Scanner;

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

        play(true);
    }

    private void play(boolean white) {
        print("Your move: ");
        Scanner scanner = new Scanner(System.in);

        String input = scanner.next();
        scanner.close();

        String piece = String.valueOf(input.charAt(0));
        char row = input.charAt(1);
        int column = Integer.parseInt(String.valueOf(input.charAt(2)));

        movePiece(white, piece, row, column);

        play(!white);
    }

    private void movePiece(boolean white, String piece, char row, int column ) {
        int index1 = -1;
        int index2 = -1;

        int pieceIntRepresentation = white ? 1 : 0;
        switch (piece.toLowerCase()) {
            case "k" -> pieceIntRepresentation += 11;
            case "q" -> pieceIntRepresentation += 9;
            case "b" -> pieceIntRepresentation += 7;
            case "n" -> pieceIntRepresentation += 5;
            case "r" -> pieceIntRepresentation += 3;
            case "p" -> pieceIntRepresentation += 1;
            default -> throw new RuntimeException("Invalid Piece");
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == pieceIntRepresentation) {
                    index1 = i;
                    index2 = j;
                    break;
                }
            }
        }

        int rowInt = row - 'A';
        column -= 1;

        if(index1 < 0 || rowInt > 7 || column > 7)
            throw new RuntimeException("Invalid Position");

        board[index1][index2] = 0;
        board[column][rowInt] = pieceIntRepresentation;

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

        println();
        println();

        print("    ");

        for(int i = 0; i < 8; i++) {
            print(" " + bold + alphabet[i] + "    ");
        }

        println();
        println();

        for (int i = 0; i < board.length; i++) {
            int num = i + 1;

            print(bold + num + reset + "   ");

            int marker = (i % 2 == 0) ? 1 : 0;

            for (int j = 0; j < board[i].length; j++) {
                int currentPosition = board[i][j];

                String backgroundColor = (marker % 2 == 0) ? black : white;
                print(backgroundColor + bold);

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

                print(foregroundColor + printChar + reset + "   ");


                marker++;
            }

            print(bold + num);

            println();
            println();
        }

        print("    ");

        for(int i = 0; i < 8; i++) {
            print(" " + bold + alphabet[i] + "    ");
        }

        println();
        println();
    }

    private void print(String s) {
        System.out.print(s);
    }

    private void println() {
        System.out.println();
    }

    public static void main(String[] args) {
        new Game();
    }
}