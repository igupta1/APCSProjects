


import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;
import java.util.ArrayList;

public class AnimatedSorts 
{ 
	/**  The ArrayList of Integer, to be created and sorted, repeatedly.          */
	private ArrayList<Integer> list;
	
	/**  The array representing different delays, in milliseconds.                */
	private final int [] delay = {0, 10, 20, 30, 40};
	
	/**  The delay choice, an index of the delay array above.                     */
	private int delayChoice;
	
	/** Creates an AnimatedSorts object.  The ArrayList is initialized, with a
	 *  size of zero, and the delayChoice is set to zero.
	 */
	public AnimatedSorts ( )
	{ 
		list = new ArrayList<Integer>();
		delayChoice = 0;
	}

	/**
	 *  The main method, to create an instance of AnimatedSorts.  The canvas
	 *  is set up, and the GUI runs.
	 */
	public static void main(String[] args)
	{
		AnimatedSorts sorts = new AnimatedSorts();
		sorts.setUpCanvas();
		sorts.runGUI();
	}
	
	/** 
	 *  Sets up the canvas, using methods from StdDraw.  This includes
	 *  setting up the canvas size, the horizontal scale (Xscale), and
	 *  the vertical scale (Yscale).  Double buffering is enabled, so that
	 *  the animation will run smoothly.
	 */
	public void setUpCanvas ( )
	{
		StdDraw.setCanvasSize(1500, 600);
		StdDraw.setXscale(0, 1500);
		StdDraw.setYscale(0, 600);

		StdDraw.enableDoubleBuffering();
	}
	
	/** 
	 *  Runs the Graphical User Interface.  This includes an infinite loop,
	 *  where the user may choose a time delay (in milliseconds), and the
	 *  type of sort.  When the user chooses the sort, the sort will run and
	 *  animate, to show what is happening to the elements of the ArrayList
	 *  during the sort.
	 */
	public void runGUI ( )
	{
		int lengthOfList = 100;
		int maxValue = 520;
		int buttonChoice = 0;
		createList(lengthOfList, maxValue, list);
		
		while(true)
		{
			StdDraw.clear(new Color(25,25,25));
			showList(list, 0);
			buttonChoice = buttonReaction(buttonChoice);
			if(buttonChoice >= 1 && buttonChoice <= 5)
			{
				switch(buttonChoice)
				{
					case 1: bubbleSort(list); break;
					case 2: selectionSort(list); break;
					case 3: insertionSort(list); break;
					case 4: mergeSort(list, 0, list.size() - 1); break;
					case 5: otherSort(list); break;
				}
				
				createList(lengthOfList, maxValue, list);
				StdDraw.pause(1000);
				showList(list, 0);
			}
			buttonChoice = 0;
			StdDraw.show();
			StdDraw.pause(50);
		}
	}
	
	/**
	 *  Reacts to the user's press of a button, recording the choice for
	 *  a particular sort, or a time delay (in milliseconds).
	 *  @param buttonChoice    The number of the button representing the sort choice.
	 *  @return                The number of the button representing the sort choice.
	 */
	public int buttonReaction(int buttonChoice)
	{
		double x = StdDraw.mouseX();
		double y = StdDraw.mouseY();
		if(x >= 1280 && x <= 1480 && StdDraw.isMousePressed())
		{
			if(y >= 125 && y <= 195)
			{
				buttonChoice = 5;
			}
			else if(y >= 215 && y <= 285)
			{
				buttonChoice = 4;
			}
			else if(y >= 305 && y <= 375)
			{
				buttonChoice = 3;
			}
			else if(y >= 395 && y <= 465)
			{
				buttonChoice = 2;
			}
			else if(y >= 485 && y <= 555)
			{
				buttonChoice = 1;
			}
		}
		if(y >= 74 && y <= 106 && x >= 1281 && x <= 1479 && StdDraw.isMousePressed())
		{
			delayChoice = (int)(x - 1281) / 40;
		}
		drawButtons(buttonChoice);
		return buttonChoice;
	}	

