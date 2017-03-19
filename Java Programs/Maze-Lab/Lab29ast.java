// Lab29ast.java
// This is the student version of the Lab29a assignment.
// Completing this file, as is, is the 100 point version.
// For 80 points you will be given the <getMove> code.


import java.util.*;


public class Lab29ast
{
	public static void main(String args[])
	{
		System.out.println("\nLab 29a 80/100 Point Version\n");
		Scanner input = new Scanner(System.in);
		System.out.print("Enter random starting seed  ===>>  ");
		int seed = input.nextInt();

		Maze maze = new Maze(seed);
		maze.displayMaze();
		maze.solveMaze();
		maze.displayMaze();
		maze.mazeSolution();
	}
}


class Maze
{

	private char mat[][];			// 2d character array that stores the maze display
	private Coord currentMove;		// object that stores current maze position
	private Stack<Coord> visitStack;		// stack that stores location that have been visited
	private int moves;


	class Coord
	// Coord is a class that stores a single maze location.
	{
		private int rPos;
		private int cPos;
		public Coord (int r, int c) 		{ rPos = r; cPos = c; }
		public boolean isFree() 			{ return (rPos == 0 && cPos == 0); }
		public void setPos(int r, int c) 	{ rPos = r; cPos = c; }
		public int getRow()					{ return rPos;}
		public int getCol()					{ return cPos;}
	}


	public Maze(int seed)
	// constructor which generates the random maze, random starting location
	// and initializes Maze class values.  If the random value equals 0 the maze
	// store an 'X' otherwise it store an 'O' in the maze.
	{
		Random random = new Random(seed);
		int startRow, startCol;
		mat = new char[12][12];
		for (int r = 0; r < 12; r++)
			for (int c = 0; c < 12; c++)
			{
				if (r == 0 || c == 0 || r == 11 || c == 11)
					mat[r][c] = 'X';
				else
				{
					int rndInt = random.nextInt(2);
					if (rndInt == 0)
						mat[r][c] = 'X';
					else
						mat[r][c] = 'O';
				}
			}
		mat[0][0] = 'O';
		startRow = random.nextInt(12);
		startCol = 11;
		mat[startRow][startCol] = '.';
		visitStack = new Stack<Coord>();
		currentMove = new Coord(startRow,startCol);
		moves=0;
	}


	public void displayMaze()
	// displays the current maze configuration
	{
		System.out.println("\nRANDOM MAZE DISPLAY\n");
		for (int r = 0; r < 12; r++)
		{
			for (int c = 0; c < 12; c++)
				System.out.print(mat[r][c] + "  ");
			System.out.println();
		}
		System.out.println();
		pause();
	}


	public void solveMaze()
	// This methods solves the maze with private helper method <getMove>.
	// A loop is needed to repeat getting new moves until either a maze solution
	// is found or it is determined that there is no way out off the maze.
	{
		System.out.println("\n>>>>>   WORKING  ....  SOLVING MAZE   <<<<<\n");
		int k = 0;Coord temp = new Coord(0,0);boolean tempSet = false;
		while(currentMove.getCol()!=0 && !(moves>300))
		{
			if(k%10==0){
				temp = new Coord(currentMove.getRow(),currentMove.getCol());
				tempSet =true;}
			if(k%10==9 && tempSet){
				if((temp.getCol()==currentMove.getCol())&&(temp.getRow()==currentMove.getRow()))
						return;}
			int setBack = 0;
			while(getMove()==false && setBack<visitStack.size())
				undoMove(setBack++);
			if(currentMove.getCol()!=0)
				getMove();
		}
		mat[currentMove.getRow()][currentMove.getCol()]='.';

	}


	public void mazeSolution()
	// Short method to display the result of the maze solution
	{
		if (currentMove.getCol()==0)
			System.out.println("\nTHE MAZE HAS A SOLUTION.\n");
		else
			System.out.println("\nTHE MAZE HAS NO SOLUTION.\n");
	}


	private boolean inBounds(int r, int c)
	// This method determines if a coordinate position is inbounds or not
	{
    	return (r >= 0 && r<12) && (c >= 0 && c<12);
	}


