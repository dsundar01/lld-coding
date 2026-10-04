package com.lldcoding.tictoc;

import lombok.Getter;

@Getter
public class Board {
    private final int id;
    private final char[][] grid;

    public Board(int id) {
        this.id = id;
        this.grid = new char[3][3];
        // Initialize board with empty indicators
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == ' ';
    }

    public void printBoard() {
        System.out.println("\n   0   1   2  <-- Columns"); // Column headers
        System.out.println("  -------------");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " "); // Row header
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + grid[i][j] + " ");
            }
            System.out.println("|");
            System.out.println("  -------------");
        }
    }
}