	/**
	 *  Draws all of the buttons, including those for the sorts, and those for
	 *  the pause time in milliseconds.
	 *  @param buttonChoice    The number of the button representing the sort choice.
	 */
	public void drawButtons(int buttonChoice)
	{		
		Font font = new Font("Arial", Font.BOLD, 16);
		StdDraw.setFont(font);
		for(int i = 0; i <= 4; i++)
		{
			if(i == buttonChoice - 1)
			{
				StdDraw.setPenColor(new Color(255, 0, 0));
				StdDraw.filledRectangle(1380, 525 - 90 * i, 105, 38);
			}
			if(i == delayChoice)
			{
				StdDraw.setPenColor(new Color(255, 0, 0));
				StdDraw.filledRectangle(1300 + 40 * i, 90, 19, 16);
			}
			StdDraw.setPenColor(new Color(50,50,50));
			StdDraw.filledRectangle(1380, 525 - 90 * i, 101, 35);
			StdDraw.setPenColor(new Color(240,240,240));
			StdDraw.filledRectangle(1380, 525 - 90 * i, 97, 32);
			StdDraw.filledRectangle(1300 + 40 * i, 90, 17, 14);
			StdDraw.setPenColor(new Color(0, 0, 0));
			StdDraw.text(1300 + 40 * i, 88, "" + (10 * i));
		}
		font = new Font("Arial", Font.BOLD, 42);
		StdDraw.setFont(font);
		StdDraw.text(1380, 520, "Bubble");
		StdDraw.text(1380, 430, "Selection");
		StdDraw.text(1380, 340, "Insertion");
		StdDraw.text(1380, 250, "Merge");
		StdDraw.text(1380, 160, "Other");
	}

	/**
	 *  Fills in the ArrayList of Integer, with random numbers from 10 to maxValue, with
	 *  a size of lengthOfList.
	 *  @param lengthOfList   The number of the elements to be created in the list.
	 *  @param maxValue       The maximum integer value possible in the list.
	 *  @param list           The ArrayList to be created.
	 */
	public void createList(int lengthOfList, int maxValue, ArrayList<Integer> list)
	{
		list.clear();
		for(int i = 0; i < lengthOfList; i++)
		{
			list.add(((int)(Math.random() * (maxValue - 10))) + 10);
		}
	}

	/**
	 *  Draws the ArrayList to the GUI.
	 *  @param list            The ArrayList to be drawn.
	 *  @param buttonChoice    The number of the button representing the sort choice.
	 */
	public void showList(ArrayList<Integer> list, int buttonChoice)
	{
		StdDraw.clear(new Color(25,25,25));
		drawButtons(buttonChoice);
		for(int k = 0; k < list.size(); k++)
		{
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledRectangle(50 + 12 * k, list.get(k).intValue()/2 + 50, 4, list.get(k).intValue()/2);
		}
		StdDraw.show();
		StdDraw.pause(delay[delayChoice]);
	}

	/**
	 *  Sorts the list using the bubble sort algorithm.  This is the least efficient
	 *  of the 3 quadratic sorting algorithms we studied.
	 *  @param list            The ArrayList to be sorted.
	 */
	public void bubbleSort(ArrayList <Integer> list)
	{
		
		for (int outer = 0; outer < list.size() - 1; outer++)
		{
			for (int inner = 0; inner < list.size() - outer - 1; inner++)
			{
				showList(list, 1);
				if (list.get(inner).compareTo(list.get(inner + 1)) > 0)
				{
					Integer temp = list.set(inner, list.get(inner + 1));
					list.set(inner + 1, temp);
					showList(list, 1);
				}
			}
		}
		showList(list, 1);
	}

	/**
	 *  Sorts the list using the selection sort algorithm.  This sort is typically slightly
	 *  more efficient than the bubble sort.
	 *  @param list            The ArrayList to be sorted.
	 */
	public void selectionSort(ArrayList <Integer> list)
	{
		
		
		Integer max, temp;
		for (int outer = list.size(); outer > 1; outer--)
		{
			max = 0;
			showList(list, 2);
			for (int inner = 1; inner < outer; inner++)
			{
				
				if (list.get(inner) > list.get(max))
				{
					max = inner; // a new largest item is found
				}
				//showList(list, 2);
			}
			
			//swap array[outer-1] & array[max]
			//temp = array[outer-1];
			//array[outer-1] = array[max];
			//array[max] = temp;
			
			temp = list.set(outer-1, list.get(max));
			list.set(max, temp);
			showList(list, 2);
			
		}
		showList(list, 2);
	}

