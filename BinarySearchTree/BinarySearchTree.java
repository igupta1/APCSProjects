/**
 * BinarySearchTree.java
 *
 * This program uses a Binary Search Tree and does a variety of operations
 * to the tree in order to manipulate the tree. Some of these operations 
 * include finding a node, finding the greatest or least value, deleting
 * a node, adding a node, counting the nodes, and finding the maximum depth.
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 4/26/2020
 */

/*
	Notes on Deletion.  To delete a TreeNode node:
	(1)  If node has no children, then replace node with null.
	(2)  If node has one (and only one) child, then "promote"
		 that child to node's place.
	(3)  If node has two children, then identify its successor
		 as the minimum value in the right subtree.  Call this
		 node with the minimum value in the right subtree node2.
		 Put node2 to node's place.  Treat the loss of node2
		 in its original position as a deletion, and follow
		 rules (1) and (2) above, since the original position
		 of node2 can only be a leaf or have one child.
*/

import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

public class BinarySearchTree
{
	//this is the root of the binary search tree
	private TreeNode root;
	
	public BinarySearchTree ( )
	{
		root = null;
	}
	
	//this method clears the tree by setting root to null
	public void clear ( )
	{
		root = null;
	}
	
	public TreeNode getRoot()
	{
		return root;
	}
	
	public void setRoot(TreeNode node)
	{
		root = node;
	}
	
	public int countNodes ( )
	{
		return countNodes(root);
	}
	
	//this method counts the nodes in the binary search tree in a recursive fashion
	public int countNodes(TreeNode node)
	{
		//this base case is if we are at the end of the tree where node is null
		if (node == null)
			return 0;
		//otherwise we count the node we are on and keep counting nodes to the
		//left of the current node and the right of the current node
		else
			return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
	}

	public Integer min()
	{
		return min(root);
	}

	//this method finds the minimum value in the tree
	private Integer min(TreeNode node) 
	{
		//this loop keeps going left until we are at the end of the tree
		//The minimum value in a binary search tree is the value to the very left
		while (node.getLeft() != null)
		{
			node = node.getLeft();
		}
		return (Integer)(node.getValue());
	}

	public Integer max()
	{
		return max(root);
	}

	//this method finds the maximum value in the tree
	private Integer max(TreeNode node) 
	{
		//this loop keeps going right until we are at the end of the tree
		//The maximum value in a binary search tree is the value to the very right
		while (node.getRight() != null)
		{
			node = node.getRight();
		}
		return (Integer)(node.getValue());
	}

	public TreeNode find(Object value) 
	{
		return find(root, value);
	}
	
	//this method finds a number in the Binary Search Tree by going through
	//the tree.
	public TreeNode find(TreeNode node, Object value)
	{
		while (node != null) 
		{
			int result = ((Integer)value).compareTo((Integer)(node.getValue()));
			//if the node is the same as the target node return the node
			if (result == 0)
			  return node;
			//if the target is less than the current node go left
			else if (result < 0)
			  node = node.getLeft();
			//if the target is more than the current node go right
			else 
			  node = node.getRight();
		}
		return null;
	}
	
	public int rank(Integer value)
	{
		if (value != null)
		{
			return rank(value, root);
		}
		return -1;
	}

	//this method finds the rank of a node in the tree by calling the
	//traverse method
	private int rank(Integer value, TreeNode node)
	{	
		int count = 0;
		return(traverse(value, node, count));
		
	}
	
	//this method traverses through the binary search tree using an
	//in order traversal.
	private int traverse(Integer value, TreeNode node, int count)
	{
		//continues left until is reaches the end of the tree
		if (node.getLeft() != null)
		{
			count = traverse(value, node.getLeft(), count++);
		}
		
		//if the node is greater than or equal to the value then return count
		//this means we either reached value or passed value because it is
		//not in the tree
		if (((Integer)(node.getValue())) >= value)
		{
			return count;
		}
			
		count++;
		
		//continues right until is reaches the end of the tree
		if (node.getRight() != null)
		{
			count = traverse(value, node.getRight(), count++);
		}
			
		return count;
	}
	
	//this method checks if it can add a value in the Binary Search tree
	public boolean add(Object value)
	{
		//if the value exists in the tree, return false
		if(contains(value))
		{
			return false;
		}
		//otherwise add the value and return true
		root = add(root, value);
		return true;
	}

	public boolean contains(Object value)
	{
		return contains(root,value);
	}
	
