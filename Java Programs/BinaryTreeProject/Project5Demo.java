import java.util.ArrayList;
/**
 * This class is for testing the BSTree class
 * CSC 1351 Proj 5
 * @author Tristan Miller
 * @see BSTree
 * @since 12/02/2015
 */
public class Project5Demo {
	public static void main(String args[]){
		BSTree tree1 = new BSTree();
		tree1.insert((Integer)6);
		tree1.insert((Integer)2);
		tree1.insert((Integer)1);
		tree1.insert((Integer)4);
		tree1.insert((Integer)3);
		tree1.insert((Integer)5);
		tree1.insert((Integer)7);
		tree1.insert((Integer)8);
		tree1.insert((Integer)12);
		tree1.insert((Integer)13);
		tree1.insert((Integer)10);
		tree1.insert((Integer)9);
		tree1.insert((Integer)11);
		ArrayList<String> paths = tree1.getPaths();
		System.out.println("The root-to-leaf paths in tree1 are: ");
		System.out.print("[");
		for(int k=0;k<paths.size()-1;k++){
			System.out.print(paths.get(k)+" , ");
		}
		System.out.print(paths.get(paths.size()-1)+"]");
		System.out.println();
		BSTree tree2 = new BSTree();
		tree2.insert((Integer)13);
		tree2.insert((Integer)14);
		tree2.insert((Integer)15);
		tree2.insert((Integer)7);
		tree2.insert((Integer)2);
		tree2.insert((Integer)1);
		tree2.insert((Integer)6);
		tree2.insert((Integer)4);
		tree2.insert((Integer)3);
		tree2.insert((Integer)5);
		tree2.insert((Integer)8);
		tree2.insert((Integer)10);
		tree2.insert((Integer)9);
		tree2.insert((Integer)11);
		tree2.insert((Integer)12);
		
		paths = tree2.getPaths();
		System.out.println("The root-to-leaf paths in tree2 are: ");
		System.out.print("[");
		for(int k=0;k<paths.size()-1;k++){
			System.out.print(paths.get(k)+" , ");
		}
		System.out.print(paths.get(paths.size()-1)+"]");
		
		
		System.out.println();
		
		System.out.println("The diameters of tree1 and tree2 are "+tree1.diameter()+" and "+
							tree2.diameter()+", respectively.");
		
		
		
		BSTree tree3 = new BSTree(tree2);
		paths = tree3.getPaths();
		System.out.println("The root-to-leaf paths in tree3 are: ");
		System.out.print("[");
		for(int k=0;k<paths.size()-1;k++){
			System.out.print(paths.get(k)+" , ");
		}
		System.out.print(paths.get(paths.size()-1)+"]");
		System.out.println("\nThe diameter of tree3 is "+tree3.diameter()+" and its size is "+tree3.size());
		tree3.trim();
		paths = tree3.getPaths();
		System.out.println("The root-to-leaf paths in tree3 are now: ");
		System.out.print("[");
		for(int k=0;k<paths.size()-1;k++){
			System.out.print(paths.get(k)+" , ");
		}
		System.out.print(paths.get(paths.size()-1)+"]");
		System.out.println("\nThe diameter of tree3 is now "+tree3.diameter()+" and its size is now "+tree3.size());
	}

}
