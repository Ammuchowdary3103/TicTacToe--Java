import java.util.Scanner;
public class TicTacToe {
    private static char[][] board = new char[3][3];

    // Initialize the game board
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Display the current game board
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Check if there's a winner
    public static char checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (board[i][0] != ' ' &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2]) {
                return board[i][0];
            }
            // Check columns
            if (board[0][i] != ' ' &&
                board[0][i] == board[1][i] &&
                board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }

        // Check diagonals
        if (board[0][0] != ' ' &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) {
            return board[0][0];
        }

        if (board[0][2] != ' ' &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return ' '; // No winner yet
    }

    // Check if the board is full
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Main game loop
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Instructions:");
        System.out.println("Enter your move as row and column numbers (0, 1, or 2). Example: 1 2");

        while (playAgain) {
            initializeBoard();
            char currentPlayer = 'X';
            boolean gameEnded = false;

            while (!gameEnded) {
                printBoard();
                System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                // Validate input
                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Invalid move! Row and column must be between 0 and 2.");
                    continue;
                }

                if (board[row][col] != ' ') {
                    System.out.println("Cell already occupied! Try again.");
                    continue;
                }

                board[row][col] = currentPlayer;

                // Check for winner
                char winner = checkWinner();
                if (winner != ' ') {
                    printBoard();
                    System.out.println("Player " + winner + " wins!");
                    gameEnded = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    gameEnded = true;
                } else {
                    // Switch player
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            if (!response.equals("yes")) {
                playAgain = false;
                System.out.println("Thanks for playing Tic Tac Toe!");
            }
        }

        scanner.close();
    }
}
