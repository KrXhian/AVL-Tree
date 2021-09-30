/**
* BST as a main class for the Binary Search Tree.
* Encompassess all the tree algorithms including the AVL Tree
*
* @author Kristian Moreno
*/
public abstract class BST
{
   /** Sole constructor; typically implicit and cannot be instantiated. */  
   BST()
   {
   
   }
   /**
   * Searches the node in the Binary Tree and prints it out.
   *
   * @param key  The data/key of the node being searched.
   */
   public abstract void search(int key);
   
   /**
   *  Searches a node within the tree with binary search scheme.
   *
   *  @param root  The first node of the Tree.
   *  @param key  The data/key used to lookup in the tree
   *  @return true  If the key matches a certain node.
   */
   public abstract boolean lookup(Node root, int key) ;
   
   /**
   * Inserts a node in the Binary Tree in a Breadth-First Order.
   *
   * @param data  The data of the node being inserted.
   */
   public abstract void insert(int data);
   
   /**
   * Deletes the node in the Binary Tree.
   *
   * @param key  The data/key of the node being deleted.
   */
   public abstract void delete(int key);
   
   /**
   * Enumerates and Prints all the nodes in the tree in its: <br>
   * In-Order Traversal <br>
   * Pre-Order Traversal <br>
   * Post-Order Traversal
   */
   public abstract void print();
}


/**
* Node Class to store datas and references for parents and descendants
*/
class Node 
{
   Node left, right;
   int data, height;
  
   /** 
   * Constructs an empty Node
   * @param data  The actual data of the node.
   */
   public Node (int data) 
   {
      this.left = null;
      this.right = null;
      this.data = data;
      this.height = 1;
   }
}


/**
* Array based Queue to enqueue and dequeue nodes
*/
class Queue 
{
   public static int size, cap;
   Node front, queue[];
   
   /**
   * Constructs an empty queue.
   *
   * @param cap  The maximum cap of elements the queue can hold
   */
   public Queue(int cap) 
   {
      this.size = -1;
      this.front = null;
      this.cap = cap;
      queue = new Node[cap];
   }
   
   /**
   * Checks whether the Queue is Empty.
   *
   * @return true  If the queue is empty.
   */ 
   public boolean isEmpty() 
   {
      return (size == -1);
   }
   
   /**
   * Adds a node to the queue.
   *
   * @param node  The node to be added in the queue.
   */
   public void offer(Node node) 
   {
      queue[++size] = node;
   }
   
   /**
   * Removes a node from the queue.
   *
   * @return removed  The node removed from the queue.
   */
   public Node poll() 
   {
      Node removed = queue[0];
      for (int i = 0; i < size; i++) 
      {
         queue[i] = queue[i + 1];
      }
      queue[size] = null;
      size--;
      return removed;
   }
}


/**
* Subclass of BST ADT named Binary Search Tree to simulate all the basic Functions of a Binary Search Tree.
*/
class BinarySearchTree extends BST
{
    /** The first node of the Tree. */
   private Node root;
   
   /** The size of the Tree. */
   private int size; 
  
   /**
   * Checks whether the BinTree is Empty.
   *
   * @param root  The first node of the Tree.
   * @return true  If the tree is empty.
   */
   public boolean isEmpty(Node root) 
   {
      return (root == null);
   }
   
   public void size() 
   {
      System.out.println("Tree Size: " + size);
   }
   
   /** Constructs an empty Binary Search Tree. */
   public BinarySearchTree() 
   {
      this.root = null;
      this.size = 0;
   }
   
   
   public void search(int key) 
   {
      if (isEmpty(root)) 
      {
         System.out.println("Empty Tree");
      }
      if(lookup(root, key) == true) 
      {
         System.out.println("\n" + key + " has been found in the tree.");
      }
      else 
      {
         System.out.println(key + " is not in the tree.");
      }
   }
   
   
   public boolean lookup(Node root, int key) 
   {
      Node current = root;
      while(current != null) 
      {
         if(current.data == key) 
         {
            return true;
         }
         else if (key < current.data) 
         {
            current = current.left;
         }
         else 
         {
            current = current.right;
         }
      }
      return false;
   }
   
  
   public void insert(int data) 
   {
      root = insertRecursive(root, data);
      System.out.println("Insert " + data + " to the tree.");
      size++;
   }
  
   /**
   * Inserts a new node to the binary search tree recursively.
   *
   * @param node  A storage that contains certain datas in a tree.
   * @param data  The data of the node being inserted.
   * @return node  Returns the inserted node.
   */
   protected Node insertRecursive(Node node, int data) 
   {
      if (node == null) 
      {
         return new Node(data);
      }
      if (data < node.data) 
      {
         node.left = insertRecursive(node.left, data);
      }
      else 
      {
         node.right = insertRecursive(node.right, data);
      }
      return node;
   }  

