// Student name: Cheuk Hang Leung (Eric)
// McGill ID: 260720788

import java.util.Scanner;
import java.util.Random;

    public class TicTacToe
    {
        public static void main (String[] args)
        {
            play();
        }

        public static char[][] createBoard (int n)  // Part A
        {
            char[][] board = new char[n][n];
            for (int i = 0; i < board.length; i++)
            {
                for (int j = 0; j < board[i].length; j++)
                {
                    board[i][j] = ' ';
                }
            }
            return board;
        }

        public static void displayBoard (char[][] gameBoard)  // (Part B) A method to print out a game board and updates every round
        {
            for (int i = 0; i < gameBoard.length; i++)
            {
                for (int j = 0; j < gameBoard[i].length; j++)
                {
                    System.out.print("+-");
                }
                System.out.print("+");
                System.out.println();

                for (int j = 0; j < gameBoard[i].length; j++)
                {
                    System.out.print ("|" + gameBoard[i][j]);
                }
                System.out.print("|");
                System.out.println();
            }
            for (int j = 0; j < gameBoard[0].length; j++)
            {
                System.out.print("+-");
            }

            System.out.print("+");
            System.out.println();
        }

        public static void writeOnBoard (char[][] board, char stuffToWrite, int x, int y)  /* (Part C) This method prevents the errors while user
   inputting an element. */
        {
            if (x >= board.length || y >= board.length)
            {
                throw new IllegalArgumentException ("The coordinates you input do not exist.");
            }

            if (board[x][y] != ' ')
            {
                throw new IllegalArgumentException ("The cell already has a character");
            }
            board[x][y] = stuffToWrite;
        }

        public static void getUserMove (char[][] board)  // (Part D) This method simply for getting a valid input from the user.
        {
            Scanner player1Move = new Scanner(System.in);
            System.out.println ("Please type in your move. Be sure to put a space between the coordinates, thank you.");
            int row = player1Move.nextInt();
            int column = player1Move.nextInt();
            while (row >= board.length || column >= board.length || board[row][column] != ' ')
            {
                System.out.println ("Your inputs are invalid! Please re-enter your inputs again.");
                player1Move.nextLine();

                row = player1Move.nextInt();
                column = player1Move.nextInt();
            }
            writeOnBoard (board, 'x', row, column);
        }

        public static boolean checkForObviousMove (char[][] board) // (Part E)
        {
            return (check(board, 'o') || check(board, 'x'));
        }

        public static boolean check(char[][] board, char role)  // helper method for checkForObviousMove
        {
            int counter = 0;

            // Columns
            for (int j = 0; j < board.length; j++)
            {
                if (board[0][j] == ' ')  // in case the first cell is empty
                {
                    counter = 0;
                    for (int i = 1; i < board.length; i++)  // start from the second cell along the first column
                    {
                        if (board[i][j] == role)
                        {
                            counter++;
                        }
                    }
                    if (counter == (board.length - 1))
                    {
                        writeOnBoard (board, 'o', 0, j);
                        return true;
                    }
                }
                else if (board[0][j] == role)  // Check if the first element is 'o' or not.
                {
                    counter = 1;
                    int index = -1;  // The index represents where the empty is. In case all cells filled in along the column, we indicate index -1 at first.
                    for (int i = 1; i < board.length; i++)
                    {
                        if (board[i][j] == board[0][j])
                        {
                            counter++;
                        }
                        if (board[i][j] == ' ')
                        {
                            index = i;
                        }
                    }
                    if (counter == (board.length - 1) && index != -1)
                    {
                        writeOnBoard (board, 'o', index, j);
                        return true;
                    }
                }
            }

            // Rows
            for (int j = 0; j < board.length; j++)
            {
                if (board[j][0] == ' ')
                {
                    counter = 0;
                    for (int i = 1; i < board.length; i++)
                    {
                        if (board[j][i] == role)
                        {
                            counter++;
                        }
                    }
                    if (counter == (board.length - 1))
                    {
                        writeOnBoard (board, 'o', j, 0);
                        return true;
                    }
                }
                else if (board[j][0] == role)
                {
                    counter = 1;
                    int index = -1;
                    for (int i = 1; i < board.length; i++)
                    {
                        if (board[j][i] == board[j][0])
                        {
                            counter++;
                        }
                        if (board[j][i] == ' ')
                        {
                            index = i;
                        }
                    }
                    if (counter == (board.length - 1) && index != -1)
                    {
                        writeOnBoard (board, 'o', j, index);
                        return true;
                    }
                }
            }

            // Diagonal
            if (board[0][0] == ' ')
            {
                counter = 0;
                for (int i = 1; i < board.length; i++)
                {
                    if (board[i][i] == role)
                    {
                        counter++;
                    }
                }
                if (counter == (board.length - 1))
                {
                    writeOnBoard (board, 'o', 0, 0);
                    return true;
                }
            }
            else if (board[0][0] == role)
            {
                counter = 1;
                int index = -1;

                for (int i = 1; i < board.length; i++)
                {
                    if (board[i][i] == role)
                    {
                        counter++;
                    }
                    else if (board[i][i] == ' ')
                    {
                        index = i;
                    }
                }
                if (counter == (board.length - 1) && index != -1)
                {
                    writeOnBoard (board, 'o', index, index);
                    return true;
                }
            }

            // Anti Diagonal
            if (board[0][board.length - 1] == ' ')
            {
                counter = 0;
                int i = 1, j = board.length - 2;
                while (i < board.length && j >= 0)
                {
                    if (board[i][j] == role)
                    {
                        counter++;
                    }
                    i++;
                    j--;
                }
                if (counter == (board.length - 1))
                {
                    writeOnBoard (board, 'o', 0, (board.length - 1));
                    return true;
                }
            }
            else if (board[0][board.length - 1] == role)   // The Anti-Diagonal starts from top right cell to the bottom left.Coordinate for that would be (0, array.length - 1)
            {
                counter = 1;
                int indexRow = -1;
                int indexColumn = -1;

                int i = 1, j = board.length - 2;
                while (i < board.length && j >= 0)
                {
                    if (board[i][j] == role)
                    {
                        counter++;
                    }
                    if (board[i][j] == ' ')
                    {
                        indexRow = i;
                        indexColumn = j;
                    }
                    i++;
                    j--;
                }
                if (counter == (board.length - 1) && indexRow != -1 || indexColumn != -1)
                {
                    writeOnBoard (board, 'o', indexRow, indexColumn);
                    return true;
                }
            }

            return false;
        }

        public static void getAIMove (char[][] board)  // (Part F) When there isn't an obvious move, then this method generates a valid random coordinate for the AI.
        {
            Random ranNum = new Random();

            System.out.println ("AI made its move: ");
            boolean hasObviousMove = checkForObviousMove(board);
            if (!hasObviousMove)
            {
                while (true)
                {
                    int rowMoveAI = ranNum.nextInt(board.length);
                    int columnMoveAI = ranNum.nextInt(board.length);
                    if (board[rowMoveAI][columnMoveAI] == ' ')
                    {
                        writeOnBoard(board, 'o', rowMoveAI, columnMoveAI);
                        break;
                    }
                }
            }
        }

        public static char checkForWinner (char[][] board)  // (Part G) Check if there is a winner.
        {
            if (checkColumnForWinner (board) == 'o' || checkRowForWinner (board) == 'o'
                    || checkDiagonalForWinner (board) == 'o' || checkAntiDiagonalForWinner (board) == 'o')
            {
                return 'o';
            }
            else if (checkColumnForWinner (board) == 'x' || checkRowForWinner (board) == 'x'
                    || checkDiagonalForWinner (board) == 'x' || checkAntiDiagonalForWinner (board) == 'x')
            {
                return 'x';
            }
            return ' ';
        }

        public static char checkColumnForWinner (char[][] board)  // Helper Method for Part G (check column for winner)
        {
            int counter;
            int oCounter;
            for (int j = 0; j < board.length; j++)
            {
                oCounter = 0;
                counter = 0;
                for (int i = 0; i < board.length; i++)
                {
                    if (board[i][j] == 'o')
                    {
                        oCounter++;
                    }
                    if (board[i][j] == 'x')
                    {
                        counter++;
                    }
                }
                if (counter == board.length)
                {
                    return 'x';
                }
                if (oCounter == board.length)
                {
                    return 'o';
                }
            }
            return ' ';
        }

        public static char checkRowForWinner (char[][] board) // Helper method that checks row for winner
        {
            int counter;
            int oCounter;
            for (int j = 0; j < board.length; j++)
            {
                oCounter = 0;
                counter = 0;
                for (int i = 0; i < board.length; i++)
                {

                    if (board[j][i] == 'o')
                    {
                        oCounter++;
                    }
                    if (board[j][i] == 'x')
                    {
                        counter++;
                    }
                }
                if (oCounter == board.length)
                {
                    return 'o';
                }
                if (counter == board.length)
                {
                    return 'x';
                }
            }
            return ' ';
        }

        public static char checkDiagonalForWinner (char[][] board)  // Helper method to check diagonal for winner
        {
            int counter = 0;
            int oCounter = 0;
            for (int i = 0; i < board.length; i++)
            {
                if (board[i][i] == 'o')
                {
                    oCounter++;
                }
                if (board[i][i] == 'x')
                {
                    counter++;
                }
            }
            if (counter == board.length)
            {
                return 'x';
            }
            if (oCounter == board.length)
            {
                return 'o';
            }
            return ' ';
        }

        public static char checkAntiDiagonalForWinner (char[][] board)  // Helper method that checks the anti-diagonal winner
        {
            int counter = 0;
            int oCounter = 0;
            int i = 0, j = board.length - 1;

            while (i < board.length && j >= 0)
            {
                if (board[i][j] == 'o')
                {
                    oCounter++;
                }
                if (board[i][j] == 'x')
                {
                    counter++;
                }
                i++;
                j--;
            }
            if (oCounter == board.length)
            {
                return 'o';
            }
            if (counter == board.length)
            {
                return 'x';
            }
            return ' ';
        }

        public static void play ()  // (Part H) To initiate the game Tic Tac Toe
        {
            Scanner scan = new Scanner (System.in);
            System.out.println ("Hello there:) Please enter your name below: ");

            String userName = scan.nextLine();
            System.out.println ("Nice to meet you, " + userName + "!");

            int dimensionOfBoard;
            do
            {
                System.out.println ("Please enter the dimension of your game board as a positive integer: ");
                while (!scan.hasNextInt())
                {
                    System.out.println ("Your input is not a number. Please enter a positive integer:");
                    scan.next();
                }
                dimensionOfBoard = scan.nextInt();
            } while (dimensionOfBoard <= 0);

            char[][] board = createBoard (dimensionOfBoard);
            Random ranInt = new Random ();
            int coin = ranInt.nextInt(2);
            System.out.println ("The result of the coin toss is: " + coin);
            if (coin == 1)
            {
                System.out.println ("AI has the first move.");
                char winner;
                while (true)
                {
                    getAIMove (board);
                    displayBoard (board);
                    winner = checkForWinner(board);
                    if (winner == 'o' || winner == 'x' || isFull(board))
                        break;
                    getUserMove (board);
                    displayBoard (board);
                    winner = checkForWinner(board);
                    if (winner == 'o' || winner == 'x' || isFull(board))
                        break;
                }
                winner = checkForWinner(board);
                if (winner == 'x')
                {
                    System.out.println ("Game Over!");
                    System.out.println ("Congratulations! You won the game!!");
                }
                else if (winner == 'o')
                {
                    System.out.println ("Game Over!");
                    System.out.println ("You lost:(");
                }
                else
                {
                    System.out.println ("Game Over!");
                    System.out.println ("Tie game.");
                }
            }
            else
            {
                System.out.println ("You have the first move");
                char winner;
                while (true)
                {
                    getUserMove (board);
                    displayBoard (board);
                    winner = checkForWinner(board);
                    if (winner == 'o' || winner == 'x' || isFull(board))
                        break;
                    getAIMove (board);
                    displayBoard (board);
                    winner = checkForWinner(board);
                    if (winner == 'o' || winner == 'x' || isFull(board))
                        break;
                }
                winner = checkForWinner(board);
                if (winner == 'x')
                {
                    System.out.println ("Game Over!");
                    System.out.println ("Congratulations! You won the game!!");
                }
                else if (winner == 'o')
                {
                    System.out.println ("Game Over!");
                    System.out.println ("You lost!");
                }
                else
                {
                    System.out.println ("Game Over!");
                    System.out.println ("Tie game!");
                }
            }
        }

        public static boolean isFull(char[][] board) {
            for (int i = 0; i < board.length; i++)
            {
                for (int j = 0; j < board.length; j++)
                {
                    if (board[i][j] == ' ')
                    {
                        return false;
                    }
                }
            }
            return true;
        }
    }
