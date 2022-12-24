import java.util.NoSuchElementException;

public class SinglyLinkedList
{
	private ListNode first;
	private ListNode last;

	public SinglyLinkedList()
	{
		first = null;
		last = null;
	}

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

	public void addFirst(Object value) 
	{
		first = new ListNode(value, first);
	}
	
	public void addLast(Object value)
	{
		last = new ListNode(value);
		if (first.getValue() == null)
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
	
	public Object getLast()
	{
		if (last == null)
			throw new NoSuchElementException();
		else
			return last.getValue();
	}

	public void printList()
	{
		for(int i = 0; i < size(); i++)
		{
			System.out.printf("%2d ", i);
		}
		System.out.println();
		
		ListNode temp = first; // start at the first node
		while (temp != null) 
		{
			System.out.printf("%2d ", temp.getValue());
			temp = temp.getNext(); // go to next node
		}
	}
	
	public void deleteIndex(int index)
	{
		if (index == 0)
		{
			first = first.getNext();
		}
		int count = 0;
		ListNode temp = first;
		while (temp != null)
		{
			if ((count == index-1) && (count < size()-1))
			{
				temp.setNext(temp.getNext().getNext());
			}
			count++;
			temp = temp.getNext();
		}
	}
}