   /**
   * Finds the smallest node within the tree as a successor to a deleted node.
   *
   * @param current  The current node in the iteration.
   * @return current  The smallest node.
   */
   protected static Node minKey(Node current) 
   {
      while (current.left != null) 
      {
         current = current.left;
      }      
      return current;
   }
   
   
   public void delete(int key) 
   {
      if (isEmpty(root)) 
      {
         System.out.println("Empty Tree");
      }
      if(lookup(root, key) == true) 
      {
         size--;
         System.out.println("\nRemoval of " + key + " completed");
         root = deleteRecursive(root, key);
      }
      else 
      {
         System.out.println("\n" + key + " does not exist in the tree.");
      }
   }
   
   /**
   * Removes a specific node in the binary search tree recursively.
   *
   * @param root  The first node of the Tree.
   * @param key  The data/key of the node being deleted.
   * @return root  Returns the root node.
   */
   protected Node deleteRecursive(Node root, int key) 
   {
      Node parent = null;
      Node current = root;
      while (current != null && current.data != key) 
      {
         parent = current;
         if (key < current.data) 
         {
            current = current.left;
         }
         else 
         {
            current = current.right;
         }
      }
      
      if (current == null) 
      {
         return root;
      }
      
      //Deleting a Leaf/Node with no Children
      if (current.left == null && current.right == null)
      {
         if (current != root) 
         {
            if (parent.left == current) 
            {
               parent.left = null;
               System.out.println("Node has no children; No Successor");
            }
            else 
            {
               parent.right = null;
               System.out.println("Node has no children; No Successor");
            }
         }
         else 
         {
            root = null;
         }
      }
      
      //Deleting a Node with 2 Children
      else if (current.left != null && current.right != null)
      {
         Node successor = minKey(current.right);
         deleteRecursive(root, successor.data);
         current.data = successor.data;
         System.out.println("Successor node: " + current.data);
      }
      
      //Deleting a Node with 1 Children
      else 
      {
         Node child = (current.left != null)? current.left: current.right;
         if (current != root) 
         {
            if (current == parent.left) 
            {
               parent.left = child;
               System.out.println("Successor node: " + parent.left.data);
            }
            else 
            {
               parent.right = child;
               System.out.println("Successor node: " + parent.right.data);
            }
         }
         else 
         {
            root = child;
         }
      }
      return root;
   }
   
  
   public void print() 
   {
      if (isEmpty(root)) 
      {
         System.out.println("Empty Tree");
      }
      else 
      {
         System.out.print("\nIn-order Traversal: ");
         inOrder(root);
         System.out.print("\nPre-order Traversal: ");
         preOrder(root);
         System.out.print("\nPost-order Traversal: ");
         postOrder(root);
         System.out.println("");
      }
   }
   
   /**
   * Traverses the Tree in In-Order and Enumarates all the nodes one by one. 
   *
   * @param node  A storage that contains certain datas in a tree.
   */ 
   protected void inOrder(Node node) 
   {
      if (node == null) 
      {
         return;
      }
      inOrder(node.left);
      System.out.print(node.data + " ");
      inOrder(node.right);
   }
   
   /**
   * Traverses the Tree in Pre-Order and Enumarates all the nodes one by one.
   *
   * @param node  A storage that contains certain datas in a tree. 
   */   
   protected void preOrder(Node node) 
   {
      if (node == null) 
      {
         return;
      }
      System.out.print(node.data + " ");
      preOrder(node.left);
      preOrder(node.right);
   }
  
   /**
   * Traverses the Tree in Post-Order and Enumarates all the nodes one by one. 
   * 
   * @param node  A storage that contains certain datas in a tree.
   */ 
   protected void postOrder(Node node) 
   {
      if (node == null) 
      {
         return;
      }
      postOrder(node.left);
      postOrder(node.right);
      System.out.print(node.data + " ");
   }
}


/**
* Subclass of Binary Search Tree ADT named AVL Tree to override the basic functions of Binary Search Tree to account for the AVL Tree's rotation.
*/
class AVLTree extends BinarySearchTree 
{
   /** The first node of the Tree. */
   public static Node root;

   /** The size of the Tree. */
   public int size;
   
   /** Constructs an empty AVL Tree. */
   public AVLTree() 
   {
      this.root = null;
      this.size = 0;
   }
   
   /**
   * Returns a specific node's height.
   *
   * @param node  A storage that contains certain datas in a tree.
   * @return node.height  The node's height.
   */
   private int height(Node node) 
   {
      if(node == null) 
      {
         return 0;
      }
      return node.height;
   }
   
   /**
   * Compares two values to check which is greater.
   *
   * @param a  Left node
   * @param b  Right node
   * @return a  if its greater than b. b otherwise
   */
   private int max(int a, int b) 
   {
      return (a > b) ? a : b;
   }
   
   /** 
   * Rotates to right to re-balance the tree if an imbalance occurs.
   *
   * @param y  A specific node
   * @return x  A specific node's left descendant
   */
   private Node rotateRight(Node y) 
   {
      Node x = y.left;
      Node z = x.right;
      x.right = y;
      y.left = z;
      y.height = max(height(y.left), height(y.right)) + 1;
      x.height = max(height(x.left), height(x.right)) + 1;
      return x;
   }
   
