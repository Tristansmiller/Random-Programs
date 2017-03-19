// Lab26bst.java
// This is the student version of the knight's tour lab assignment.


import java.util.*;
import java.text.DecimalFormat;


public class Lab26bst
{
	public static void main (String args[])
	{
		System.out.println("\nLab26b 90/100 Point Version\n");
		Scanner input = new Scanner(System.in);
		System.out.print("Enter start row: ");
		int row = input.nextInt();
		String dummy = input.nextLine();
		System.out.print("\nEnter start col: ");
		int col = input.nextInt();
		dummy = input.nextLine();

		Knight knight = new Knight(row,col);
		knight.solveTour();
		knight.displayBoard();
	}
}


class Knight
{
	private boolean canMove;
	private int board[][];			// stores the sequence of knight moves
	private int startRow;			// row location where the knight starts
	private int startCol;			// col location where the knight starts
	private int rowPos;				// current row position of the knight
	private int colPos;				// current col position of the knight
	private int moves;				// number of location visited by the knight

								//	USED WITH THE 100 POINT VERSION
	private int access[][] = {{0,0,0,0,0,0,0,0,0,0,0,0},
 						 			{0,0,0,0,0,0,0,0,0,0,0,0},
     					 			{0,0,2,3,4,4,4,4,3,2,0,0},
     					 			{0,0,3,4,6,6,6,6,4,3,0,0},
     					 			{0,0,4,6,8,8,8,8,6,4,0,0},
     					 			{0,0,4,6,8,8,8,8,6,4,0,0},
     					 			{0,0,4,6,8,8,8,8,6,4,0,0},
     					 			{0,0,4,6,8,8,8,8,6,4,0,0},
     					 			{0,0,3,4,6,6,6,6,4,3,0,0},
     					 			{0,0,2,3,4,4,4,4,3,2,0,0},
     					 			{0,0,0,0,0,0,0,0,0,0,0,0},
     					 			{0,0,0,0,0,0,0,0,0,0,0,0}};



	public Knight(int r, int c)
	// constructor used to initializes the data attributes
	{
		--r;--c;
		board = new int[8][8];
		startRow = r;
		startCol = c;
		board[r][c] = 1;
		access[r+2][c+2]=0;
		rowPos = r;
		colPos = c;
		moves = 1;
		canMove = true;


	}


	public void getStart()
	// input method to get starting row and col from keyboard entry
	{

	}


	public void displayBoard()
	// displays the chessboard after the tour is concluded
	{
		DecimalFormat twoDigits = new DecimalFormat("00");
      for(int row[]: board)
      {

      	System.out.print("{");
      	for(int tile: row)
      		System.out.print("["+twoDigits.format(tile)+"]");
      	System.out.println("}");
      }
      System.out.println("The knight moved "+moves+" times.");

	}


	private void getMove()
	// computes the next available knight's move.  Alters RowPos and ColPos and
	// returns true if move is possible, otherwise returns false
	{
		int jumpLoc = 0;
		int accessRow = rowPos+2;
		int accessCol = colPos+2;
		int bestAccess = 10;
		int availablePositions[] = {access[(accessRow)-2][(accessCol)+1],access[(accessRow)-1][(accessCol)+2],
									access[(accessRow)+1][(accessCol)+2],access[(accessRow)+2][(accessCol)+1],
									access[(accessRow)+2][(accessCol)-1],access[(accessRow)+1][(accessCol)-2],
									access[(accessRow)-1][(accessCol)-2],access[(accessRow)-2][(accessCol)-1]};

		for(int k=0;k<8;k++)//finds move location with the least access
		{
			if(bestAccess>availablePositions[k] && availablePositions[k]!=0)
			{
				bestAccess = availablePositions[k];
				jumpLoc = k;
			}
			if(bestAccess==availablePositions[k] && bestAccess!=0)
			{ //chooses the better location using a tiebreaking algorithm that sums the accesibility of adjacent tiles
				jumpLoc = tieBreak(jumpLoc,k);
			}

		}
     	if(bestAccess==10 || bestAccess==0)
     	{
        	canMove=false;
        	jumpLoc=8;
        	return;
     	}
        else
  			canMove=true;


		switch(jumpLoc) {//chooses the knight movement pattern
			case 0:
					rowPos-=2;colPos+=1;
					access[(accessRow)-2][(accessCol)+1]=0;
					break;
			case 1:
					rowPos-=1;colPos+=2;
					access[(accessRow)-1][(accessCol)+2]=0;
					break;
			case 2:
					rowPos+=1;colPos+=2;
					access[(accessRow)+1][(accessCol)+2]=0;
					break;
			case 3:
					rowPos+=2;colPos+=1;
					access[(accessRow)+2][(accessCol)+1]=0;
					break;
			case 4:
					rowPos+=2;colPos-=1;
					access[(accessRow)+2][(accessCol)-1]=0;
					break;
			case 5:
					rowPos+=1;colPos-=2;
					access[(accessRow)+1][(accessCol)-2]=0;
					break;
			case 6:
					rowPos-=1;colPos-=2;
					access[(accessRow)-1][(accessCol)-2]=0;
					break;
			case 7:
					rowPos-=2;colPos-=1;
					access[(accessRow)-2][(accessCol)-1]=0;
				 break;
			default: break;}

		++moves;//increments moves and marks the board
		board[rowPos][colPos]=moves;
	}

