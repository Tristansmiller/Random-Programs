import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class jIntepreter {
	private String mainLines;
	private String otherLines;
	private Line[] inputLines;
	private File javaFile;
	private String name;
	
	public jIntepreter(String fileName,String[] input){
		inputLines = createLines(input);
		mainLines = "";
		otherLines = "";
		name = fileName;
		javaFile = new File("./"+fileName+".java");
		javaTranslate();
	}
	
	public Line[] createLines(String[] input){
		Line[] lines = new Line[input.length];
		for(int k =0;k<input.length;k++){
			lines[k]=new Line(input[k]);
		}
		return lines;
	}
	public void writeJavaFile(){
		try{
			FileWriter writer = new FileWriter(javaFile);
			writer.write(makeJavaFileString());
			writer.close();
		}catch(IOException e){System.out.println(e.getLocalizedMessage());}
	}
	public String makeJavaFileString(){
		return "public class "+name+" {\n"
				+ "public static void main(String args[]){\n"+mainLines+"\n}\n"
				+ otherLines+"\n}";
	}
	public void javaTranslate(){
		int openBrackets = 0;
		boolean inFunction = false;
		for(int x=0;x<inputLines.length;x++){
			String newLine = "";
			if(inputLines[x].getLexemes().length==0){
				while(openBrackets!=0){
					newLine +="}";
					openBrackets--;
				}
			}
			else{
				String[] currLine = inputLines[x].getLexemes();
				for(int k=0;k<currLine.length;k++){
					if(currLine[k].equals("function")){
						newLine+="public static ";
						inFunction = true;
					}
					else if(currLine[k].equals("integer")){
						newLine+="int ";
					}
					else if(currLine[k].equals("<-")){
						newLine+=" = ";
					}
					else if(currLine[k].equals(":")){
						if(newLine.contains("if")){
							newLine+=")";
						}
						newLine+="{";
						openBrackets++;
					}
					else if(currLine[k].equals("if")){
						newLine+="if ( ";
					}
					else if(currLine[k].equals("otherwise")){
						newLine+="else ";
					}
					else if(currLine[k].equals("or")){
						newLine+="|| ";
					}
					else if(currLine[k].equals("and")){
						newLine+="&& ";
					}
					else if(currLine[k].equals("for")){
						newLine+="for ( "+currLine[k+1]+" = "+currLine[k+3]+" ; "+currLine[k+1]+" <= "+currLine[k+5]+" ; "+currLine[k+1]+" += "+currLine[k+9]+" ){";
						k=k+10;
						openBrackets++;
					}
					else if(currLine[k].equals("return")){
						newLine+="return ";
					}
					else if(currLine[k].equals("call")){
						newLine+="";
					}
					else{
						newLine+=currLine[k];
					}
				}
			}
			if(newLine.contains("for")||newLine.contains("public")||newLine.contains("if")||newLine.contains("else")){
				newLine+="\n";
			}
			else if(newLine.length()>0){
				newLine+=";\n";
			}
			if(x<inputLines.length-1&&(inputLines[x+1].getTabs()-inputLines[x].getTabs())<0&&openBrackets!=0){
				for(int n=0;n<(inputLines[x+1].getTabs()-inputLines[x].getTabs())*-1/2;n++){
					newLine+="}";
					openBrackets--;
				}
			}
			if(!inFunction){
				mainLines+=newLine;
			}
			else{
				otherLines+=newLine;
			}
			if(openBrackets==0){
				inFunction=false;
			}
		}
	}
}