   /** 
   * Rotates to left to re-balance the tree if an imbalance occurs.
   *
   * @param x  A specific node
   * @return y  A specific node's right descendant
   */
   private Node rotateLeft(Node x) 
   {
      Node y = x.right;
      Node z = y.left;
      y.left = x;
      x.right = z;
      x.height = max(height(x.left), height(x.right)) + 1;
      y.height = max(height(y.left), height(y.right)) + 1;
      return y;
   }
   
   /**
   * Tracks the balance factor of a specific node.
   *
   * @param node  A storage that contains certain datas in a tree.
   * @return  The difference of its descendants
   */
   private int updateBalance(Node node) 
   {
      if (node == null) 
      {
         return 0;
      }
      return height(node.left) - height(node.right);
   }
   
   /**
   * Searches the node in the Binary Tree and prints it out.
   *
   * @param key  The data/key of the node being searched.
   */
   @Override
   public void search(int key) 
   {
      if (isEmpty(root)) 
      {
         System.out.println("Empty Tree");
      }
      if(lookup(root, key) == true) 
      {
         System.out.println("\n" + key + " has been found in the tree.");
      }
      else 
      {
         System.out.println(key + " is not in the tree.");
      }
   }
   
   /**
   * Inserts a node in the Binary Tree in a Breadth-First Order.
   *
   * @param data  The data of the node being inserted.
   */
   @Override
   public void insert(int data) 
   {
      root = insertRecursive(root, data);
      System.out.println("Insert " + data + " to the tree.");
      size++;
   }

   /**
   * Inserts a new node to the binary search tree recursively.
   *
   * @param node  A storage that contains certain datas in a tree.
   * @param data  The data of the node being inserted.
   * @return node  Returns the inserted node.
   */
   @Override
   protected Node insertRecursive(Node node, int data) 
   {
      if (node == null) 
      {
         return new Node(data);
      }
      if (data < node.data) 
      {
         node.left = insertRecursive(node.left, data);
      }
      else 
      {
         node.right = insertRecursive(node.right, data);
      }
      
      node.height = 1 + max(height(node.left), height(node.right));
      int balanceFactor = updateBalance(node);
      
      if (balanceFactor > 1) 
      {
         if (data < node.left.data) 
         {
            return rotateRight(node);
         }
         else if (data > node.left.data) 
         {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
         }
      }
      
      if (balanceFactor < -1) 
      {
         if (data > node.right.data) 
         {
            return rotateLeft(node);
         }
         else if (data < node.right.data) 
         {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
         }
      }
      return node;
   }
   
   /**
   * Deletes the node in the Binary Tree.
   *
   * @param key  The data/key of the node being deleted.
   */
   @Override
   public void delete(int key) 
   {
      if (isEmpty(root)) 
      {
         System.out.println("Empty Tree");
      }
      if(lookup(root, key) == true) 
      {
         size--;
         System.out.println("\nRemoval of " + key + " completed");
         root = deleteRecursive(root, key);
      }
      else 
      {
         System.out.println("\n" + key + " does not exist in the tree.");
      }
   }
   
   /**
   * Removes a certain node from the binary search tree recursively.
   *
   * @param root  The first node of the Tree.
   * @param data  The data of the node to be deleted.
   * @return root  The first node of the Tree.
   */
   @Override
   protected Node deleteRecursive(Node root, int data) 
   {
      if (isEmpty(root)) 
      {
         return root;
      }
      if (data < root.data) 
      {
         root.left = deleteRecursive(root.left, data);
      }
      else if (data > root.data) 
      {
         root.right = deleteRecursive(root.right, data);
      }
      else 
      {
         if ((root.left == null) || (root.right == null)) 
         {
            Node current = null;
            if (current == root.left) 
            {
               current = root.right;
            }
            else current = root.left;
            if (current == null) 
            {
               current = root;
               root = null;
            }
            else root = current;
         }
         else 
         {
            Node current = minKey(root.right);
            root.data = current.data;
            root.right = deleteRecursive(root.right, current.data);
         }
      }
      
      if (isEmpty(root)) 
      {
         return root;
      }
      root.height = max(height(root.left), height(root.right)) + 1;
      int balanceFactor = updateBalance(root);
      
      if (balanceFactor > 1) 
      {
         if (updateBalance(root.left) >= 0) 
         {
            return rotateRight(root);
         }
         else 
         {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
         }
      }
      
      if (balanceFactor < -1) 
      {
         if (updateBalance(root.right) <= 0) 
         {
            return rotateLeft(root);
         }
         else 
         {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
         }
      }
      return root;
   }
   
   /**
   * Enumerates and Prints all the nodes in the tree in its: <br>
   * In-Order Traversal <br>
   * Pre-Order Traversal <br>
   * Post-Order Traversal
   */
   @Override
   public void print() 
   {
      if (isEmpty(root)) 
      {
         System.out.println("Empty Tree");
      }
      else 
      {
         System.out.print("\nPre-order Traversal: ");
         preOrder(root);
         System.out.print("\nIn-order Traversal: ");
         inOrder(root);
         System.out.print("\nPost-order Traversal: ");
         postOrder(root);
         System.out.println("");
      }
   }
}
