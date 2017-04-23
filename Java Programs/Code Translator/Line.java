import java.util.ArrayList;

public class Line {
	private int tabNum;
	private String[] lexemes;
	private String fullLine;
	
	public Line(String input){
		tabNum = measureTabs(input);
		lexemes = createLexemes(input);
		fullLine = input;
	}
	
	public int measureTabs(String input){
		int place = 0;
		while(place<input.length()&&input.charAt(place)==' '){
			place++;
		}
		return place;
	}
	
	public static String[] createLexemes(String input){
		String[]lexs = input.split("(?<=\\()|(?=\\))|(?=:)|\\b|(?=\\s)");
		ArrayList<String> newLexemes = new ArrayList<String>();
		for(int k=0;k<lexs.length;k++){
			lexs[k]=lexs[k].replaceAll(("\\s"), "");
			if(!(lexs[k].length()==0)){
				newLexemes.add(lexs[k]);
			}
		}
		String[] arr = new String[newLexemes.size()];
		for(int k=0;k<newLexemes.size();k++){
			arr[k]=newLexemes.get(k);
		}
		return arr;
	}
	
	public String getFullLine(){
		return fullLine;
	}
	public int getTabs(){
		return tabNum;
	}
	
	public String[] getLexemes(){
		return lexemes;
	}
}