	private boolean getMove()
	// This method checks eight possible positions in a counter-clock wise manner
	// starting with the (-1,0) position.  If a position is found the method returns
	// true and the currentMove coordinates are altered to the new position
	{
		int row = currentMove.getRow();
    	int col = currentMove.getCol();
    	
    	if (inBounds(row, col - 1))
    	{  	
    		if((mat[row][(col - 1)] != 'X') && (mat[row][(col - 1)] != '.'))
    		{
    			mat[row][col] = '.';
    			visitStack.push(new Coord(currentMove.getRow(),currentMove.getCol()));
    			currentMove.setPos(row, col - 1);
    			moves++;
    			System.out.println("Moving west");
    			return true;
    		}
    	}
    	if (inBounds(row - 1, col - 1))
    	{     
    		if((mat[(row - 1)][(col - 1)] != 'X') && (mat[(row - 1)][(col - 1)] != '.'))
    		{
    			mat[row][col] = '.';
    			visitStack.push(new Coord(currentMove.getRow(),currentMove.getCol()));
    			currentMove.setPos(row - 1, col - 1);
    			moves++;
    			System.out.println("Moving northwest");
    			return true;
    		}
    	}
    	if (inBounds(row - 1, col))
    	{   
    		if((mat[(row - 1)][col] != 'X') && (mat[(row - 1)][col] != '.'))
    		{
    			mat[row][col] = '.'; 		
    			visitStack.push(new Coord(currentMove.getRow(),currentMove.getCol()));
    			currentMove.setPos(row - 1, col);
    			moves++;
    			System.out.println("Moving north");
    			return true;
    		}
    	}
    	if (inBounds(row - 1, col + 1))
    	{   
    		if((mat[(row - 1)][(col + 1)] != 'X') && (mat[(row - 1)][(col + 1)] != '.'))
    		{
    			mat[row][col] = '.';
    			visitStack.push(new Coord(currentMove.getRow(),currentMove.getCol()));
    			currentMove.setPos(row - 1, col + 1);
	    		moves++;
	      		System.out.println("Moving northeast");
	      		return true;
    		}
    	}
    	if (inBounds(row, col + 1))
    	{
    		if((mat[row][(col + 1)] != 'X') && (mat[row][(col + 1)] != '.'))
    		{
    			mat[row][col] = '.';
	    		visitStack.push(new Coord(currentMove.getRow(),currentMove.getCol()));
	      		currentMove.setPos(row, col + 1);
	      		moves++;
	      		System.out.println("Moving east");
	      		return true;
    		}
    	}
    	if (inBounds(row + 1, col + 1))
    	{
    		if((mat[(row + 1)][(col + 1)] != 'X') && (mat[(row + 1)][(col + 1)] != '.'))
    		{
    			mat[row][col] = '.';
	    		visitStack.push(new Coord(currentMove.getRow(),currentMove.getCol()));
	      		currentMove.setPos(row + 1, col + 1);
	      		moves++;
	      		System.out.println("Moving southeast");
	      		return true;
    		}
    	}
    	if (inBounds(row + 1, col))
    	{   
    		if((mat[(row + 1)][col] != 'X') && (mat[(row + 1)][col] != '.'))
    		{
    			mat[row][col] = '.';
	    		visitStack.push(new Coord(currentMove.getRow(),currentMove.getCol()));
	      		currentMove.setPos(row + 1, col);
	      		moves++;
	      		System.out.println("Moving south");
	      		return true;
    		}
    	}
    	if (inBounds(row + 1, col - 1))
    	{   
    		if((mat[(row + 1)][(col - 1)] != 'X') && (mat[(row + 1)][(col - 1)] != '.'))
    		{
    			mat[row][col] = '.'; 		
	    		visitStack.push(new Coord(currentMove.getRow(),currentMove.getCol()));
	      		currentMove.setPos(row + 1, col - 1);
	      		moves++;
	      		System.out.println("Moving southwest");
	      		return true;
    		}
    	}
    	return false;

	}
	
	public void undoMove(int sBack)
	{
		Stack<Coord> temp = new Stack<Coord>();
		if(sBack!=0){
			for(int k =0;k<sBack-1;k++)
				temp.push(visitStack.pop());}
		currentMove = visitStack.peek();
		while(!temp.isEmpty())
			visitStack.push(temp.pop());
			
		
	}

	private void pause()
	{
		Scanner input = new Scanner(System.in);
		String dummy;
		System.out.print("\nPress <Enter> to continue  ===>>  ");
		dummy = input.nextLine();
	}


}
