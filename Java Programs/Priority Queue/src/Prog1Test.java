/*
 * Written by Tristan Miller
 * LSU Email: Tmill61@lsu.edu
 */
public class Prog1Test {
	
	public static void main(String args[]){
		PriorityQueue Q = new PriorityQueue();
		test1(Q);
		test2(Q);
		test3(Q);
		test4(Q);
		
	}

	public static void test1(PriorityQueue Q){
		Q.clear();
		Q.append('a');
		Q.append('b');
		assert Q.size()==2;
		assert Q.check()==true;
	}
	
	public static void test2(PriorityQueue Q){
		Q.clear();
		Q.append('b');
		Q.append('a');
		assert Q.size()==2;
		assert Q.check()==false;
	}
	
	public static void test3(PriorityQueue Q){
		Q.clear();
		Q.insert('a');
		Q.insert('b');
		assert Q.size()==2;
		assert Q.check()==true;
		assert Q.remove()=='a';
		assert Q.size()==1;
	}
	
	public static void test4(PriorityQueue Q){
		Q.clear();
		Q.insert('b');
		Q.insert('a');
		assert Q.size()==2;
		assert Q.check()==true;
		assert Q.remove()=='a';
		assert Q.size()==1;
	}

}
