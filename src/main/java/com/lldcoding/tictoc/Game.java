package com.lldcoding.tictoc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Game {
    private Board board;
    private Player player1;
    private Player player2;

    public void makeMove(Player player, int row, int col) {
        if (!isValidMove(row, col)) {
            throw new IllegalArgumentException("Invalid move! Position out of bounds or cell already occupied.");
        }
        board.getGrid()[row][col] = player.getSymbol();
    }

    public boolean isValidMove(int row, int col) {
        // Bounds check
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false;
        }
        // Cell availability check
        return board.isCellEmpty(row, col);
    }

    public boolean isGameOver() {
        return checkWinner() != null || isBoardFull();
    }

    public boolean isBoardFull() {
        char[][] grid = board.getGrid();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') return false;
            }
        }
        return true;
    }

    public Player checkWinner() {
        char[][] grid = board.getGrid();

        // Check rows & columns
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] != ' ' && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                return grid[i][0] == player1.getSymbol() ? player1 : player2;
            }
            if (grid[0][i] != ' ' && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                return grid[0][i] == player1.getSymbol() ? player1 : player2;
            }
        }

        // Check diagonals
        if (grid[0][0] != ' ' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            return grid[0][0] == player1.getSymbol() ? player1 : player2;
        }
        if (grid[0][2] != ' ' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            return grid[0][2] == player1.getSymbol() ? player1 : player2;
        }

        return null;
    }
}