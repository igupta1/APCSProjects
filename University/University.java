/**
 *  University.java
 *  Represents a University, with fields for name,
 *  city, state, cost of attendance, year founded, and
 *  undergraduate population. The Comparable interface is
 *  implemented, toString is overridden from the Object
 *  class, and a getName method is included.
 *  @author Scott DeRuiter and Ishaan Gupta
 *  @version 1.0
 *  @since 12/10/2019
 */

public class University implements Comparable<University>
{
	/**    The name of the University.                            */
	private String name;
	/**    The city where the University can be found.            */
	private String city;
	/**    The state where the University can be found.           */
	private String state;
	/**    The cost of attending the University for one year.     */
	private int costOfAttending;
	/**    The year the University was founded.                   */
	private int founded;
	/**    The undergraduate population at the University.        */
	private int undergradPop;
	
	/**
	 *  Constructor for the University object
	 *
	 * @param  n   name of University
	 * @param  c   city of University
	 * @param  s   state of University
	 * @param  co  cost of attending University
	 * @param  f   year the University was founded
	 * @param  p   undergraduate population of University
	 */	
	public University(String n, String c, String s, int co, int f, int p) 
	{
		name = n;
		city = c;
		state = s;
		costOfAttending = co;
		founded = f;
		undergradPop = p;
		
	}
	
	/** 
	 *  Compares the names of the Universities, returning a negative number if
	 *  the calling University is less than the University passed as an argument.  A
	 *  positive number is returned if the calling University is greater than
	 *  the University passed as an argument.
	 *
	 *  @param other      The University to be compared
	 *  @return           a negative integer, zero, or a positive integer as this University
     *                    is less than, equal to, or greater than the University to be compared
	 */
	public int compareTo(University other)
	{
		return (name.compareTo(other.name));
	}
	
	/** 
	 *  Compares the names of the Universities by undergraduate population, returning 
	 *  a negative number if the calling University is less than the University passed 
	 *  as an argument.  A positive number is returned if the calling University is 
	 *  greater than the University passed as an argument.
	 *
	 *  @param other      The University to be compared
	 *  @return           a negative integer, zero, or a positive integer as this University
     *                    is less than, equal to, or greater than the University to be compared
	 */
	public int compareToCost(University other)
	{
		return (costOfAttending-other.costOfAttending);
	}

	/**
	 *  Compares the University to the specified object
	 *
	 * @param  other      University object to compare to
	 * @return            true if equal, false otherwise
	 */
	public boolean equals(Object other)
	{
		if (other instanceof University)
		{
			if ((compareTo((University)other) == 0) && (compareToCost((University)other) == 0))
			{
				return true;
			}
		}
		return false;
	}
	
	/** 
	 *  Returns all of the information for this University as a String.
	 *
	 *  @return        The String representing the University
	 */
	public String toString() 
	{
		return String.format("  %-20s%-20s%-6s%10d%10d%10d",name,city,state,costOfAttending,founded,undergradPop);
	}
	
	public String getName()
	{
		return name;
	}
}
