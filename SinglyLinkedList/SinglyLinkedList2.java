/**
 * SinglyLinkedList.java
 *
 * This program uses a Singly Linked List and does a variety of operations
 * to the list in order to manipulate the list. These operations include
 * adding to the beginning of a list, adding to the end of the list, deleting values,
 * inserting values, swapping values, reversing the order of a list, and sorting a list.
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 3/7/2020
 */
import java.util.NoSuchElementException;

public class SinglyLinkedList
{
	//the first and last node of the SinglyLinkedList
	private ListNode first;
	private ListNode last;

	public SinglyLinkedList()
	{
		first = null;
		last = null;
	}

	//this method gets the value of the first node and if the first
	//node is null it returns a NoSuchElementException
	public Object getFirst()
	{
		if (first == null)
		{
			throw new NoSuchElementException();
		}
		else
		{
			return first.getValue();
		}
	}
	
	//this method adds a new node as the first node of the list
	public void addFirst(Object value) 
	{
		first = new ListNode(value, first);
	}
	
	//this method gets the size or how many nodes are in the list
	public int size()
	{
		int count = 0;
		ListNode temp = first; // start at the first node
		while (temp != null) 
		{
			count++;
			temp = temp.getNext();
		}
		return count;
	}
	
	//this method adds a node at the end of the list making it the last node
	public void addLast(Object value)
	{
		last = new ListNode(value);
		if (first == null)
		{
			first = last;
		}
		else
		{
			ListNode temp = first; // start at the first node
			while (temp.getNext() != null) 
			{
				temp = temp.getNext();
			}
			temp.setNext(last);
		}
	}
	
	//this method deletes a node at a specific index in the list
	public void deleteIndex(int index)
	{
		//if the index is 0 the second node becomes the first node
		if (index == 0)
		{
			first = first.getNext();
		}
		
		//if the index is at the end the last node becomes the second-to-last node
		else if (index == size()-1)
		{
			ListNode temp = first; 
			while (temp.getNext().getNext() != null) 
			{
				temp = temp.getNext();
			}
			last = temp;
			last.setNext(null);
		}
		
		//otherwise if the index is elsewhere inside the list connect the
		//node before the index to the node after the index
		else if ((index < size() - 1) && (index > 0))
		{
			int count = 0;
			ListNode temp = first; 
			while (count != index-1) 
			{
				count++;
				temp = temp.getNext();
			}
			temp.setNext(temp.getNext().getNext());
		}
		
	}
	
	//this method inserts a node with the passed in value at the passed in index
	public void insert(Object value, int index)
	{
		ListNode place = new ListNode(value);
		//if the index is 0 add this node as the first node
		if (index == 0)
		{
			addFirst(place.getValue());
		}
		
		//if the index is the size of the list, then add the node as the
		//last node
		else if (index == size())
		{
			addLast(place.getValue());
		}
		//otherwise if the index is inside the list then connect the node before
		//the index to this node and connect this node to the node at the index
		else if ((index > 0) && (index < size()))
		{
			int count = 0;
			ListNode temp = first; 
			while (count != index-1) 
			{
				count++;
				temp = temp.getNext();
			}
			ListNode use = temp.getNext();
			temp.setNext(place);
			place.setNext(use);
		}
																																																							
	}
	
	//this method deletes all nodes with a specific value
	public void deleteValue(Object value)
	{
		//first we get how many nodes of the targeted value there are to delete
		//and we save it in a variable called count
		int count = 0;
		ListNode tmp = first; 
		while (tmp != null) 
		{
			if (tmp.getValue() == value)
			{
				count++;
			}
			tmp = tmp.getNext();
		}
		
		//we repeat this count number of times to delete all the nodes with a given value
		for (int i = 0; i < count; i++)
		{
			//if first has the value, the second node becomes first
			if (first.getValue() == value)
			{
				first = first.getNext();
			}
			
			//if last has the value, the seond to last node becomes last
			else if (last.getValue() == value)
			{
				ListNode temp = first; 
				while (temp.getNext().getNext() != null) 
				{
					temp = temp.getNext();
				}
				last = temp;
				last.setNext(null);
			}
			
			//otherwise go through the list from the beginning and if
			//you find a node with the value delete the node by connecting the node
			//before to the node after
			else
			{	
				ListNode temp = first; // start at the first node
				while (temp.getNext().getValue() != value)
				{
					temp = temp.getNext();
				}
				temp.setNext(temp.getNext().getNext());
				
				
			}
		}
	}
	