	//this method checks whether the tree contains a value
	private boolean contains(TreeNode node, Object value)
	{
		//if the node is empty return false
		if (node == null)
			return false;
		else
		{
			int diff = ((Integer)value).compareTo((Integer)(node.getValue()));
			//if node is the same as the target return true
			if (diff == 0)
				return true;
			//if the value is less than the node go left
			else if (diff < 0)
				return contains(node.getLeft(), value);
			//if the value is more than the node go right
			else
				return contains(node.getRight(), value);
		}
	}
	
	//this method adds a node to a Binary Search Tree.
	private TreeNode add(TreeNode node, Object value)
	{
		//if the current node is empty seet the current node to value
		if (node == null)
			node = new TreeNode(value);
		else
		{
			int diff = ((Integer)value).compareTo((Integer)(node.getValue()));
			//if value is less than the current node go left
			if (diff < 0)
				node.setLeft(add(node.getLeft(), value));
			//if value is more than the current node go right
			else
				node.setRight(add(node.getRight(), value));
		}
		return node;
	}
	
	public int levelCount ( ) 
	{
		return(maxDepth(root));
	}
	
	//this method finds the maximum depth of the binary search tree
	private int maxDepth(TreeNode node) 
	{
		//the base case is that if it is at the very end return 0
		if (node == null) 
		  return 0; 

		// Get the depth of the left and right subtree.	
		int leftDepth = maxDepth(node.getLeft()); 
		int rightDepth = maxDepth(node.getRight()); 

		//then choose the larger of the two and add 1 to account for the root
		if (leftDepth > rightDepth) 
		  return (leftDepth + 1); 
		else 
		  return (rightDepth + 1);
	}
	
	public void delete(Object value) 
	{
		root = delete(root, value);
	}

	//this method finds a node in the tree to delete
	private TreeNode delete(TreeNode node, Object value) 
	{
		//if the node is null that means the value does not exist and 
		//then it prints an error message and returns null;
		if (node == null)
		{
			System.out.println(" Sorry, but " + value + " could not be found");
			return null;
		}
		
		//if the current node is the same as the target then call the method to delete
		//the node
		else if (((Integer)value).compareTo((Integer)(node.getValue())) == 0)
			return deleteNode(node);
			
		//if the value is less than the current node then go left
		else if (((Integer)value).compareTo((Integer)(node.getValue())) < 0)
		{
			node.setLeft(delete(node.getLeft(), value));
			return node;
		}
		
		//if the value is less than the current node then go left
		else
		{
			node.setRight(delete(node.getRight(), value));
			return node;
		}
	}
	
	//this method deletes the node from the Binary Search Tree
	private TreeNode deleteNode(TreeNode target) 
	{
		//if the target only has a left node then connect it to the left node
		if (target.getRight() == null)
		{
			return target.getLeft();
		}
		
		//if the target only has a right node then connect it to the right node
		else if (target.getLeft() == null)
		{
			return target.getRight();
		}
		
		//if the least value on the right side of the target node is null
		//connect the right node to the tree
		else if (target.getRight().getLeft() == null)
		{
			target.setValue(target.getRight().getValue());
			target.setRight(target.getRight().getRight());
			return target;
		}
		
		//otherwise find the left most value on the right side. This value is the
		//smallest value on teh right of the target node and this node is the
		//closest to the target node
		else
		{
			TreeNode temp = target.getRight();
			//continue going  left until you hit the end of the tree
			while (temp.getLeft().getLeft() != null)
				temp = temp.getLeft();
			
			//set the value of target to the value of the smallest node
			//on the right side of target
			target.setValue(temp.getLeft().getValue());
			//set the left node of temp to be the right node of the left node of temp
			temp.setLeft(temp.getLeft().getRight());
			return target;
		}
	}
	
	//  The methods below are complete.  Do not change them.
	
	public String toString ( )
	{
		return print(root);
	}
	
	public String print(TreeNode node)
	{
		if (node != null) 
		{
			return print(node.getLeft()) + String.format("%4d",((Integer)node.getValue()).intValue()) + print(node.getRight());
		}
		return "";
	}

	public void printInFullForm ( )
	{
		int height = getHeight(root);
		int size = (int)Math.pow(2, height)-1;
		List<List<String>> result = new ArrayList<List<String>>();
		for(int i = 0; i < height; i++)
		{
			List<String> list = new ArrayList<String>();
			for(int j = 0; j < size; j++)
			{
				list.add("  ");
			}
			result.add(list);
		}
		helper(result, root, 0, 0, size - 1);
		System.out.println();
		for(List<String> line : result)
		{
			for(String numberOrSpaces : line)
			{
				System.out.print(numberOrSpaces);
			}
			System.out.println("\n");
		}
	}

