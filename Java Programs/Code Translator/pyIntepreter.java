import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class pyIntepreter {
	private String lines;
	private Line[] inputLines;
	private File pythonFile;
	
	public pyIntepreter(String fileName,String[] input){
		inputLines = createLines(input);
		lines = "";
		pythonFile = new File("./"+fileName+".py");
		pythonTranslate();
	}
	
	public Line[] createLines(String[] input){
		Line[] lines = new Line[input.length];
		for(int k =0;k<input.length;k++){
			lines[k]=new Line(input[k]);
		}
		return lines;
	}
	public void writePythonFile(){
		try{
			FileWriter writer = new FileWriter(pythonFile);
			writer.write(makePythonFileString());
			writer.close();
		}catch(IOException e){System.out.println(e.getLocalizedMessage());}
	}
	public String makePythonFileString(){
		return "#!/usr/bin/python\n\n"+lines;
	}
	public void pythonTranslate(){
		int openBrackets = 0;
		boolean inFunction = false;
		for(int x=0;x<inputLines.length;x++){
			String newLine = "";
			if(inputLines[x].getLexemes().length==0){
			}
			else{
				String[] currLine = inputLines[x].getLexemes();
				for(int k=0;k<currLine.length;k++){
					if(currLine[k].equals("function")){
						newLine+="def ";
						inFunction = true;
					}
					else if(currLine[k].equals("integer")){
						newLine+="";
					}
					else if(currLine[k].equals("<-")){
						newLine+=" = ";
					}
					else if(currLine[k].equals("otherwise")){
						newLine+="else";
					}
					else if(currLine[k].equals("for")){
						newLine+="for "+currLine[k+1]+" in range("+currLine[k+3]+","+currLine[k+5]+"):";
						k=k+10;
					}
					else if(currLine[k].equals("return")){
						newLine+="return ";
					}
					else if(currLine[k].equals("if")){
						newLine+="if ";
					}
					else if(currLine[k].equals("or")){
						newLine+=" or ";
					}
					else if(currLine[k].equals("call")){
						newLine+="";
					}
					else{
						newLine+=currLine[k];
					}
				}
			}
			for(int k=0;k<inputLines[x].getTabs()/2;k++){
				newLine="\t"+newLine;
			}
			if(inputLines[x].getFullLine().contains("integer")&&!inputLines[x].getFullLine().contains("function")&&!inputLines[x].getFullLine().contains("<-"))
				lines+="";
			else
				lines+=newLine+"\n";
		}
	}
}
