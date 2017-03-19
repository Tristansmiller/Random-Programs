/*
 * Written by Tristan Miller
 * LSU Email: Tmill61@lsu.edu
 */
import java.io.*;
import java.util.Scanner;

public class Prog1 {
	public static void main(String[] args) throws FileNotFoundException{
		PriorityQueue Q = new PriorityQueue();
		
		Scanner inputScan = new Scanner(new File(args[0]));
		String input = "";
		while(inputScan.hasNextLine()){
			input+=inputScan.nextLine();
		}
		inputScan.close();
		
		
		char current;
		for(int k = 0;k<input.length();k++){
			current = input.charAt(k);
			if((current>='A'&&current<='Z') || (current>='a'&&current<='z'))
				Q.insert(current);
			else if(current=='!')
				System.out.print(Q.remove());
		}
		System.out.println();
	}

	
}
