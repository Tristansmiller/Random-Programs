/*
 * Tristan Miller
 * CSC 3380
 * Assignment 3
 * cs338019
 * 
 * This is the demo class that reads input data and processes it using the chain of employees, then produces a report.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class prog3Demo {
	
	public static void main(String args[]){
		try{
			//Reading employee data
			Scanner reader = new Scanner(new File("RiverTasks.data"));
			AbstractEmployee current = new Employee(reader.nextLine());
			AbstractEmployee first = current;
			while(reader.hasNextLine()){
				current.setNextEmployee(new Employee(reader.nextLine()));
				current=current.nextEmployee;
			}
			
			//reading event data
			reader = new Scanner(new File("RiverEvents.data"));
			ArrayList<Event> events = new ArrayList<Event>();
			while(reader.hasNextLine()){
				String name = reader.next();
				String next = reader.next();
				int numTasks;
				try{ numTasks = Integer.parseInt(next);}
				catch(NumberFormatException e){name = name+" "+next; numTasks = Integer.parseInt(reader.next());}
				int[] tasks= new int[numTasks];
				for(int k = 0;k<numTasks;k++){
					tasks[k]=Integer.parseInt(reader.next());
				}
				events.add(new Event(name,numTasks,tasks));
			}
			
			reader.close();
			
			//writing the report
			PrintWriter writer = new PrintWriter("Report.out");
			writer.write("EVENT NAME \t\t\t TASKS\t\t\t\t\tEMPLOYEES");
			writer.println();
			for(int k = 0;k<events.size();k++){
				writer.write("NAME: "+events.get(k).getName()+" |\tTASKS: ");//writing name
				for(int j =0;j<events.get(k).getNumTasks();j++){//writing tasks
					if(events.get(k).getTasks()[j]==1)
						writer.write(" Floor Setup ");
					else if(events.get(k).getTasks()[j]==2)
						writer.write(" Floor Safety Check ");
					else if(events.get(k).getTasks()[j]==3)
						writer.write(" Handicap Facilities and Access ");
					else if(events.get(k).getTasks()[j]==4)
						writer.write(" Sound System Preparation ");
					else if(events.get(k).getTasks()[j]==5)
						writer.write(" Curtain Installation ");
					else if(events.get(k).getTasks()[j]==6)
						writer.write(" Emergency Verification ");
					if(j!=events.get(k).getNumTasks()-1)
						writer.write(",");
				}
				writer.write("|\t");
				ArrayList<AbstractEmployee> assignment = new ArrayList<AbstractEmployee>();
				AbstractEmployee iterator = first;
				writer.write("STAFF: ");
				while(iterator!=null){//finding which employees match the event codes
					if(iterator.checkCodes(events.get(k).getTasks())&&!assignment.contains(iterator))
							assignment.add(iterator);
					iterator = iterator.nextEmployee;
				}
				for(int j=0;j<assignment.size();j++){//writing employees
					if(j==assignment.size()-1)
						writer.write(" "+assignment.get(j).name);
					else
						writer.write(assignment.get(j).name+" ,");
				}
				writer.println();
			}
			writer.close();
		}catch(FileNotFoundException e){}
	}

}