	/**
	 *  Sorts the list using the insertion sort algorithm.  This sort is the most efficient
	 *  of the 3 quadratic sorting algorithms we studied.
	 *  @param list            The ArrayList to be sorted.
	 */
	public void insertionSort(ArrayList <Integer> list)
	{
		for (int outer = 1; outer < list.size(); outer++)
		{
			showList(list, 3);
			int position = outer;
			int key = list.get(position);

			// Shift larger values to the right
			while (position > 0 && list.get(position - 1) > key)
			{
				showList(list, 3);
				list.set(position, list.get(position - 1));
				position--;
			}
			list.set(position, key);
		}
		showList(list, 3);
	}

	/**
	 *  Sorts the list using the merge sort algorithm.  This sort is an n log n sort,
	 *  so that it is more efficient than any quadratic sorting algorithm.  It uses
	 *  recursion to sort the list.
	 *  @param a               The ArrayList to be sorted.
	 *  @param from            The index in the ArrayList where the sorting starts.
	 *  @param to              The index in the ArrayList where the sorting ends.
	 */
	public void mergeSort(ArrayList<Integer> a, int from, int to)
	{
		//showList(list, 4);
		if (to - from < 2)
		{
			if (to > from && a.get(to) < a.get(from))
			{
				
				Integer temp = a.set(to, a.get(from));
				a.set(from, temp);
			}
		}
		 
		else
		{
			int middle = (from + to) / 2;
			mergeSort(a, from, middle);
			mergeSort(a, middle + 1, to);
			merge(a, from, middle, to);
		}
		showList(list, 4);
	}

	/**
	 *  A helper method for the mergeSort, that merges two parts of the list.
	 *  @param a               The ArrayList to be sorted.
	 *  @param from            The index in the ArrayList where the sorting starts.
	 *  @param middle          The index in the ArrayList that is in the middle, between from and to.
	 *  @param to              The index in the ArrayList where the sorting ends.
	 */
	public void merge(ArrayList<Integer> a, int from, int middle, int to)
	{
		int i = from;
		int j = middle + 1;
		int k = from;
		//System.out.println(a.size());
		ArrayList<Integer> temp = new ArrayList<Integer>(a.size());
		for (int n = 0; n < a.size(); n++)
		{
			temp.add(0);
		}
		
		//System.out.println(temp.size());

		while (i <= middle && j <= to)
		{
			if (a.get(i) < a.get(j))
			{
				temp.set(k, a.get(i));
				i++;
			}
			else
			{
				temp.set(k, a.get(j));
				j++;
			}
			k++;
		}
		while (i <= middle)
		{
			temp.set(k,a.get(i));
			i++;
			k++;
		}
		while (j <= to)
		{
			temp.set(k, a.get(j));
			j++;
			k++;
		}
		for (k = from; k <= to; k++)
		{
			a.set(k, temp.get(k));
		}
		showList(list, 4);
		
	}

	/**
	 *  Another sort, to be chosen and implemented by the programmer.
	 *  @param a            The ArrayList to be sorted.
	 */
	public void otherSort(ArrayList<Integer> a)
	{
		//showList(list, 5);
		recursiveSort(a, 0, a.size()-1);
	}
	
	public void recursiveSort(ArrayList<Integer> a, int from, int to)
	{
		if (from >= to)
		{
			return;
		}
		
		int p = (from + to)/2;
		
		int i = from;
		int j = to;
		
		while (i <= j)
		{
			if (a.get(i) <= a.get(p))
				i++;
			else if (a.get(j) >= a.get(p))
				j--;
			else
			{
				Integer temp = a.set(i, a.get(j));
				a.set(j, temp);
				
				i++;
				j--;
			}
		}
		
		if (p < j)
		{
			//swap(a,j,p);
			
			Integer temp = a.set(j, a.get(p));
			a.set(p, temp);
				
			p = j;
		}
		else if (p > i)
		{
			//swap (a,i,p);
			Integer temp = a.set(i, a.get(p));
			a.set(p, temp);
				
			p = i;
		}
		showList(list, 5);
		
		recursiveSort(a, from, p-1);
		showList(list, 5);
		recursiveSort(a, p + 1, to);
		showList(list, 5);
	}
} 
