/*
 * Tristan Miller
 * CSC 3380
 * Assignment 3
 * cs338019
 * 
 * This is the abstract class that the Employee class extends
 */
public abstract class AbstractEmployee {
	protected int numWorkCodes;
	protected int[] workCodes;
	protected String name;
	protected AbstractEmployee nextEmployee;
	
	public void setNextEmployee(AbstractEmployee e){};
	
	public boolean checkCodes(int[] checkCodes){
		for(int i = 0;i<numWorkCodes;i++){
			for(int j =0;j<checkCodes.length;j++){
				if(workCodes[i]==checkCodes[j])
					return true;
			}
		}
		return false;
	}
	

}
