import java.util.ArrayList;
import java.util.function.Function;

/**
 * A binary search tree <br>
 * Requires JDK 1.8 for Function*
 * 
 * @author Duncan, Tristan Miller
 * @param <E>
 *            the tree data type
 * @since November 13, 2015
 */
public class BSTree<E extends Comparable<E>> implements BSTreeAPI<E> {
	/**
	 * the root of this tree
	 */
	private Node root;
	/**
	 * the number of nodes in this tree
	 */
	private int size;

	/**
	 * A node of a tree stores a data item and references to the child nodes to
	 * the left and to the right.
	 */
	private class Node {
		/**
		 * the data in this node
		 */
		public E data;
		/**
		 * A reference to the left subtree rooted at this node.
		 */
		public Node left;
		/**
		 * A reference to the right subtree rooted at this node
		 */
		public Node right;
	}

	/**
	 * Constructs an empty tree
	 */
	public BSTree() {
		root = null;
		size = 0;
	}
	/**
	 * Constructs a tree that is a copy of another tree.
	 * @param tree - the tree to be copied.
	 */
	public BSTree(BSTree tree){
		copyTree(tree.root);
	}
	
	/**
	 * auxiliary recursive method used to copy trees
	 * @param originalSubTreeRoot the root node of the subtree being copied.
	 */
	private void copyTree(Node originalSubTreeRoot){
		if(originalSubTreeRoot!=null){
			insert(originalSubTreeRoot.data);
			copyTree(originalSubTreeRoot.left);
			copyTree(originalSubTreeRoot.right);
		}
	}
	
	/**
	 * Delete all leaf nodes of this tree and decrement the size of the tree. Does nothing if the tree is empty.
	 */
	public void trim(){
		if(root!=null){
			trim(root);
		}
	}
	
	/**
	 * A recursive auxiliary method of the trim method.
	 * @param subTreeRoot a node at which a subtree is rooted.
	 */
	private void trim(Node subTreeRoot){
		if(subTreeRoot!=null){
			if(subTreeRoot.left!=null){
				if(subTreeRoot.left.left==null && subTreeRoot.left.right==null){
					subTreeRoot.left=null;
					size--;
				}
			}
			if(subTreeRoot.right!=null){
				if(subTreeRoot.right.left==null && subTreeRoot.right.right==null){
					subTreeRoot.right=null;
					size--;
				}
			}
			trim(subTreeRoot.left);
			trim(subTreeRoot.right);
		}
			
	}
	
	/**
	 * Gives an array list containing all the root-to-leaf paths of the tree. Each path is represented as n1->n2->n3...nk
	 * where each ni is a data value in the node along the path and n1 is the root node, nk is a leaf node.
	 * @return an array list of strings containing all the root-to-leaf paths.
	 */
	public ArrayList<String> getPaths(){
		ArrayList<String> paths = new ArrayList<String>();
		genPaths(root,"",paths);
		return paths;
	}
	
	/**
	 * A recursive auxiliary method for the getPath method.
	 * @param node a node along the root-to-leaf path
	 * @param pStr a string representing a path
	 * @param paths an array list whose elements are the root-to-leaf paths in the tree
	 * in the format n1->n2->n3...nk, where n1 is the root and nk a leaf.
	 */
	private void genPaths(Node node, String pStr, ArrayList<String> paths){
		if(node!=null){
			if(node.left==null && node.right==null){
				pStr+=node.data;
				paths.add(pStr);
			}
				
			else{
				pStr+=node.data+" -> ";
				genPaths(node.left,pStr,paths);
				genPaths(node.right,pStr,paths);
			}
			
		}
	}
	
	/**
	 * Gives the diameter of this tree.
	 * @return the diameter of this tree.
	 */
	public int diameter(){
		return diameter(root);
	}
	
	/**
	 * A recursive auxiliary method of the diameter method that
	 * gives the diameter of the subtree rooted at the specified node.
	 * @param node in the tree
	 * @return the diameter of the subtree rooted at the specified node.
	 */
	private int diameter(Node node){
		if(node==null)
			return 0;
		int diamThruRoot = maxPath(node.left) + maxPath(node.right)+1;
		int diamLeftTree = diameter(node.left);
		int diamRightTree = diameter(node.right);
		
		return Math.max(diamThruRoot, Math.max(diamLeftTree,diamRightTree));
	}
	
