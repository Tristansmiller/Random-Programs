import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Babelfish {
	

	public static void main(String[] args) throws FileNotFoundException {
		File sum = new File("sum.alg");
		File fib = new File("fib.alg");

		String[] input = getLines(sum);
		jIntepreter jTranslate = new jIntepreter("sum",input);
		jTranslate.writeJavaFile();	
		cIntepreter cTranslate = new cIntepreter("sum",input);
		cTranslate.writeCFile();
		pyIntepreter pyTranslate = new pyIntepreter("sum",input);
		pyTranslate.writePythonFile();
		mIntepreter mTranslate = new mIntepreter("sum",input);
		mTranslate.writeMatlabFile();

		input = getLines(fib);
		jTranslate = new jIntepreter("fib",input);
		jTranslate.writeJavaFile();
		cTranslate = new cIntepreter("fib",input);
		cTranslate.writeCFile();
		pyTranslate = new pyIntepreter("fib",input);
		pyTranslate.writePythonFile();
		mTranslate = new mIntepreter("fib",input);
		mTranslate.writeMatlabFile();
	}
	
	public static String[] getLines(File input){
		String output = "";
		try{
			Scanner read = new Scanner(input);
			while(read.hasNextLine()){
				output+=(read.nextLine()+"\n");
			}
		}catch(FileNotFoundException e){}
		return output.split("\n");
	}

}
