//Tristan Miller
//Tmill61@lsu.edu
//892674883 LSU ID
import java.io.*;
import java.util.Scanner;

public class Prog0 {
    public static void main(String[] args) throws FileNotFoundException {;
        Scanner input = new Scanner(new File(args[0]));
        int s= 0;
        while(input.hasNextInt()){
        	s++;
        	input.nextInt();
        }
        
        int[] intArray = new int[s];
        int index = 0;
        input = new Scanner(new File(args[0]));
        while (input.hasNextInt()) {
            intArray[index++]=input.nextInt();
        }
        
        int temp=0;
        for(int i=0;i<intArray.length;i++){
        	for(int j=1; j<(intArray.length-i);j++){
        		if(intArray[j-1]>intArray[j]){
        			temp = intArray[j-1];
        			intArray[j-1] = intArray[j];
        			intArray[j] = temp;
        			
        		}
        	}
        }
        System.out.println();
        for(int k =0;k<intArray.length;k++){
        	System.out.print(intArray[k]+" ");
        }
        
    }
}