/*
 * Tristan Miller
 * CSC 3380
 * Assignment 3
 * cs338019
 * 
 * This is the employee class which holds names and task codes, and references to the next in the chain
 */
public class Employee extends AbstractEmployee{
	
	public Employee(String n){
		String[] splits = n.split(" +");
		name =splits[0];
		numWorkCodes = Integer.parseInt(splits[1]);
		workCodes = new int[numWorkCodes];
		for(int k = 0;k<numWorkCodes;k++){
			workCodes[k]=Integer.parseInt(splits[k+2]);
		}
	}
	
	public Employee(int numCodes, int[] codes, String name){
		numWorkCodes=numCodes;
		workCodes=codes;
		this.name = name;
	}
	
	public void setNextEmployee(AbstractEmployee next){
		nextEmployee = next;
	}
	
	public String toString(){
		return name;
	}
	
	public boolean equals(Object o){
		if(o instanceof Employee){
			Employee other = (Employee)o;
			if(this.name.equals(other.name))
				return true;
			else
				return false;
		}
		else return false;
	}


}
