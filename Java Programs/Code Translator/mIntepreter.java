import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class mIntepreter {
	private String lines;
	private String functionDeclarations;
	private Line[] inputLines;
	private File matlabFile;
	
	public mIntepreter(String fileName,String[] input){
		inputLines = createLines(input);
		lines = "";
		matlabFile = new File("./"+fileName+".m");
		matlabTranslate();
	}
	
	public Line[] createLines(String[] input){
		Line[] lines = new Line[input.length];
		for(int k =0;k<input.length;k++){
			lines[k]=new Line(input[k]);
		}
		return lines;
	}
	public void writeMatlabFile(){
		try{
			FileWriter writer = new FileWriter(matlabFile);
			writer.write(makeMatlabFileString());
			writer.close();
		}catch(IOException e){System.out.println(e.getLocalizedMessage());}
	}
	
	public String makeMatlabFileString(){
		return lines;
	}
	public void matlabTranslate(){
		int openBrackets = 0;
		boolean inFunction = false;
		boolean inIf = false;
		boolean inElse = false;
		for(int x=0;x<inputLines.length;x++){
			String newLine = "";
			if(inputLines[x].getLexemes().length==0){
				while(openBrackets!=0){
					newLine +="\n end \n";
					openBrackets--;
				}
			}
			else{
				String[] currLine = inputLines[x].getLexemes();
				for(int k=0;k<currLine.length;k++){
					if(currLine[k].equals("function")){
						newLine+="function ans = "+currLine[k+2]+"("+currLine[k+5]+")";
						k=k+6;
						inFunction = true;
					}
					else if(currLine[k].equals("integer")){
						newLine+="";
					}
					else if(currLine[k].equals("<-")){
						newLine+=" = ";
					}
					else if(currLine[k].equals(":")){
						if(newLine.contains("function")||newLine.contains("if")||newLine.contains("else")){
							newLine+="";
						}
						else{
							newLine+=":";
						}
						if(!(newLine.contains("if")||newLine.contains("else")))
							openBrackets++;
					}
					else if(currLine[k].equals("if")){
						newLine+="if ";
						inIf = true;
					}
					else if(currLine[k].equals("otherwise")){
						newLine+="else ";
						inElse = true;
					}
					else if(currLine[k].equals("or")){
						newLine+="| ";
					}
					else if(currLine[k].equals("and")){
						newLine+="& ";
					}
					else if(currLine[k].equals("for")){
						newLine+="for "+currLine[k+1]+" = "+currLine[k+3]+":"+currLine[k+5]+":"+currLine[k+9];
						k=k+10;
						openBrackets++;
					}
					else if(currLine[k].equals("return")){
						newLine+="ans = ";
					}
					else if(currLine[k].equals("call")){
						newLine+="";
					}
					else{
						newLine+=currLine[k];
					}
				}	
			}
			if(newLine.contains("for")||newLine.contains("if")||newLine.contains("else")||newLine.contains("function")){
				newLine+="\n";
			}
			else if(newLine.length()>0){
				newLine+=";\n";
			}
			if(x<inputLines.length-1&&(inputLines[x+1].getTabs()-inputLines[x].getTabs())<0&&openBrackets!=0){
				if(!inIf&&!inElse){
					for(int n=0;n<(inputLines[x+1].getTabs()-inputLines[x].getTabs())*-1/2;n++){
						String tabs = "";
						for(int k=0;k<inputLines[x-1].getTabs()/2;k++)
							tabs+="\t"; 
						newLine+=tabs+"end\n";
						openBrackets--;
					}
				}
				else if(inIf){
					inIf=false;
				}
				else if(inElse){
					inElse=false;
				}
			}
			for(int k=0;k<inputLines[x].getTabs()/2;k++){
				newLine="\t"+newLine;
			}
			if(inputLines[x].getFullLine().contains("integer")&&!inputLines[x].getFullLine().contains("function")&&!inputLines[x].getFullLine().contains("<-")){
				lines+="";}
			else
				lines+=newLine;
		}
	}
}
