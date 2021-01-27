/**
 * This program uses a Binary Search Tree to store
 * the names of countries and their GDP per capitas.
 * It takes the name of the .csv file from the user,
 * and makes nodes out of them, inserting them into
 * a tree. The Binary Search Tree is based its name.
 * <p>
 * After creating and filling the tree, it deletes
 * the countries Australia, Tunisia, and Norway from
 * the tree. Once it does this, it prints a preorder
 * traversal of the tree.
 * <p>
 * Then, it searches for the countries Sri Lanka, North
 * Cyprus, and Tunisia. If found, it will display their GDP
 * per capita. Whether found or not, it will also show how many
 * nodes were visited as it traversed the tree to find the node.
 * <p>
 * It will then delete Malawi, Somalia, Ireland, Greece, and
 * Singapore from the tree. After that, it will print a 
 * postorder traversal of the tree.
 * <p>
 * Finally, it will display the top and bottom 10 countries
 * in the tree based on their GDP per capita.
 * 
 * @author Hailey Francis
 * @version November 16, 2019
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Project4 {
	public static void main(String[] args) {
		BinarySearchTree binSearchTree = new BinarySearchTree();
		Scanner input = new Scanner(System.in);
		
		System.out.println("COP3538 Project 4 -- Xudong Liu");
		System.out.println("Binary Search Trees\n");
		System.out.print("Enter the file name: ");
		
		String fileName = input.next();
		input.close();
		readFile(fileName, binSearchTree);
		
		//inorder traverse
		System.out.println("\nInorder Traversal:");
		System.out.println("Name                GDP per Capita");
		System.out.println("----------------------------------");
		binSearchTree.printInorder(binSearchTree.root);
		System.out.println("----------------------------------\n");
		
		//delete Australia, Tunisia, and Norway
		checkDelete(binSearchTree, "Australia");
		checkDelete(binSearchTree, "Tunisia");
		checkDelete(binSearchTree, "Norway");
		
		//preorder
		System.out.println("\nPreorder Traversal:");
		System.out.println("Name                GDP per Capita");
		System.out.println("----------------------------------");
		binSearchTree.printPreorder(binSearchTree.root);
		System.out.println("----------------------------------");
		
		//find sri lanka, north cyprus, and tunisia
		findCountry(binSearchTree, "Sri Lanka");
		findCountry(binSearchTree, "North Cyprus");
		findCountry(binSearchTree, "Tunisia"); 
		
		System.out.println();
		
		//delete malawi, somalia, ireland, greece, and singapore
		checkDelete(binSearchTree, "Malawi");
		checkDelete(binSearchTree, "Somalia");
		checkDelete(binSearchTree, "Ireland");
		checkDelete(binSearchTree, "Greece");
		checkDelete(binSearchTree, "Singapore");
		
		//postorder
		System.out.println("\nPostorder Traversal:");
		System.out.println("----------------------------------");
		binSearchTree.printPostorder(binSearchTree.root);
		System.out.println("----------------------------------");
		
		
		//print bottom 10
		System.out.println("\nBottom 10 Countries by GDP per Capita:");
		System.out.println("----------------------------------");
		binSearchTree.printBottomTen();
		System.out.println("----------------------------------");
		
		
		//print top 10
		System.out.println("\nTop 10 Countries by GDP per Capita:");
		System.out.println("----------------------------------");
		binSearchTree.printTopTen();
		System.out.println("----------------------------------");

	}
	
	/**
	 * Reads data from file Countries4.csv (or fileName given by user) and inserts the Country name and GDP per capita into
	 * a BinarySearchTree.
	 * @param fileName -- name of file to be read
	 * @param binSearchTreeTemp -- Binary Search Tree to have data inserted
	 */
	public static void readFile(String fileName, BinarySearchTree binSearchTreeName) {
		String name, code, capital; 
		int population, rank;
		double GDP; // doubles will all be read as exponentials
		Scanner r;
		// OPENING FILE //
		try {
			r = new Scanner(new File(fileName));
			r.useDelimiter("\\,|\r\n|\n");
		    // READING FILE //
			for(int i = 0; i < 6; i++) { //meant to cycle through first line of file, which presumably is labels such as in Countries1.csv and Countries2.csv
				r.next();
			}
			while(r.hasNext()) { //assigns values from file to variables 
				// only name, GDP, and population are ultimately used, but all variables are assigned because of format of csv file. //
				name = r.next();
				code = r.next();
				capital = r.next();
				population = r.nextInt();
				GDP = r.nextDouble();
				rank = r.nextInt();
				
				double GDPperCapita = GDP / (double) population;
				
				binSearchTreeName.insert(name, GDPperCapita);
			}
		}catch(InputMismatchException i) {
			System.out.println("Input Mismatch Exception. Program likely tried assigning value to wrong variable due to format of file.");
		}catch(NumberFormatException j) {
			System.out.println("Number format exception. Program likely tried assigning value to wrong variable due to format of file.");
		}catch(FileNotFoundException e) {
			System.out.println("File not found. "
				+ "Please make sure you entered the name of the file correctly "
				+ "and that the file is accessable the next time you run this program.\n"
				+ "(This project was submitted with a file called Countries4.csv.) [This error was thrown by the readFile method]");
		}catch(NullPointerException n) {
			System.out.println("Null pointer exception in readFile");
		}
	}
	
	/**
	 * Uses the find method in the BinarySearchTree class,
	 * as well as printing the number of nodes visited.
	 * @param binSearchTree -- Binary Search Tree to be searched
	 * @param name -- name of country being searched for
	 */
	public static void findCountry(BinarySearchTree binSearchTree, String name) {
		int visited = binSearchTree.find(name);
		System.out.println("Visited nodes: " + visited + "\n");
	} 
	
	/**
	 * Uses the delete method in the BinarySearchTree class, 
	 * it takes the boolean that is returned and prints whether or not
	 * the node was deleted successfully.
	 * @param binSearchTree -- Binary Search Tree having node deleted
	 * @param name -- name of country to be deleted from tree
	 */
	public static void checkDelete(BinarySearchTree binSearchTree, String name) {
		boolean check = binSearchTree.delete(name);
		if(check) {
			System.out.println(name + " was deleted successfully.");
		}else {
			System.out.println(name + " not found. Nothing to delete.");
		}
	}
}
