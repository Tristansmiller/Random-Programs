/*
 * Tristan Miller
 * CSC 3380
 * Assignment 3
 * cs338019
 * 
 * This is a small class to contain the data from the event file.
 */
public class Event {
	
	private String name;
	private int numTasks;
	private int[] tasks;
	
	public Event(String n, int nT, int[] t){
		name = n;
		numTasks = nT;
		tasks = t;
	}

	public String getName() {
		return name;
	}

	public int getNumTasks() {
		return numTasks;
	}

	public int[] getTasks() {
		return tasks;
	}
	
	

}
