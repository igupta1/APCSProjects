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
	
//  The 6 methods that follow are not necessary for BSTTester1
		
	public TreeNode find(Object value) 
	{
		return find(root, value);
	}
	
	public TreeNode find(TreeNode node, Object value)
	{
		return node;
	}
	
	private TreeNode smallestNode(TreeNode node)
	{
		return node;	
	}
	
	private TreeNode largestNode(TreeNode node)
	{
		return node;	
	}
	
	public void delete(Object value) 
	{
		root = delete(root, value);
	}

	private TreeNode delete(TreeNode node, Object value) 
	{
		int diff = ((Comparable<Object>)value).compareTo(node.getValue());
		if (diff == 0)
			delete(node);
		else if (diff < 0)
			node.setLeft(delete(node.getLeft(), value));
		else
			node.setRight(delete(node.getRight(), value));
		return node;
    }
	
//  Most of the methods above are incomplete, and need to be completed.

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
		printHelper(result, root, 0, 0, size - 1);
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

	public void printHelper(List<List<String>> result, TreeNode node, int level, int left, int right)
	{
		if(node == null)
		{
			return;
		}
		int index = (left + right) / 2;
		result.get(level).set(index, (Integer)(node.getValue())+"");
		printHelper(result, node.getLeft(), level+1, left, index-1);
		printHelper(result, node.getRight(), level+1, index+1, right);
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

C:\Java>java BSTTester1



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




C:\Java>

*/
