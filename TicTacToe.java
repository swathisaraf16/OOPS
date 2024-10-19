import java.io.*;
import java.util.*;
public class TicTacToe {
  private char[][] board;
  private int whoseturn;
  private String[] players;
  int movesmade;
  final static private char[] pieces = {'X','O'};
  // Initializes a Tic Tac Toe object.
  public TicTacToe(String player1, String player2) {
    board = new char[3][3];
    for (int i=0;i < 3; i++)
      for (int j=0; j < 3; j++)
        board[i][j] = '_';
    whoseturn = 0;
    movesmade = 0;
    players = new String[2];
    players[0] = player1;
    players[1] = player2;
  }
  // Tries to make a move at row, column. If it's valid the move is made
  // and true is returned. Else nothing is done and false is returned.
  public boolean Move(int row, int column) {
    if ( (board[row][column] == '_') && inbounds(row,column) ) {
      board[row][column] = pieces[whoseturn];
      movesmade++;
      return true;
    }
    else
      return false;
  }
  // Returns true if indexes passed to the method are inbounds.
  public boolean inbounds(int row, int column) {
    if ((row < 0) || (column < 0))
      return false;
    if ((row > 2) || (column > 2))
      return false;
    return true;
  }
  // Changes whose turn it is.
  public void changeturn() {
    whoseturn = (whoseturn + 1)%2;
  }
  // Returns the current player's name.
  public String getCurrentPlayer() {
    return players[whoseturn];
  }
  // Prints out the playing board.
  public void printboard() {
    System.out.println("\t0  1  2");
    for (int i=0; i<3; i++) {
      System.out.print(i+"\t");
      for (int j=0; j<3; j++)
        System.out.print(board[i][j]+"  ");
      System.out.println();
    }
  }
  // Returns a character signifying the winner.
  public char winner() {
	// Check for three X's or O's in a row.
    for (int i=0; i<3; i++)
      if (SameArray(board[i]) && board[i][0] != '_')
        return board[i][0];
	// Check for three X's or O's in a column.
    for (int i=0; i<3; i++)
      if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i]) && board[0][i] != '_')
         return board[0][i];
	// Checks forward diagonal. Here, we know if there are three blanks, no one has one.
    if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]))
         return board[0][0];
	// Checks backward diagonal. Here, we know if there are three blanks, no one has one.
    if ((board[2][0] == board[1][1]) && (board[1][1] == board[0][2]))
         return board[2][0];
    if (movesmade == 9)
      return 'T';
    return '_';
  }
  // Checks to see if all the characters in a single character array are
  // the same.
  private static boolean SameArray(char[] word) {
    char check = word[0];
    for (int i=1; i<word.length; i++)
      if (check != word[i])
        return false;
    return true;
  }
  // Returns the player who's playing piece is the character x.
  public String whosePiece(char x) {
    for (int i=0; i<2; i++)
      if (x == pieces[i])
        return players[i];
    return "Dummy";
  }
  public static void main(String[] args) throws IOException {
    Scanner stdin = new Scanner(System.in);
    // Read in players' names.
    System.out.println("Player #1, enter your name.");
    String name1 = stdin.next();
    System.out.println("Player #2, enter your name.");
    String name2 = stdin.next();
    // Create the TicTacToe object.
    System.out.println("   Let's Start the Game...   ");
    TicTacToe mygame = new TicTacToe(name1, name2);
    // Play as long as there is no winner or tie.
    while (mygame.winner() == '_') {
    int r,c;
      boolean done = false;
    // Read in a move & check if it's valid.
      do {
        mygame.printboard();
        System.out.print(mygame.getCurrentPlayer());            
        System.out.print(", Enter the row(0-2) and column(0-2) ");
	System.out.println("of your move.");
      	r = stdin.nextInt();
      	c = stdin.nextInt();	
        if (!mygame.inbounds(r,c)) 
          System.out.println("Sorry, those are invalid entries.");
        else {
          if (!mygame.Move(r,c))
            System.out.println("Sorry, that square is taken.");
          else
            done = true;
       }  
      } while (!done);
	 // Change who's turn it is.
      mygame.changeturn();
    }
    // Print out a message with the winner.
    mygame.printboard();
    char win = mygame.winner();
    if (win == 'T')
      System.out.println("Both of you played to a tie.");
    else {
      System.out.print("Congratulations, " + mygame.whosePiece(win));
      System.out.println(", you have won the game.");
    }
  }
}