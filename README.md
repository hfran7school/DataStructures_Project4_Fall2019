# DataStructures_Project4_Fall2019
COP 3538: Project 4 -- Binary Search Tree
 * @author Hailey Francis (n01402670@unf.edu)
 * @version November 16, 2019
 
Project4.java
 * This program uses a Binary Search Tree to store
   the names of countries and their GDP per capitas.
   It takes the name of the .csv file from the user,
   and makes nodes out of them, inserting them into
   a tree. The Binary Search Tree is based its name.
 * After creating and filling the tree, it deletes
   the countries Australia, Tunisia, and Norway from
   the tree. Once it does this, it prints a preorder
   traversal of the tree.
 * Then, it searches for the countries Sri Lanka, North
   Cyprus, and Tunisia. If found, it will display their GDP
   per capita. Whether found or not, it will also show how many
   nodes were visited as it traversed the tree to find the node.
 * It will then delete Malawi, Somalia, Ireland, Greece, and
   Singapore from the tree. After that, it will print a 
   postorder traversal of the tree.
 * Finally, it will display the top and bottom 10 countries
   in the tree based on their GDP per capita.
   
BinarySearchTree.java
 * This class is the implementation of Binary Search Tree in Project 4.
   It has two separate insert methods that insert based on name and GDP,
   a delete method that deletes nodes based on the number of children the
   node has, and able to print the top and bottom 10 countries based on 
   GDP per capita in the tree. 