	public void helper(List<List<String>> result, TreeNode node, int level, int left, int right)
	{
		if(node == null)
		{
			return;
		}
		int index = (left + right) / 2;
		result.get(level).set(index, (Integer)(node.getValue())+"");
		helper(result, node.getLeft(), level+1, left, index-1);
		helper(result, node.getRight(), level+1, index+1, right);
	}

	public int getHeight(TreeNode node)
	{
		if(node == null)
		{
			return 0;
		}
		return Math.max(1 + getHeight(node.getLeft()), 1 + getHeight(node.getRight()));
	}
}

/*

C:\Java>java BSTTester2



The tree node contains: 43

The tree node contains: 27



 TESTING THE METHOD add:


      63

  37      75

    56  68

--------------------------------------------------
OR     37  56  63  68  75




 TESTING THE METHOD countNodes:


      63

  37      75

    56  68

--------------------------------------------------
OR     37  56  63  68  75

The number of nodes in the tree above is: 5

              63

      37              75

  21      56      68

                    73

--------------------------------------------------
OR     21  37  56  63  68  73  75

The number of nodes in the tree above is: 7




 TESTING THE METHOD countLevels:

              63

      37              75

  21      56      68

                    73

--------------------------------------------------
OR     21  37  56  63  68  73  75

The number of levels in the tree above is: 4

              63

      37              75

  21      56      68      91

14      45      66  73  88

--------------------------------------------------
OR     14  21  37  45  56  63  66  68  73  75  88  91

The number of levels in the tree above is: 4

                              63

              37                              75

      21              56              68              91

  14              45              66      73      88      97

    17          39

--------------------------------------------------
OR     14  17  21  37  39  45  56  63  66  68  73  75  88  91  97

The number of levels in the tree above is: 5

The number of nodes in the tree above is: 15



 TESTING THE METHODS find (a specific value) and rank:

  14  17  21  37  39  45  56  63  66  68  73  75  88  91  97
 Looking for the value 88:
 YES, 88 is in the Binary Search Tree, at "index" 12

  14  17  21  37  39  45  56  63  66  68  73  75  88  91  97
 Looking for the value 14:
 YES, 14 is in the Binary Search Tree, at "index" 0

  14  17  21  37  39  45  56  63  66  68  73  75  88  91  97
 Looking for the value 97:
 YES, 97 is in the Binary Search Tree, at "index" 14

  14  17  21  37  39  45  56  63  66  68  73  75  88  91  97
 Looking for the value 11:
 NO, 11 is NOT in the Binary Search Tree

  14  17  21  37  39  45  56  63  66  68  73  75  88  91  97
 Looking for the value 54:
 NO, 54 is NOT in the Binary Search Tree


 TESTING THE METHODS max and min:

  14  17  21  37  39  45  56  63  66  68  73  75  88  91  97
The minimum value in this tree is: 14
The maximum value in this tree is: 97

  10  14  17  21  37  39  45  56  63  66  68  73  75  88  91  97  98
The minimum value in this tree is: 10
The maximum value in this tree is: 98


 TESTING THE METHOD delete (a specific value):

                              63

              37                              75

      21              56              68              91

  14              45              66      73      88      97

10  17          39                                          98

--------------------------------------------------
OR     10  14  17  21  37  39  45  56  63  66  68  73  75  88  91  97  98

 Delete the value 37:

                              63

              39                              75

      21              56              68              91

  14              45              66      73      88      97

10  17                                                      98

--------------------------------------------------
OR     10  14  17  21  39  45  56  63  66  68  73  75  88  91  97  98


 Delete the value 45:

                              63

              39                              75

      21              56              68              91

  14                              66      73      88      97

10  17                                                      98

--------------------------------------------------
OR     10  14  17  21  39  56  63  66  68  73  75  88  91  97  98


 Delete the value 63:

                              66

              39                              75

      21              56              68              91

  14                                      73      88      97

10  17                                                      98

--------------------------------------------------
OR     10  14  17  21  39  56  66  68  73  75  88  91  97  98


 Delete the value 21:

                              66

              39                              75

      14              56              68              91

  10      17                              73      88      97

                                                            98

--------------------------------------------------
OR     10  14  17  39  56  66  68  73  75  88  91  97  98


 Delete the value 91:

              66

      39              75

  14      56      68      97

10  17              73  88  98

--------------------------------------------------
OR     10  14  17  39  56  66  68  73  75  88  97  98


 Delete the value 66:

              68

      39              75

  14      56      73      97

10  17                  88  98

--------------------------------------------------
OR     10  14  17  39  56  68  73  75  88  97  98


 Delete the value 70:
 Sorry, but 70 could not be found.

              68

      39              75

  14      56      73      97

10  17                  88  98

--------------------------------------------------
OR     10  14  17  39  56  68  73  75  88  97  98




C:\Java>

*/
