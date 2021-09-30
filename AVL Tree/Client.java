/**
* Main class of AVL tree program. <br>
* Simulates the functions in the AVL tree program. <br>
* Simulates the following functions of an AVL Tree Data Structures:
* Inserts the nodes: {14, 17, 11, 7, 53, 4, 13, 12, 8}
* @author Kristian Moreno
*/
public class Client 
{
   public static void main(String[] args) 
   {
      AVLTree tree = new AVLTree();
      
      // Basic Tree Insertion
      // Inserts the nodes: {14, 17, 11, 7, 53, 4, 13, 12, 8}
      tree.insert(14);
      tree.insert(17);
      tree.insert(11);
      tree.insert(7);
      tree.insert(53);
      tree.insert(4);
      tree.insert(13);
      tree.insert(12);
      tree.insert(8);
      
      //Basic AVL Tree print functions that prints the contents of the AVL tree
      tree.print();
      
      //Basic AVL Tree deletion
      tree.delete(53);
      tree.print();
      
      //Basic AVL Tree deletion from disc and exer number 2
      tree.delete(11);
      tree.print();
      
      //Basic AVL Tree deletion from disc and exer number 2
      tree.delete(8);
      tree.print();
      
      //Basic AVL Tree search
      tree.search(7);
   }
}