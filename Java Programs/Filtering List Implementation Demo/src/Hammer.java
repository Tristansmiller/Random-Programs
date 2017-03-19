
public class Hammer {
	private int id;
	private char handle;
	private int claw;
	
	public Hammer(String s){
		String[] sList = s.split(" ");
		id=Integer.parseInt(sList[0]);
		handle=sList[1].charAt(0);
		claw=Integer.parseInt(sList[2]);
	}

	public int getId() {
		return id;
	}

	public char getHandle() {
		return handle;
	}

	public int getClaw() {
		return claw;
	}
	

}