	/**
	 * Computes the maximum path of the subtree rooted at the specified node.
	 * @param node the root of a subtree
	 * @return the number of nodes along the longest path of the subtree rooted at the specified node.
	 */
	private int maxPath(Node node){
		int max;
		if(node == null)
			max = 0;
		else{
			max = Math.max(maxPath(node.left),maxPath(node.right))+1;
		}
		return max;
		
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public void insert(E item) {
		Node newNode = new Node();
		newNode.data = item;
		if (size == 0) {
			root = newNode;
			size++;
		} else {
			Node tmp = root;
			while (true) {
				int d = tmp.data.compareTo(item);
				if (d == 0) { /* Key already exists. (update) */
					tmp.data = item;
					return;
				} else if (d > 0) {
					if (tmp.left == null) { /* If the key is less than tmp */
						tmp.left = newNode;
						size++;
						return;
					} else { /* continue searching for insertion pt. */
						tmp = tmp.left;
					}
				} else {
					if (tmp.right == null) {/* If the key is greater than tmp */
						tmp.right = newNode;
						size++;
						return;
					} else { /* continue searching for insertion point */
						tmp = tmp.right;
					}
				}
			}
		}
	}

	@Override
	public boolean inTree(E item) {
		Node tmp;
		if (size == 0)
			return false;
		/* find where it is */
		tmp = root;
		while (true) {
			int d = tmp.data.compareTo(item);
			if (d == 0)
				return true;
			else if (d > 0) {
				if (tmp.left == null)
					return false;
				else
					/* continue searching */
					tmp = tmp.left;
			} else {
				if (tmp.right == null)
					return false;
				else
					/* continue searching for insertion pt. */
					tmp = tmp.right;
			}
		}
	}

	@Override
	public void remove(E item) {
		Node nodeptr;
		nodeptr = search(item);
		if (nodeptr != null) {
			remove(nodeptr);
			size--;
		}
	}

	@Override
	public void inorderTraverse(Function func) {
		inorderTraverse(root, func);
	}

	@Override
	public E retrieve(E key) throws BSTreeException {
		Node nodeptr;
		if (size == 0)
			throw new BSTreeException("Non-empty tree expected on retrieve().");
		nodeptr = search(key);
		if (nodeptr == null)
			throw new BSTreeException("Existent key expected on retrieve().");
		return nodeptr.data;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * A recursive auxiliary method for the inorderTraver method that
	 * 
	 * @param node
	 *            a reference to a Node object
	 * @param func
	 *            a function that is applied to the data in each node as the
	 *            tree is traversed in order.
	 */
	private void inorderTraverse(Node node, Function func) {
		if (node != null) {
			inorderTraverse(node.left, func);
			func.apply(node.data);
			inorderTraverse(node.right, func);
		}
	}

	/**
	 * An auxiliary method that support the remove method
	 * 
	 * @param node
	 *            a reference to a Node object in this tree
	 */
	private void remove(Node node) {
		E theData;
		Node parent, replacement;
		parent = findParent(node);
		if ((node.left != null) && (node.right != null)) {
			replacement = node.right;
			while (replacement.left != null)
				replacement = replacement.left;
			theData = replacement.data;
			remove(replacement);
			node.data = theData;
		} else {
			if ((node.left == null) && (node.right == null))
				replacement = null;
			else if (node.left == null)
				replacement = node.right;
			else
				replacement = node.left;
			if (parent == null)
				root = replacement;
			else if (parent.left == node)
				parent.left = replacement;
			else
				parent.right = replacement;
		}
	}

	/**
	 * An auxiliary method that supports the search method
	 * 
	 * @param key
	 *            a data key
	 * @return a reference to the Node object whose data has the specified key.
	 */
	private Node search(E key) {
		Node current = root;
		while (current != null) {
			int d = current.data.compareTo(key);
			if (d == 0)
				return current;
			else if (d > 0)
				current = current.left;
			else
				current = current.right;
		}
		return null;
	}

	/**
	 * An auxiliary method that gives a Node reference to the parent node of the
	 * specified node
	 * 
	 * @param node
	 *            a reference to a Node object
	 * @return a reference to the parent node of the specified node
	 */
	private Node findParent(Node node) {
		Node tmp = root;
		if (tmp == node)
			return null;
		while (true) {
			assert tmp.data.compareTo(node.data) != 0;
			if (tmp.data.compareTo(node.data) > 0) {
				/*
				 * this assert is not needed but just in case there is a bug
				 */
				assert tmp.left != null;
				if (tmp.left == node)
					return tmp;
				else
					tmp = tmp.left;
			} else {
				assert tmp.right != null;
				if (tmp.right == node)
					return tmp;
				else
					tmp = tmp.right;
			}
		}
	}
}