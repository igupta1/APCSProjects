public class ListTester2
{
	public static void main (String [] args)
	{
		ListTester2 run = new ListTester2();
		run.methods();
	}
	
	public void methods ( )
	{
		printNodes();
		SinglyLinkedList mylist = new SinglyLinkedList();
		testAddFirst(mylist);
		testAddLast(mylist);
		testDelete1(mylist);
		testInsert(mylist);
		testDelete2(mylist);
		testSwap(mylist);
		testReverse(mylist);
	}
	
	public void printNodes ( )
	{
		ListNode list;
		list = new ListNode(13, null);
		System.out.println("\n\n\nThe node contains: " + list.getValue());

		list.setValue(17);
		System.out.println("\nThe node contains: " + list.getValue());
	}
	
	public void testAddFirst(SinglyLinkedList mylist)
	{
		System.out.println("\n\n");
		System.out.println(" TESTING THE METHOD addFirst:\n");
		for (int i = 0; i < 10; i++)
		{
			mylist.addFirst(i*i);
		}
		mylist.printList();
		System.out.println("\n\n");
	}
	
	public void testAddLast(SinglyLinkedList mylist)
	{
		System.out.println(" TESTING THE METHOD addLast:\n");
		for (int i = 1; i < 6; i++)
		{
			mylist.addLast(i);
		}
		mylist.printList();
		System.out.println("\n\n");
	}
	
	public void testDelete1(SinglyLinkedList mylist)
	{
		System.out.println(" TESTING THE METHOD delete (at an index):\n");
		System.out.println(" Delete at index 0:");
		mylist.deleteIndex(0);
		mylist.printList();
		System.out.println("\n Delete at index 7:");
		mylist.deleteIndex(7);
		mylist.printList();
		System.out.println("\n Delete at index 12:");
		mylist.deleteIndex(12);
		mylist.printList();
		System.out.println("\n Delete at index 11:");
		mylist.deleteIndex(11);
		mylist.printList();
		System.out.println("\n Delete at index 11:");
		mylist.deleteIndex(11);
		mylist.printList();
		System.out.println("\n Delete at index 20:");
		mylist.deleteIndex(20);
		mylist.printList();
		System.out.println("\n Delete at index 0:");
		mylist.deleteIndex(0);
		mylist.printList();
		System.out.println("\n Delete at index 9:");
		mylist.deleteIndex(9);
		mylist.printList();
		System.out.println("\n\n");
	}
	
	public void testInsert(SinglyLinkedList mylist)
	{
		System.out.println(" TESTING THE METHOD insert:\n");
		System.out.println(" Insert 37 at index 0:");
		mylist.insert(37,0);
		mylist.printList();
		System.out.println("\n Insert 43 at index 10:");
		mylist.insert(43,10);
		mylist.printList();
		System.out.println("\n Insert 51 at index 6:");
		mylist.insert(51,6);
		mylist.printList();
		System.out.println("\n Insert 51 at index 1:");
		mylist.insert(51,1);
		mylist.printList();
		System.out.println("\n Insert 51 at index 13:");
		mylist.insert(51,13);
		mylist.printList();
		System.out.println("\n Insert 51 at index 8:");
		mylist.insert(51,8);
		mylist.printList();
		System.out.println("\n\n");
	}
	
	public void testDelete2(SinglyLinkedList mylist)
	{
		System.out.println(" TESTING THE METHOD delete (a specific value):\n");
		System.out.println(" Delete the value 9:");
		mylist.deleteValue(9);
		mylist.printList();
		System.out.println("\n Delete the value 37:");
		mylist.deleteValue(37);
		mylist.printList();
		System.out.println("\n Delete the value 43:");
		mylist.deleteValue(43);
		mylist.printList();
		System.out.println("\n Delete the value 99:");
		mylist.deleteValue(99);
		mylist.printList();
		System.out.println("\n Delete the value 51:");
		mylist.deleteValue(51);
		mylist.printList();
		System.out.println("\n\n");
	}
	
	public void testSwap(SinglyLinkedList mylist)
	{
		System.out.println(" TESTING THE METHOD swap (at two given indices):\n");
		System.out.println(" Swap at indices 2 and 5:");
		mylist.swap(2,5);
		mylist.printList();
		System.out.println("\n Swap at indices 3 and 7:");
		mylist.swap(3,7);
		mylist.printList();
		System.out.println("\n Swap at indices 4 and 4:");
		mylist.swap(4,4);
		mylist.printList();
		System.out.println("\n Swap at indices 0 and 1:");
		mylist.swap(0,1);
		mylist.printList();
		System.out.println("\n Swap at indices 2 and 6:");
		mylist.swap(2,6);
		mylist.printList();
		System.out.println("\n Swap at indices 0 and 4:");
		mylist.swap(0,4);
		mylist.printList();
		System.out.println("\n\n");
	}
	
	public void testReverse(SinglyLinkedList mylist)
	{
		System.out.println(" TESTING THE METHOD reverse:\n");
		System.out.println(" Reverse:");
		mylist.reverse();
		mylist.printList();
		System.out.println("\n Reverse:");
		mylist.reverse();
		mylist.printList();
		System.out.println("\n\n");
	}
}

