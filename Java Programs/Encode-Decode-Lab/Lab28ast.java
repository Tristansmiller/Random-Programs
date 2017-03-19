// Lab28ast
// The Encryption File Writing Program
// Student Version
// NOTE: 3 files are used for this assignment: Lab28ast.java, Lab28bst.java, and code.dat


import java.io.*;

import java.util.Scanner;

public class Lab28ast
{
	public static void main(String args[]) throws IOException
	{
		String line1 = "How much wood would a wood chuck chuck";
		String line2 = "if a wood chuck would chuck wood?";
		String line3 = "He would chuck what a wood chuck COULD";
		String line4 = "if a wood chuck would chuck wood.";

		Encode topSecret = new Encode(line1, line2, line3, line4);
		topSecret.displayOriginalText();
		topSecret.enterKey();
		topSecret.encodeText();
		topSecret.displayEncodedText();
		topSecret.writeToFile();
	}
}

class Encode
{
	private String line1, line2, line3, line4;
	private String codeLine1, codeLine2, codeLine3, codeLine4;
	private String key;
	
	public Encode(String ln1, String ln2, String ln3, String ln4)
	{
		codeLine1 = line1 = ln1;
		codeLine2 = line2 = ln2;
		codeLine3 = line3 = ln3;
		codeLine4 = line4 = ln4;
	}
	
	public void displayOriginalText()
	{
		System.out.println("The original uncoded message is:\n");
		System.out.println(line1);
		System.out.println(line2);
		System.out.println(line3);
		System.out.println(line4);
		System.out.println();
	}
	
	public void enterKey()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter key --> ");
		// For the 90-point version the "key" is a single letter.
		// For the 100-point version, the "key" is a word.
		key = input.nextLine();
		input.close();
	}
	
	public String encodeLine(String line)
	{
		char[] encodedLine = new char[line.length()];
		char[] keyLetters = key.toCharArray();
		for(int k =0;k<line.length();k++)
		{
			int shift = keyLetters[k%key.length()]-'A';
			if(line.charAt(k)+shift>126){
				encodedLine[k]=(char)(line.charAt(k)+shift-96);}//this line is for wrap around, -96 moves it from 128 back to 32
			else{
				encodedLine[k]=(char)(line.charAt(k)+shift);}
				
		}
		return new String(encodedLine);
	
	}	
	
	public void encodeText()
	{
		System.out.println("\nEncoding the Text\n");
		codeLine1 = encodeLine(line1);
		codeLine2 = encodeLine(line2);
		codeLine3 = encodeLine(line3);
		codeLine4 = encodeLine(line4);

	}
		
	public void displayEncodedText()
	{
		System.out.println("The ENCODED message is:\n");
		System.out.println(codeLine1);
		System.out.println(codeLine2);
		System.out.println(codeLine3);
		System.out.println(codeLine4);
	}
	
	public void writeToFile() throws IOException
	{
		BufferedWriter outStream = new BufferedWriter(new FileWriter("code.dat"));
		outStream.write(codeLine1);
		outStream.newLine();
		outStream.write(codeLine2);
		outStream.newLine();
		outStream.write(codeLine3);
		outStream.newLine();
		outStream.write(codeLine4);
		outStream.close();
		System.out.println("Saving coded message\n");

	}
}