	public int tieBreak(int tile1, int tile2)
	{
		int tile1Sum=0;
		int tile2Sum=0;
		int tile1Row=0,tile1Col=0,tile2Row=0,tile2Col =0;
		switch(tile1) {//finds the location of tile1
			case 0:
					if(tile1Row >=0 && tile1Col>=0)
						tile1Row=rowPos-2;tile1Col=colPos+1;
					break;

			case 1:
					if(tile1Row >=0 && tile1Col>=0)
						tile1Row=rowPos-1;tile1Col=colPos+2;
					break;
			case 2:
					if(tile1Row >=0 && tile1Col>=0)
						tile1Row=rowPos+1;tile1Col=colPos+2;
					break;
			case 3:
					if(tile1Row >=0 && tile1Col>=0)
						tile1Row=rowPos+2;tile1Col=colPos+1;
					break;
			case 4:
					if(tile1Row >=0 && tile1Col>=0)
						tile1Row=rowPos+2;tile1Col=colPos-1;
					break;
			case 5:
					if(tile1Row >=0 && tile1Col>=0)
						tile1Row=rowPos+1;tile1Col=colPos-2;
					break;
			case 6:
					if(tile1Row >=0 && tile1Col>=0)
						tile1Row=rowPos-1;tile1Col=colPos-2;
					break;
			case 7:
					if(tile1Row >=0 && tile1Col>=0)
						tile1Row=rowPos-2;tile1Col=colPos-1;
				 	break;

			default: break;}


		for(int x=0;x<8;x++)
		{
			switch(x) {//calculates total adjacent accesibility for tile1
			case 0:
					tile1Sum+=access[(tile1Row-2)+2][(tile1Col+1)+2];
					break;
			case 1:
					tile1Sum+=access[(tile1Row-1)+2][(tile1Col+2)+2];
					break;
			case 2:
					tile1Sum+=access[(tile1Row+1)+2][(tile1Col+2)+2];
					break;
			case 3:
					tile1Sum+=access[(tile1Row+2)+2][(tile1Col+1)+2];
					break;
			case 4:
					tile1Sum+=access[(tile1Row+2)+2][(tile1Col-1)+2];
					break;
			case 5:
					tile1Sum+=access[(tile1Row+1)+2][(tile1Col-2)+2];
					break;
			case 6:
					tile1Sum+=access[(tile1Row-1)+2][(tile1Col-2)+2];
					break;
			case 7:
					tile1Sum+=access[(tile1Row-2)+2][(tile1Col-1)+2];
				 	break;

			default: break;}
		}




		switch(tile2) {//finds location of tile2
			case 0:
					tile2Row=rowPos-2;tile2Col=colPos+1;
					break;

			case 1:
					tile2Row=rowPos-1;tile2Col=colPos+2;
					break;
			case 2:
					tile2Row=rowPos+1;tile2Col=colPos+2;
					break;
			case 3:
					tile2Row=rowPos+2;tile2Col=colPos+1;
					break;
			case 4:
					tile2Row=rowPos+2;tile2Col=colPos-1;
					break;
			case 5:
					tile2Row=rowPos+1;tile2Col=colPos-2;
					break;
			case 6:
					tile2Row=rowPos-1;tile2Col=colPos-2;
					break;
			case 7:
					tile2Row=rowPos-2;tile2Col=colPos-1;
				 	break;

			default: break;}


		for(int x=0;x<8;x++)
		{

			switch(x) {//calculates total adjacent accessibility
			case 0:
					if(tile2Row >=0 && tile2Col>=0)
						tile2Sum+=access[(tile2Row-2)+2][(tile2Col+1)+2];
					break;
			case 1:
					if(tile2Row >=0 && tile2Col>=0)
						tile2Sum+=access[(tile2Row-1)+2][(tile2Col+2)+2];
					break;
			case 2:
					if(tile2Row >=0 && tile2Col>=0)
						tile2Sum+=access[(tile2Row+1)+2][(tile2Col+2)+2];
					break;
			case 3:
					if(tile2Row >=0 && tile2Col>=0)
						tile2Sum+=access[(tile2Row+2)+2][(tile2Col+1)+2];
					break;
			case 4:
					if(tile2Row >=0 && tile2Col>=0)
						tile2Sum+=access[(tile2Row+2)+2][(tile2Col-1)+2];
					break;
			case 5:
					if(tile2Row >=0 && tile2Col>=0)
						tile2Sum+=access[(tile2Row+1)+2][(tile2Col-2)+2];
					break;
			case 6:
					if(tile2Row >=0 && tile2Col>=0)
						tile2Sum+=access[(tile2Row-1)+2][(tile2Col-2)+2];
					break;
			case 7:
					if(tile2Row >=0 && tile2Col>=0)
						tile2Sum+=access[(tile2Row-2)+2][(tile2Col-1)+2];

			default: break;}
		}

			if(tile1Sum<=tile2Sum)
				return tile1;
			else
				return tile2;

	}

	public void solveTour()
	// primary method that drives the knight's tour solution
	{
		for(int k =0;k<64;k++)
		{
			if(canMove)
				getMove();
		}
	}

}
