
public class CoordQueue {
	private Coord[] coords;
	private int backEnd;
	private int size;
	
	public CoordQueue(int s)
	{
		size = s;
		coords = new Coord[size];
		backEnd = 0;
	}
	
	public Coord add(Coord xy)
	{
		if(backEnd ==size){
			Coord temp = poll();
			coords[backEnd-1]=xy;
			return temp;}
		else
			coords[backEnd++]=xy;
			return null;
	}
	
	public boolean isFull()
	{
		return (backEnd == (size));
	}
	
	public Coord poll()
	{
		Coord temp = new Coord(coords[0]);
		for(int k=0;k<size-1;k++)
			coords[k]=coords[k+1];
		return temp;
	}

}
