// Theo Newton 117 5457 COMP316 ASSGN 1

import java.util.*;
import java.io.*;

public class Blind{

	
	private String direction;
	private static HashMap<Node, Node> forward = new HashMap<>(), backward = new HashMap<>();
	private static ArrayDeque<Node> q = new ArrayDeque<>();
	private static Node goal;

	public static void main(String[] args) throws IOException{
		if(args.length != 1){
			System.out.println("Must give file name");
		}
		// Scanner to read in the boards
		Scanner s = new Scanner(new FileReader(args[0]));
		ArrayList<int[][]> boards = new ArrayList<>(100);
		int count = 0;
		// keep going for 100 boards
		while(count++<100){
		//Skip 2 lines as per positions.txt formatting
			s.nextLine();
			s.nextLine();
			int[][] board = new int[5][5];
			// Collect the board rows/columns
			for(int y = 0; y < 5; y++){
				String[] line = s.nextLine().split(" ");
				for(int x = 0; x < 5; x++){
					board[x][y] = Integer.parseInt(line[x]);
				}
			}
			// Add the board to the list of all boards
			boards.add(board);
			s.nextLine();
		}
		// Set the goal board
		int goalBoard[][] =	{{0, 5,10,15,20,},
							 {1, 6,11,16,21},
							 {2, 7,12,17,22},
							 {3, 8,13,18,23},
							 {4, 9,14,19,24}};
	
		Node goal = new Node(goalBoard, false, 0, 0, 0);

		for(int b =0; b<100;b++){					
		    initialise();
		    int[][] tempArray = boards.get(b);
		    Node start = null;
		    here:
		    for(int y = 0; y < 5; y++){
				
				for(int x = 0; x < 5; x++){
					if(tempArray[x][y] == 0){
					 start = new Node(boards.get(b), true, 0, x, y);
					 break here;
					}
				}
			}
		   
		    // Add the start and goal states to our Deque    
			q.add(start);
			q.add(goal);
			// Add the nodes to our hashmap		
		    forward.put(start, start);
		    backward.put(goal,goal);
		    // Store the runtime for the current board
		    long runTime = System.nanoTime();		
		    
		    /*
		    
		        BOARD WILL NOT TIMEOUT
		    
		    */   
		    while(true){
			    Node temp = q.removeFirst();	    
			    if(temp.isForward()){
			    	// If our backward hashmap has the first node in the queue
			    	if(backward.containsKey(temp)){

			    		// PRINT RESULT
			    		runTime = System.nanoTime()-runTime;

			    		// print: Board Number , Runtime , depth , boards visited
			    		System.out.printf(" Board: %-3d Time(ms): %-6f Depth: %-7d Positions: %-6d \n", b+1,  (runTime/1000000.0 ), (temp.depth()+backward.get(temp).depth()), Node.numPositions() );
			    		//System.out.printf("%-3d,%-6f,%-5d,%-6d\n", b+1, (runTime/1000000.0 ), (temp.depth()+backward.get(temp).depth()), Node.numPositions() );
			    		break;
			    	}

			    	// Directional movements which call the appropriate methods in the Node.class
			    	Node child = temp.up();
			    	if(child != null && !forward.containsKey(child)){
			    		forward.put(child, child);
			    		q.add(child);
			    	}
			    	child = temp.down();
			    	if(child != null  && !forward.containsKey(child)){
			    		forward.put(child, child);
			    		q.add(child);
			    	}
			    	child = temp.left();
			    	if(child != null  && !forward.containsKey(child)){
			    		forward.put(child, child);
			    		q.add(child);
			    	}
			    	child = temp.right();
			    	if(child != null  && !forward.containsKey(child)){
			    		forward.put(child, child);
			    		q.add(child);
			    	}
			    }
			    else{
			    	// If our forward hashmap has the first node in the queue
			    	if(forward.containsKey(temp)){
			    		// PRINT RESULT
			    		runTime = System.nanoTime()-runTime;
			    		System.out.printf(" Board: %-3d Time(ms): %-6f Depth: %-7d Positions: %-6d \n", b+1,  (runTime/1000000.0 ), (temp.depth()+forward.get(temp).depth()), Node.numPositions() );
			    		//System.out.printf("%-3d,%-6f,%-5d,%-6d\n", b+1, (runTime/1000000.0 ), (temp.depth()+forward.get(temp).depth()), Node.numPositions() );
			    		break;
			    	}

			    	// Directional movements which call the appropriate methods in the Node.class
			    		Node child = temp.up();
			    	if(child != null  && !backward.containsKey(child)){
			    		backward.put(child, child);
			    		q.add(child);
			    	}
			    	child = temp.down();
			    	if(child != null  && !backward.containsKey(child)){
			    		backward.put(child, child);
			    		q.add(child);
			    	}
			    	child = temp.left();
			    	if(child != null  && !backward.containsKey(child)){
			    		backward.put(child, child);
			    		q.add(child);
			    	}
			    	child = temp.right();
			    	if(child != null  && !backward.containsKey(child)){
			    		backward.put(child, child);
			    		q.add(child);
			    	}
			    }			   
			}

		}			
	}

	// Method to initialise queue and hashmaps in order to search
	private static void initialise(){		
	    q.clear();
	    forward.clear();
	    backward.clear();
	}

	/*
		Method to print the board, for testing purposes

	public static void printBoard(int[][] b){
		for(int y = 0; y < 5; y++){
				
				for(int x = 0; x < 5; x++){
					System.out.print(b[x][y] + " ");
				}
				System.out.println();
			}
	}
	*/
	
	
}

