package prog4;
/*
 * Tristan Miller
 * CSC 3380
 * cs338019
 * Assignment 4
 * 
 * This Class reads a file to get order codes, then creates an output file displaying the orders.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class prog4 {
	public static void main(String args[]){

		try{
			Scanner input = new Scanner(new File("Cupcakes.data"));
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<Integer> numCakes = new ArrayList<Integer>();
			ArrayList<Cupcake> cupCakes = new ArrayList<Cupcake>();
			for(int k =0;input.hasNext();k++){
				names.add(input.next());
				numCakes.add(input.nextInt());
				input.nextLine();
				for(int h =0;h<numCakes.get(k);h++){
					String cupcake = input.nextLine();
					String[] formaltypes = cupcake.split(" ");
					int[] types = new int[formaltypes.length];
					for(int z = 0;z<types.length;z++){
						types[z] = Integer.parseInt(formaltypes[z].substring(1, 2));
					}
					if(types.length==3){
						switch(types[0]){
							case 1: cupCakes.add(new Topping(new Frosting(new Vanilla(),types[1]),types[2])); break;
							case 2: cupCakes.add(new Topping(new Frosting(new Chocolate(),types[1]),types[2])); break;
							default: break;
						}
					}
					else{
						switch(types[0]){
							case 1: cupCakes.add(new Frosting(new Vanilla(),types[1])); break;
							case 2: cupCakes.add(new Frosting(new Chocolate(),types[1])); break;
							default: break;
						}
					}
				}
			}
			input.close();
			
			PrintWriter writer = new PrintWriter("Report.out");
			for(int k=0; k<names.size();k++){
				writer.write("Customer Name: "+names.get(k)+"     Number of cupcakes in order: "+numCakes.get(k));
				writer.println();
				int buffer=0;
				if(k!=0){
					int z = 0;
					while(z<k){
						buffer+=numCakes.get(z); z++;
					}
				}
				for(int h =buffer;h<numCakes.get(k)+buffer;h++){
					writer.write(cupCakes.get(h).toString() +"      Price:"+cupCakes.get(h).price());
					writer.println();
				}
				writer.println();
			}
			writer.close();
		}catch(FileNotFoundException e){}
	}
}
