package com.lldcoding.tictoc;

import java.util.Scanner;

public class TicTacToeFacade {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Initialize Players
        Player p1 = new Player("Alice", 'X');
        Player p2 = new Player("Bob", 'O');

        // 2. System creates a new game and board
        Board board = new Board(1);
        Game game = new Game(board, p1, p2);

        System.out.println("=== Game Started: " + p1.getName() + " (X) vs " + p2.getName() + " (O) ===");
        board.printBoard();

        Player currentPlayer = p1;

        // Game Loop
        while (!game.isGameOver()) {
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
            System.out.print("Enter row (0-2) and column (0-2): ");

            try {
                System.out.print("Enter ROW (0, 1, or 2): ");
                int row = scanner.nextInt();
                System.out.print("Enter COLUMN (0, 1, or 2): ");
                int col = scanner.nextInt();
                System.out.println("--> You selected position: [" + row + "][" + col + "]\n");

                // 3 & 4. Make move (validates move internally)
                game.makeMove(currentPlayer, row, col);
                board.printBoard();

                // Toggle turn
                currentPlayer = (currentPlayer == p1) ? p2 : p1;

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + " Try again.\n");
            } catch (Exception e) {
                System.out.println("Invalid input. Enter numbers between 0 and 2.");
                scanner.nextLine(); // Clear buffer
            }
        }

        // Output Result
        Player winner = game.checkWinner();
        if (winner != null) {
            System.out.println("🎉 Congratulations " + winner.getName() + "! You won!");
        } else {
            System.out.println("🤝 It's a draw!");
        }

        scanner.close();
    }
}