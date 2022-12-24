/**
 *  UniversitySearch.java
 *
 *  This program has a list of 25 universities. Using that list, it can
 *  sort the list by alphabetical order or it can sort the list from least to
 *  greatest based on the cost of the university.
 * 	This program also uses a binary search to look for a University the user
 * 	provides.
 *
 *  @author Ishaan Gupta
 *  @version 1.0
 *  @since 12/10/2019
 */
 
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class UniversitySearch
{
	/**    The ArrayList of the Universities   */
	private ArrayList<University> college;
	
	/**
	 *  Constructor for the UniversitySearch object
	 *  Loads the information from the specified file.
	 *
	 * @param  fName  name of file to opened (contains university data)
	 */
	public UniversitySearch(String fName)
	{
		college = new ArrayList<University>();
		loadFile(fName);
	}
	
	/**
	 *  Constructor for the UniversitySearch object
	 *  Loads the information from the file "university25.txt".
	 */
	public UniversitySearch ( )
	{
		this("university25.txt");
	}

	/**
	 *  Runs the program
	 */
	public static void main(String [] args)
	{
		UniversitySearch run = new UniversitySearch("university25.txt");
		run.menu();
	}
	
	/**
	 *  Provide the user with a menu of 3 choices (plus exit).  Prompt
	 *  the user to make a choice, and act on this choice.  Loops until
	 *  the user decides to exit.
	 */
	public void menu ( )
	{
		char choice;
		do
		{
			choice = getUserInput();
			switch(choice)
			{
				case '1':
					insertionSortName(college);
					displayUniversities();
					break;
				case '2':
					selectionSortCost(college);
					displayUniversities();
					break;
				case '3':
					findUniversity(college);
					break;
			}
		}while(choice >= '1' && choice <= '3');
		goodBye();
	}
	
	/**
	 *  Prompts the user to enter a char, from the list of '1', '2',
	 *  '3', or '4'.  Keeps asking until one of these is chosen.
	 */
	public char getUserInput ( )
	{
		char choice = '1';
		System.out.println("\n\n1: Display Universities sorted by name (alphabetical)");
		System.out.println("2: Display Universities sorted by cost of attending (least to greatest)");
		System.out.println("3: Search for a University by name");
		System.out.println("4: Exit");
	
		do
		{
			choice = Prompt.getChar("\nPlease Enter 1 through 4, indicating your choice from the menu above");
		}
		while(choice < '1' || choice > '5');
		return choice;
	}
	
	/**
	 *  Opens the text file, and creates the university data, saving the information
	 *  into the ArrayList college.  A typical line from university25.txt looks like this:
	 *  Northwestern	Evanston		IL		75698	1851	 8231
	 *  This method will pull out the university name (a String), the city (a String), 
	 *  the state (a String), the cost (an int), the year (an int), and a student
	 *  population (an int).  These six values will then be used to 
	 *  create a University object, and added to the ArrayList of college.
	 *
	 *  @param  inFileName  name of file to opened (contains university data)
	 */
	private void loadFile(String inFileName)
	{
		Scanner infile = OpenFile.openToRead("university25.txt");
		String temp = null;
		
		while(infile.hasNext())
		{
			String tempName = infile.next();
			String tempCity = infile.next();
			String tempState = infile.next();
			int tempCost = infile.nextInt();
			int tempYear = infile.nextInt();
			int tempPop = infile.nextInt();
			college.add(new University(tempName,tempCity,tempState,tempCost,tempYear,tempPop));
		}
		infile.close();
		
	}

	/**
	 *  Displays the universities, using a frame, and the toString method from the
	 *  University class.
	 */
	public void displayUniversities ( )
	{
		System.out.println("\n\n\n+------------------------+");
		System.out.println("| List of Universities   |");
		System.out.println("+---------------------------------------------------------------------------------------------+");
		System.out.println("|                                                                                             |");
		System.out.println("|          University          City                State      Cost       Founded  Undergrads  |");
		for (int i = 0; i < college.size(); i++)
		{
			if (i%5 == 0)
			{
				System.out.println("|                                                                                             |");
			}
			System.out.print("|  ");
			System.out.printf("%4d  ",(i+1));
			System.out.println(college.get(i) + "       |");
		}
		System.out.println("|                                                                                             |");
		System.out.println("+---------------------------------------------------------------------------------------------+");
	}
	
	
	/**
	 * This is an insertion sort implementation
	 * This method sorts the universities by cost from least to greatest	
	*/
	
	public void insertionSortName (ArrayList<University> list)
	{
		for (int outer = 1; outer < list.size(); outer++)
		{
			int position = outer;
			University key = list.get(position);

			while (position > 0 && (list.get(position - 1).compareTo(key) > 0))
			{
				list.set(position, list.get(position - 1));
				position--;
			}
			list.set(position, key);
		}
	}
	
 	/**
	 *  Swaps the elements in the ArrayList at i and j.
	 */ 
	 
	private void swap(ArrayList<University> list, int i, int j)
	{
		University temp = list.set(j, list.get(i));
		list.set(i, temp);
	}
	
	/**
	 *  A selection sort implementation, sorting the universities by cost.
	 */
	public void selectionSortCost(ArrayList<University> list)
	{
		int max;
		for (int outer = list.size(); outer > 1; outer--)
		{
			max = 0;
			
			for (int inner = 1; inner < outer; inner++)
			{
				
				if (list.get(inner).compareToCost(list.get(max)) > 0)
				{
					max = inner; 
				}

			}
			
			swap(list, max, outer-1);
			
		}
	}

	/**
	 *  Prompts the user to enter the name of a university in the form of:
	 *  Stanford
	 *  Calls a binary search algorithm to accomplish the search.  Continues to
	 *  prompt the user until -1 is entered.
	 */
	public void findUniversity(ArrayList<University> list)
	{
		insertionSortName(college);
		String universityName = new String("");
		do
		{
			System.out.println("\n\n----------------------------------------------------------------\n");
			universityName = Prompt.getString("Please enter a University to search for (-1 to exit)");

			binarySearch(list, universityName);



		}while(!universityName.equals("-1"));
	}

	/**
	 *  A binary search.  A counter is kept, so that the number of steps (times inside the loop)
	 *  can be displayed after each search.
	 */	
	public void binarySearch (ArrayList<University> list, String university)
	{
		int counter = 0;


		int low = 0;
        int high = list.size() - 1;
        int mid;

        while( low <= high )
        {
            mid = ( low + high ) / 2;
            counter++;

            if( list.get(mid).getName().compareTo( university ) < 0 )
                low = mid + 1;
            else if( list.get(mid).getName().compareTo( university ) > 0 )
                high = mid - 1;
            else
            {
                System.out.println("\nThe binary search took " + counter + " steps to find this University.\n");
                System.out.println("The University is: ");
                System.out.println(list.get(mid));
                return;
            }
        }

		System.out.println("\nThe binary search took " + counter + " steps to determine that this University does not exist in the list.");
		return;
	}

	/**
	 *  An exit message.
	 */
	public void goodBye ( )
	{
		System.out.println("\n\nThanks for reviewing the University Search results!\n\n\n");
	}
}
