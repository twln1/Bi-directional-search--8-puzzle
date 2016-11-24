// Theo Newton 117 5457 COMP316 ASSGN 1
import java.util.*;

public class Node{

    private int[][] board;
    private int x, y, depth;
    private static int numPositions = 0; // We must keep track of the number of board positions generated
    private boolean isForward;

    public Node(int[][] board, boolean isForward, int depth, int x, int y){
        this.board = board;
        this.isForward = isForward;
        this.depth = depth;
        this.x = x;
        this.y = y;
        numPositions++;
        /*
        // Search through the board for the 'hole' (0) and set the coordinates
	    for(int y = 0; y < 5; y++){				
            for(int x = 0; x < 5; x++){
                 if(board[x][y] == 0){
                 this.x = x;
                 this.y = y;
                }
             }
        }
        */
    }


    // Returns how deep in the search we are
    public int depth(){
        return depth;
    }

    // If true, search is going down the tree
    public boolean isForward(){
        return isForward;
    }

    // This will make a copy of the board
    private int[][] copyBoard(){
       /*  int[][] _board = new int[5][5];
        for(int m = 0; m < 5; m++){
            for(int n = 0; n < 5; n++){
                _board[m][n] = board[m][n];
            }
        }
        return _board; */
        return new int[][]{board[0].clone(), board[1].clone(),board[2].clone(), board[3].clone(),board[4].clone()};
    }

    // Methods for directional movement:
    public Node up(){
        // We are at the top of the board, no rows are above
        if(y == 0)
            return null;
        else{
            int[][] _board = copyBoard();
            _board[x][y] = board[x][y - 1];
            _board[x][y - 1] = 0;
            return new Node(_board, isForward, depth + 1, x, y-1); // We need to return the new board
        }
        
    }
    public Node down(){
        // We are at the bottom of the board, no rows are below
        if(y == 4)
            return null;
        else{
            int[][] _board = copyBoard();
            _board[x][y] = board[x][y + 1];
            _board[x][y + 1] = 0;
            return new Node(_board, isForward, depth + 1, x, y+ 1); // We need to return the new board
        }
    }
    public Node left(){
        // We are at the left side of the board, moving left is invalid
        if(x == 0)
            return null;
        else{
            int[][] _board = copyBoard();
            _board[x][y] = board[x -1 ][y];
            _board[x - 1][y] = 0;
            return new Node(_board, isForward, depth + 1, x -1, y); // We need to return the new board
        }
    }
    public Node right(){
        // We are at the right side of the board, moving right will fall off the board
        if(x == 4)
            return null;
        else{
            int[][] _board = copyBoard();
            _board[x][y] = board[x + 1 ][y];
            _board[x + 1][y] = 0;
            return new Node(_board, isForward, depth + 1, x+1, y); // We need to return the new board
        }
    }


    // Return the number of postions the board has visited
    public static int numPositions(){
        int temp = numPositions;
        numPositions = 0;
        return temp;
    }
    
    // Method that checks if we have already visited the board
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(o == this)
            return true;
        if(o instanceof Node){
            Node temp = (Node)o;
            if(this.hashCode() != temp.hashCode())
                return false;
            for(int x = 0; x < 5; x++){
                if(!(Arrays.equals(this.board[x], temp.board[x])))
                    return false;
            }
            return true;
        }
        else
            return false;
        
    }

    private int hash;

    // Method to get the hashCode of the board
    public int hashCode(){
       // return Arrays.deephashCode(board);

        // Optimised hashCode as Arrays.deephashCode refused to work
        if(hash == 0){
        
            for(int i = 0; i < 5; i ++)for(int j = 0; j < 5; j++)hash += board[i][j]<<(5*j);
        
        }
        return hash;
        
    }
  
}