/*
C:\Java\LinkedList>java ListTester2



The node contains: 13

The node contains: 17



 TESTING THE METHOD addFirst:

  0   1   2   3   4   5   6   7   8   9
 81  64  49  36  25  16   9   4   1   0
 Number of elements: 10



 TESTING THE METHOD addLast:

  0   1   2   3   4   5   6   7   8   9  10  11  12  13  14
 81  64  49  36  25  16   9   4   1   0   1   2   3   4   5
 Number of elements: 15



 TESTING THE METHOD delete (at an index):

 Delete at index 0:
  0   1   2   3   4   5   6   7   8   9  10  11  12  13
 64  49  36  25  16   9   4   1   0   1   2   3   4   5
 Number of elements: 14

 Delete at index 7:
  0   1   2   3   4   5   6   7   8   9  10  11  12
 64  49  36  25  16   9   4   0   1   2   3   4   5
 Number of elements: 13

 Delete at index 12:
  0   1   2   3   4   5   6   7   8   9  10  11
 64  49  36  25  16   9   4   0   1   2   3   4
 Number of elements: 12

 Delete at index 11:
  0   1   2   3   4   5   6   7   8   9  10
 64  49  36  25  16   9   4   0   1   2   3
 Number of elements: 11

 Delete at index 11:
  0   1   2   3   4   5   6   7   8   9  10
 64  49  36  25  16   9   4   0   1   2   3
 Number of elements: 11

 Delete at index 20:
  0   1   2   3   4   5   6   7   8   9  10
 64  49  36  25  16   9   4   0   1   2   3
 Number of elements: 11

 Delete at index 0:
  0   1   2   3   4   5   6   7   8   9
 49  36  25  16   9   4   0   1   2   3
 Number of elements: 10

 Delete at index 9:
  0   1   2   3   4   5   6   7   8
 49  36  25  16   9   4   0   1   2
 Number of elements: 9



 TESTING THE METHOD insert:

 Insert 37 at index 0:
  0   1   2   3   4   5   6   7   8   9
 37  49  36  25  16   9   4   0   1   2
 Number of elements: 10

 Insert 43 at index 10:
  0   1   2   3   4   5   6   7   8   9  10
 37  49  36  25  16   9   4   0   1   2  43
 Number of elements: 11

 Insert 51 at index 6:
  0   1   2   3   4   5   6   7   8   9  10  11
 37  49  36  25  16   9  51   4   0   1   2  43
 Number of elements: 12

 Insert 51 at index 1:
  0   1   2   3   4   5   6   7   8   9  10  11  12
 37  51  49  36  25  16   9  51   4   0   1   2  43
 Number of elements: 13

 Insert 51 at index 13:
  0   1   2   3   4   5   6   7   8   9  10  11  12  13
 37  51  49  36  25  16   9  51   4   0   1   2  43  51
 Number of elements: 14

 Insert 51 at index 8:
  0   1   2   3   4   5   6   7   8   9  10  11  12  13  14
 37  51  49  36  25  16   9  51  51   4   0   1   2  43  51
 Number of elements: 15



 TESTING THE METHOD delete (a specific value):

 Delete the value 9:
  0   1   2   3   4   5   6   7   8   9  10  11  12  13
 37  51  49  36  25  16  51  51   4   0   1   2  43  51
 Number of elements: 14

 Delete the value 37:
  0   1   2   3   4   5   6   7   8   9  10  11  12
 51  49  36  25  16  51  51   4   0   1   2  43  51
 Number of elements: 13

 Delete the value 43:
  0   1   2   3   4   5   6   7   8   9  10  11
 51  49  36  25  16  51  51   4   0   1   2  51
 Number of elements: 12

 Delete the value 99:
  0   1   2   3   4   5   6   7   8   9  10  11
 51  49  36  25  16  51  51   4   0   1   2  51
 Number of elements: 12

 Delete the value 51:
  0   1   2   3   4   5   6   7
 49  36  25  16   4   0   1   2
 Number of elements: 8



 TESTING THE METHOD swap (at two given indices):

 Swap at indices 2 and 5:
  0   1   2   3   4   5   6   7
 49  36   0  16   4  25   1   2
 Number of elements: 8

 Swap at indices 3 and 7:
  0   1   2   3   4   5   6   7
 49  36   0   2   4  25   1  16
 Number of elements: 8

 Swap at indices 4 and 4:
  0   1   2   3   4   5   6   7
 49  36   0   2   4  25   1  16
 Number of elements: 8

 Swap at indices 0 and 1:
  0   1   2   3   4   5   6   7
 36  49   0   2   4  25   1  16
 Number of elements: 8

 Swap at indices 2 and 6:
  0   1   2   3   4   5   6   7
 36  49   1   2   4  25   0  16
 Number of elements: 8

 Swap at indices 0 and 4:
  0   1   2   3   4   5   6   7
  4  49   1   2  36  25   0  16
 Number of elements: 8



 TESTING THE METHOD reverse:

 Reverse:
  0   1   2   3   4   5   6   7
 16   0  25  36   2   1  49   4
 Number of elements: 8

 Reverse:
  0   1   2   3   4   5   6   7
  4  49   1   2  36  25   0  16
 Number of elements: 8

 
 
 
C:\Java\LinkedList>
*/
