import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment2Demo {

	public static void main(String args[]){
		List<Hammer> hammers = new ArrayList<Hammer>();
		try{
			Scanner fileReader = new Scanner(new File("Hammer.data"));
			while(fileReader.hasNextLine())
				hammers.add(new Hammer(fileReader.nextLine()));
		} catch(FileNotFoundException e){ System.out.println("File not found");}
		
		List<Hammer> shortHammers = (new ShortCriteria()).meetCriteria(hammers);
		List<Hammer> longHammers = (new LongCriteria()).meetCriteria(hammers);
		List<Hammer> shortFlexOrRipHammers = (new OrCriteria(new RipCriteria(),new FlexCriteria())).meetCriteria(shortHammers);
		List<Hammer> longOrCurveHammers = (new CurvedCriteria()).meetCriteria(longHammers);
	
		List<String> hammersText = new ArrayList<String>();
		for(int k=0;k<hammers.size();k++){
			Hammer currentHammer = hammers.get(k);
			hammersText.add(""+currentHammer.getId()+"\t\t"+currentHammer.getHandle()+"\t\t"+currentHammer.getClaw());
		}
		
		
		List<String> shortHammersText = new ArrayList<String>();
		for(int k=0;k<shortHammers.size();k++){
			Hammer currentHammer = shortHammers.get(k);
			shortHammersText.add(""+currentHammer.getId()+"\t\t"+currentHammer.getHandle()+"\t\t"+currentHammer.getClaw());
		}
		
		
		List<String> longHammersText = new ArrayList<String>();
		for(int k=0;k<longHammers.size();k++){
			Hammer currentHammer = longHammers.get(k);
			longHammersText.add(""+currentHammer.getId()+"\t\t"+currentHammer.getHandle()+"\t\t"+currentHammer.getClaw());
		}
		
		List<String> shortFlexOrRipHammersText = new ArrayList<String>();
		for(int k=0;k<shortFlexOrRipHammers.size();k++){
			Hammer currentHammer = shortFlexOrRipHammers.get(k);
			shortFlexOrRipHammersText.add(""+currentHammer.getId()+"\t\t"+currentHammer.getHandle()+"\t\t"+currentHammer.getClaw());
		}
		
		List<String> longOrCurveHammersText = new ArrayList<String>();
		for(int k=0;k<longOrCurveHammers.size();k++){
			Hammer currentHammer = longOrCurveHammers.get(k);
			longOrCurveHammersText.add(""+currentHammer.getId()+"\t\t"+currentHammer.getHandle()+"\t\t"+currentHammer.getClaw());
		}
		

		List<List<String>> textLists = new ArrayList<List<String>>();
		textLists.add(hammersText);
		textLists.add(longHammersText);
		textLists.add(shortHammersText);
		textLists.add(shortFlexOrRipHammersText);
		textLists.add(longOrCurveHammersText);
		
		
		try{
			PrintWriter writer = new PrintWriter("Report.out","UTF-8");
			writer.write("ID VALUE\tHANDLE TYPE\tCLAW TYPE");
			writer.println();
			for(int k = 0;k<5;k++){
				writer.write("---------------------------------------------------------------");
				writer.println();
				if(k==0){
					writer.write("\t\tHAMMERS");
					writer.println();
				}
				if(k==1){
					writer.write("\t\tLONG HAMMERS");
					writer.println();
				}
				else if(k==2){
					writer.write("\t\tSHORT HAMMERS");
					writer.println();
				}
				else if(k==3){
					writer.write("\t\tSHORT FLEX/RIP CLAW HAMMERS");
					writer.println();
				}
				else if(k==4){
					writer.write("\t\tLONG CURVED CLAW HAMMERS");
					writer.println();
				}
				writer.write("---------------------------------------------------------------");			
				writer.println();
				for(int h = 0;h<textLists.get(k).size();h++){
					writer.write(textLists.get(k).get(h));
					writer.println();
				}
			}
			writer.close();
		}catch(FileNotFoundException e){}catch(UnsupportedEncodingException e){}
		
		
	}
	
}
