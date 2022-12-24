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
	private TreeNode root;
	
	public BinarySearchTree ( )
	{
		root = null;
	}
	
	public void clear ( )
	{
		root = null;
	}
	
	public int countNodes ( )
	{
		return countNodes(root);
	}
	
	public int countNodes(TreeNode node)
	{
		if (node == null)
			return 0;
		else
			return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
	}

	public Integer min()
	{
		return min(root);
	}

	private Integer min(TreeNode node) 
	{
		return new Integer(-1);
	}

	public Integer max()
	{
		return max(root);
	}

	private Integer max(TreeNode node) 
	{
		return new Integer(-1);
	}

	public TreeNode find(Object value) 
	{
		return find(root, value);
	}
	
	public TreeNode find(TreeNode node, Object value)
	{
		return node;
	}
	
	public int rank(Integer value)
	{
		if (value != null)
		{
			return rank(value, root);
		}
		return -1;
	}

	private int rank(Integer value, TreeNode node)
	{
		return -1;
	} 
	
	public boolean add(Object value)
	{
		if(contains(value))
		{
			return false;
		}
		root = add(root, value);
		return true;
	}

	public boolean contains(Object value)
	{
		return contains(root,value);
	}
	
	private boolean contains(TreeNode node, Object value)
	{
		if (node == null)
			return false;
		else
		{
			int diff = ((Comparable<Object>)value).compareTo(node.getValue());
			if (diff == 0)
				return true;
			else if (diff < 0)
				return contains(node.getLeft(), value);
			else
				return contains(node.getRight(), value);
		}
	}
	
	private TreeNode add(TreeNode node, Object value)
	{
		if (node == null)
			node = new TreeNode(value);
		else
		{
			int diff = ((Comparable<Object>)value).compareTo(node.getValue());
			if (diff < 0)
				node.setLeft(add(node.getLeft(), value));
			else
				node.setRight(add(node.getRight(), value));
		}
		return node;
	}
	
	public int levelCount ( ) 
	{
		return(maxDepth(root));
	}
	
	private int maxDepth(TreeNode node) 
	{
		if (node == null) 
		  return 0; 

		// Get the depth of the left and right subtree 
		// using recursion.
		
		int leftDepth = maxDepth(node.getLeft()); 
		int rightDepth = maxDepth(node.getRight()); 

		// Choose the larger one and add the root to it.
		if (leftDepth > rightDepth) 
		  return (leftDepth + 1); 
		else 
		  return (rightDepth + 1);
	}
	
	public void delete(Object value) 
	{
		root = delete(root, value);
	}

	private TreeNode delete(TreeNode node, Object value) 
	{
		return node;
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
