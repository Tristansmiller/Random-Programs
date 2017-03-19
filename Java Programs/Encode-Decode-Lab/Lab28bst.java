// Lab28bst
// The Decryption File Reading Program
// Stduent Version
// NOTE: 3 files are used for this assignment: Lab28ast.java, Lab28bst.java, and code.dat


import java.io.*;

import java.util.Scanner;

public class Lab28bst
{
	public static void main (String args[]) throws IOException
	{
		Decode topSecret = new Decode();
		topSecret.readFromFile();
		topSecret.displayEncodedText();
		topSecret.enterKey();
		topSecret.decodeText();
		topSecret.displayOriginalText();		
	}
}

class Decode
{
	private String line1, line2, line3, line4;
	private String codeLine1, codeLine2, codeLine3, codeLine4;
	private String key;
	
	public Decode()
	{
		codeLine1 = line1 = codeLine2 = line2 = codeLine3 = line3 = codeLine4 = line4 = "";
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
	
	public String decodeLine(String encodedLine)
	{		
		char[] decodedLine = new char[encodedLine.length()];
		char[] keyLetters = key.toCharArray();
		for(int k =0;k<encodedLine.length();k++)
		{
		int shift = keyLetters[k%key.length()]-'A';
		if(encodedLine.charAt(k)-shift<32){
			decodedLine[k]=(char)(encodedLine.charAt(k)-shift+96);}//this line is for wrap around, -96 moves it from 128 back to 32
		else{
			decodedLine[k]=(char)(encodedLine.charAt(k)-shift);}
			
		}
	return new String(decodedLine);
		
	}	
	
	public void decodeText()
	{
		System.out.println("\nDecoding the Text\n");
		line1 = decodeLine(codeLine1);
		line2 = decodeLine(codeLine2);
		line3 = decodeLine(codeLine3);
		line4 = decodeLine(codeLine4);

	}
		
	public void displayEncodedText()
	{
		System.out.println("The ENCODED message is:\n");
		System.out.println(codeLine1);
		System.out.println(codeLine2);
		System.out.println(codeLine3);
		System.out.println(codeLine4);
		System.out.println();
	}
	
	public void readFromFile() throws IOException
	{
		File f = new File("H:/Compsci3/Eclipse Workspace/Lab 28a/code.dat");
		BufferedReader inStream = new BufferedReader(new FileReader(f));
		codeLine1=inStream.readLine();
		codeLine2=inStream.readLine();
		codeLine3=inStream.readLine();
		codeLine4=inStream.readLine();
		System.out.println("Loading coded message\n");
		inStream.close();

	}
}