	//this method swaps the nodes at two given indices by manipulating connections
	public void swap(int index1, int index2)
	{
		//if index1 and index2 are the same do nothing
		if (index1 == index2) 
		{
			return; 
		}
  
        //search for index1 while keeping track of prev1 and curr1 
        ListNode prev1 = null;
        ListNode curr1 = first;
        int counter = 0; 
        while (curr1 != null && counter != index1) 
        { 
            prev1 = curr1; 
            curr1 = curr1.getNext();
            counter++; 
        } 
  
        //search for index2 while keeping track of prev2 and curr2
        ListNode prev2 = null; 
        ListNode curr2 = first; 
        int count = 0;
        while (curr2 != null && count != index2) 
        { 
            prev2 = curr2; 
            curr2 = curr2.getNext(); 
            count++;
        } 
  
        //if either index1 or index2 are not in bounds of the list do nothing 
        if (curr1 == null || curr2 == null)
        { 
            return; 
        }
  
        //if the first node to be swapped is not head of the list then connect the node
        //before the node at index1 to the node at index2
        if (prev1 != null) 
        {
            prev1.setNext(curr2);
        }
        //if the node at index1 is the first make the node at index2 the new first 
        else
        { 
            first = curr2; 
        }
  
        //if the second node to be swapped is not head of the list then connect the node
        //before the node at index2 to the node at index1
        if (prev2 != null) 
        {
            prev2.setNext(curr1); 
        }
		//if the node at index2 is the first make the node at index1 the new first
        else
        { 
            first = curr1; 
        }
  
        // Otherwise swap the nodes
        ListNode temp = curr1.getNext(); 
        curr1.setNext(curr2.getNext()); 
        curr2.setNext(temp); 
	}
	
	//this method reverses the nodes in a list
	public void reverse()
	{
		//Declare and Initialize pointers
		ListNode prev = null; 
		ListNode curr = first; 
		ListNode next = null; 
		
		
		while (curr != null) 
		{ 
			//Save the next pointer
			next = curr.getNext(); 
			
			//Reverse - point the current node to the previous node
			curr.setNext(prev);
			 
			//Advance prev and curr
			prev = curr; 
			curr = next;
		}
		//set first to prev because prev is the new head of the reversed list
		first = prev;
	}
	
	//this method sorts the nodes in a list in ascending order through a
	//selection sort
	public void selectionSort()
	{
		int max, temp;

		for (int outer = size(); outer > 1; outer--)
		{
			max = 0;
			for (int inner = 1; inner < outer; inner++)
			{
				if (getValueAtIndex(inner) > getValueAtIndex(max))
				{
					max = inner;
				}
			}
			swap(max, outer-1);
		}
	}
	
	//this method is a helper method that gets the value of a node at a specific index 
	public int getValueAtIndex(int index)
	{
		int counter = 0;
		ListNode temp = first; // start at the first node
		while (counter != index)
		{
			//System.out.println("Counter is " + counter);
			temp = temp.getNext();
			counter++;
		}
		return (Integer)temp.getValue();
	}
	
	
	//this method prints the list in a desired format
	public void printList()
	{
		for(int i = 0; i < size(); i++)
		{
			System.out.printf("%3d ", i);
		}
		System.out.println();
		
		ListNode temp = first; // start at the first node
		while (temp != null) 
		{
			System.out.printf("%3d ", temp.getValue());
			temp = temp.getNext(); // go to next node
		}
		System.out.println("\nNumber of elements: " + size());
		System.out.println();
	}
	
	
}
