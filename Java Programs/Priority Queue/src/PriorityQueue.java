/*
 * Written by Tristan Miller
 * LSU Email: Tmill61@lsu.edu
 */
public class PriorityQueue {
	
	private char[] charArray;//the array of char values that is the priority queue is built around
	int currentMaxIndex;//the current largest index being used
	
	public PriorityQueue(){
		charArray= new char[255];
		currentMaxIndex=-1;
	}

	//removes all characters from the priority queue.
	public void clear(){
		while(currentMaxIndex!=-1)
			charArray[currentMaxIndex--]=0;
	}
	
	//adds a character to the end of the priority queue, regardless of if it maintains heap order.
	public void append(char a){
		charArray[++currentMaxIndex]=a;
	}
	
	//checks the priority queue to see if it fulfills the heap order property.
	public boolean check(){
		int i = 0;
		boolean isHeap = true;
		while(i<=currentMaxIndex){
			if((charArray[i]>charArray[2*i+1] && 2*i+1<=currentMaxIndex)
					|| (charArray[i]>charArray[2*i+2] && 2*i+2<=currentMaxIndex)){//checks to see if the parent is greater than the children
				isHeap = false;
				break;
			}
			i++;
		}
		return isHeap;	
	}
	
	//returns the size of the priority queue.
	public int size(){
		return currentMaxIndex+1;
	}
	
	//adds an element to the priority queue and rearranges the priority queue so it satisfies the heap order property.
	public void insert(char a){
		charArray[++currentMaxIndex]=a;
		if(currentMaxIndex!=0){
			int i = currentMaxIndex;
			while(!check()){//swaps parent and child nodes until the queue is a heap
				char temp = charArray[i];
				charArray[i]=charArray[(i-1)/2];
				charArray[(i-1)/2]=temp;
				i=(i-1)/2;
			}
		}
	}
	
	//removes the first element of the priority queue and then rearranges the queue so it satisfies the heap order property.
	public char remove(){
		char output = charArray[0];
		charArray[0]=charArray[currentMaxIndex--];
		if(currentMaxIndex>0){
			int i=0;
			while(!check()){//swaps the parent with the smaller of its child nodes until the queue is a heap
				if(charArray[2*i+1]<=charArray[2*i+2]){
					char temp = charArray[i];
					charArray[i]=charArray[2*i+1];
					charArray[2*i+1]=temp;
					i=2*i+1;
				}
				else{
					char temp = charArray[i];
					charArray[i]=charArray[2*i+2];
					charArray[2*i+2]=temp;
					i=2*i+2;
				}
			}
		}
		return output;
	}
	
	
}


