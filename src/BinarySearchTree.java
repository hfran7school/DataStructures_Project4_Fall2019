/**
 * This class is the implementation of Binary Search Tree in Project 4.
 * It has two separate insert methods that insert based on name and GDP,
 * a delete method that deletes nodes based on the number of children the
 * node has, and able to print the top and bottom 10 countries based on 
 * GDP per capita in the tree. 
 * 
 * @author Hailey Francis
 * @version November 16, 2019
 */
import java.util.*;
public class BinarySearchTree {
	
	public Node root;
	private List<Node> nodesList = new ArrayList<Node>();
	
	/**
	 * Empty Constructor
	 */
	public BinarySearchTree(){
	}
	
	/**
	 * Inserts node into proper position in search tree based on country name.
	 * @param name -- name of country to be inserted
	 * @param GDPperCapita -- GDP per capita of country to be inserted
	 */
	public void insert(String name, double GDPperCapita) {
		Node newNode = new Node(name, GDPperCapita); //making new node with data from parameters
		if(root == null) { //no node in root
			root = newNode;
		}else { //root occupied
			Node curr = root;
			Node parent;
			while(true) { // exits internally
				parent = curr;
				if(newNode.name.compareTo(curr.name) < 0) { //based on name; goLeft
					curr = curr.leftChild;
					if(curr == null) { //if end of line, insert on left
						parent.leftChild = newNode;
						return;
					}
				} //end if goLeft
				else { //goRight
					curr = curr.rightChild;
					if(curr == null) { //if end of line, insert on right
						parent.rightChild = newNode;
						return;
					}
				} //end else goRight
			}//end while
		}//end else root occupied
	}//end insert()
	
	
	/**
	 * Inserts nodes into proper position in tree
	 * based on GDP per capita, instead of by name.
	 * @param name -- name of Country
	 * @param GDPperCapita -- GDP per capita of country
	 */
	public void insertByGDP(String name, double GDPperCapita) {
		Node newNode = new Node(name, GDPperCapita);
		if(root == null) { //no node in root
			root = newNode;
		}else { //root occupied
			Node curr = root;
			Node parent;
			while(true) { // exits internally
				parent = curr;
				if(newNode.gdpPerCapita < curr.gdpPerCapita) { //based on GDPperCapita; goLeft
					curr = curr.leftChild;
					if(curr == null) { //if end of line, insert on left
						parent.leftChild = newNode;
						return;
					}
				} //end if goLeft
				else { //goRight
					curr = curr.rightChild;
					if(curr == null) { //if end of line, insert on right
						parent.rightChild = newNode;
						return;
					}
				} //end else goRight
			}//end while
		}//end else root occupied
	}
	
	
	/**
	 * Searches tree for the country of the given name and if found will
	 * return the GDP per capita or -1 if not found. (Assume non-empty tree.)
	 * 
	 * @param name -- name of string to be found
	 * @return number of nodes visited, although whether or not 
	 * found and GDP per capita is printed within method
	 */
	public int find(String name) {
		int visited = 0; // num of nodes visited
		Node curr = root; //start at root
		while(!curr.name.equals(name)) { //while no match
			if(name.compareTo(curr.name) < 0){ //based on name; goLeft
				curr = curr.leftChild;
				visited++;
			}else { //goRight
				curr = curr.rightChild;
				visited++;
			}
			// made it to leaf node //
			if(curr == null) { //did not find
				System.out.println(name + " not found. ");
				return visited;
			}
		}//end while
		System.out.println(name + " was found. GDP per Capita is: " + curr.gdpPerCapita + ". ");
		return visited; //found it
	}
	
	/**
	 * Finds and deletes the given country from the tree.
	 * (Assume non-empty)
	 * @param name -- name of country to be delete
	 */
	public boolean delete(String name) {
		Node curr = root;
		Node parent = root;
		boolean isLeftChild = true;
		
		while(!name.equals(curr.name)){ //search for node
			parent = curr;
			if(name.compareTo(curr.name) < 0) {
				isLeftChild = true;
				curr = curr.leftChild;
			}else{ //goRight
				isLeftChild = false;
				curr = curr.rightChild;
			}
			if(curr == null) {
				//
				return false;
			}
		}//end while
		if(curr.leftChild == null && curr.rightChild == null) { //case 1: no children
			if(curr == root) {
				root = null; //tree is empty
			}else if(isLeftChild) {
				parent.leftChild = null;
			}else {
				parent.rightChild = null;
			}
		}else if(curr.rightChild == null) { //case 2.1: one child, left child
			if(curr == root) {
				root = curr.leftChild;
			}else if(isLeftChild) { //left child of parent
				parent.leftChild = curr.leftChild;
			}else { //right child of parent
				parent.rightChild = curr.leftChild;
			}
		}else if(curr.leftChild == null) { //case 2.2: one child, right child
			if(curr == root) {
				root = curr.rightChild;
			}else if(isLeftChild) { //left child of parent
				parent.leftChild = curr.rightChild;
			}else { //right child of parent
				parent.rightChild = curr.rightChild;
			}
		}else { //case 3: two children; replace with inorder successor
			Node successor = getSuccessor(curr); //get successor
			// connect parent of current to successor instead
			if(curr == root) {
				root = successor;
			}else if(isLeftChild) {
				parent.leftChild = successor;
			}else {
				parent.rightChild = successor;
			} 
			successor.leftChild = curr.leftChild; //connect successor to curr's left child
		}//end else two children
		return true;
}

	/**
	 * Returns the successor of the node specified as its delNode arguement. 
	 * @param delNode -- node to be deleted
	 * @return successor of deleted node
	 */
	private Node getSuccessor(Node delNode){
		Node successorParent = delNode;
		Node successor = delNode;
		Node curr = delNode.rightChild; //go to right child
		while(curr != null) { //until no more left children
			successorParent = successor;
			successor = curr;
			curr = curr.leftChild; //go to left child
		}
		if(successor != delNode.rightChild) { //if successor is not right child
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}
	
	/**
	 * Traverses the tree in using an Inorder traversal (LNR) 
	 * and print each node.
	 */
	public void printInorder(Node localRoot) {
		if(localRoot != null) {
			printInorder(localRoot.leftChild);
			localRoot.printNode();
			printInorder(localRoot.rightChild);
			
		}
	}
	
	/**
	 * Traverses the tree using a Predorder traversal (NLR) 
	 * and print each node.
	 */
	public void printPreorder(Node localRoot) {
		if(localRoot != null) {
			localRoot.printNode();
			printPreorder(localRoot.leftChild);
			printPreorder(localRoot.rightChild);
		}
	}
	
	
	/**
	 * Traverses the tree using a Postorder traversal (LRN) and
	 * print each node.
	 */
	public void printPostorder(Node localRoot) {
		if(localRoot != null) {
			printPostorder(localRoot.leftChild);
			printPostorder(localRoot.rightChild);
			localRoot.printNode();
		}
	}
	
	/**
	 * Finds and prints the bottom 10 countries regarding GDP
	 * per capita.
	 */
	public void printBottomTen() {
		BinarySearchTree capitaTree = new BinarySearchTree();
		moveToCapitaTree(capitaTree, this.root);
		capitaTree.toList(capitaTree.root, nodesList);
		for(int i = 0; i < 10; i++) {
			nodesList.get(i).printNode();
		}
		nodesList.clear();
	}

	/**
	 * Finds and prints the top 10 countries regarding GDP
	 * per capita.
	 */
	public void printTopTen() {
		BinarySearchTree capitaTree = new BinarySearchTree();
		moveToCapitaTree(capitaTree, this.root);
		capitaTree.toList(capitaTree.root, nodesList);
		for(int i = nodesList.size() - 1; i > nodesList.size() - 11; i--) {
			nodesList.get(i).printNode();
		}
		nodesList.clear();
	}
	
	/**
	 * Traverses a tree inorder, starting at a local root, 
	 * and adds it to a Node List given in the parameters.
	 * (This method is used with the private nodesList 
	 * variable.)
	 * @param localRoot -- root of tree
	 * @param nodes -- list of Nodes
	 */
	public void toList(Node localRoot, List<Node> nodes) {
		if(localRoot != null) {
			toList(localRoot.leftChild, nodes);
			nodes.add(localRoot);
			toList(localRoot.rightChild, nodes);
		}

	}
	
	/**
	 * Takes a BinarySearchTree and inserts its contents into
	 * another BinarySearchTree that is sorted based on its GDP
	 * per capita as opposed to its name.
	 * @param capitaTree -- BinarySearchTree that is to be sorted by GDP per capita
	 * @param curNode -- presumably root of other tree whose contents are being moved to capitaTree
	 */
	public void moveToCapitaTree(BinarySearchTree capitaTree, Node curNode) {
		if(curNode != null) {
			moveToCapitaTree(capitaTree, curNode.leftChild);
			capitaTree.insertByGDP(curNode.name, curNode.gdpPerCapita);
			moveToCapitaTree(capitaTree, curNode.rightChild);
		}
	}
	
/**
 * Template node class from rubric. (Would not let me use
 * private modifier.)
 * 
 * @author Hailey Francis
 *
 */
class Node {
	String name;
	double gdpPerCapita;
	Node leftChild;
	Node rightChild; 

	public Node(String name, double gdpPerCapita) {
		this.name = name; this.gdpPerCapita = gdpPerCapita;
	}
	
	public void printNode() {
		System.out.printf("%-25s%,-20.2f\n", name, gdpPerCapita);    
	}
}
}
