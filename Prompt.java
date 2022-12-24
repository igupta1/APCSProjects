import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 *	Prompt.java - Uses BufferedReader.
 *	Provides utilities for user input.  This enhances the BufferedReader
 *	class so our programs can recover from "bad" input, and also provides
 *	a way to limit numerical input to a range of values.
 *
 *	The advantages of BufferedReader are speed, synchronization, and piping
 *	data in Linux.
 *
 *	@author	Ishaan Gupta
 *	@since	8-27-19
 */


public class Prompt
{
	/**  Variables for reading in the information from the keyboard.   */
	private static InputStreamReader streamReader = new InputStreamReader(System.in);
	private static BufferedReader bufReader = new BufferedReader(streamReader);
	
	
	/**
	 *	Prompts user for string of characters and returns the string.
	 *	@param ask  The prompt line
	 *	@return  	The string input
	 */
	public static String getString (String ask)
	{
		String input = "";
		System.out.print(ask + ": ");
		try
		{
			input = bufReader.readLine();
		}
		catch(IOException e)
		{
			System.err.println("Error: Improper text input.");
		}
		return input;
	}
	
	/**
	 *  Prompts the user and picks up an int.  Checks for
	 *  "bad" input and reprompts if not an int.
	 *  @param ask       The String prompt to be displayed to the user.
	 *  @return          The int entered by the user.
	 */
	public static int getInt (String ask)
	{
		int value = 0;
		boolean badInput = false;
		String input = new String("");
		
		do
		{
			badInput = false;
			input = getString(ask);
			try
			{
				value = Integer.parseInt(input);
			}
			catch(NumberFormatException e)
			{
				badInput = true;
			}
		}
		while(badInput);
		
		return value;
	}
	
	/**
	 *  Prompts the user and picks up an int.  Checks for
	 *  "bad" input and reprompts if not an int.  Also checks
	 *  for input within a given range, and reprompts if outside
	 *  that range.
	 *  @param ask       The String prompt to be displayed to the user.
	 *  @param min       The minimum integer value to be allowed as input.
	 *  @param max       The maximum integer value to be allowed as input.
	 *  @return          The int entered by the user.
	 */
	public static int getInt (String ask, int min, int max)
	{
		int value = 0;
		do
		{
			value = getInt(ask + " (from " + min + " to " + max + ") ");
		}
		while(value < min || value > max);
		return value;
	}
	
	/**
	 *	Prompts the user for a character and returns the character.
	 *	@param ask  The prompt line
	 *	@return  	The character input
	 */
	public static char getChar (String ask)
	{
		String input = "";
		
		char first = ' ';
		
		
		do
		{
			System.out.print(ask + ": ");
			try
			{
				input = bufReader.readLine();
			}
			catch(IOException e)
			{
				System.err.println("Error: Improper text input.");
			}
		}
		while (input.length() != 1);
		first = input.charAt(0);
		return first;
	}
	
	/**
	 *	Prompts the user for a double and returns the double.
	 *	@param ask  The prompt line
	 *	@return  The double input
	 */
	public static double getDouble (String ask)
	{
		double value = 0.0;
		boolean badInput = false;
		String input = new String("");
		
		do
		{
			badInput = false;
			input = getString(ask);
			try
			{
				value = Double.parseDouble(input);
			}
			catch(NumberFormatException e)
			{
				badInput = true;
			}
		}
		while(badInput);
		
		return value;
	}
	
	/**
	 *	Prompts the user for a double and returns the double.
	 *	@param ask  The prompt line
	 *	@param min  The minimum double accepted
	 *	@param max  The maximum double accepted
	 *	@return  The double input
	 */
	public static double getDouble (String ask, double min, double max)
	{
		double value = 0.0;
		String formatMinMax = String.format("(from %.2f to %.2f)",min,max);
		do
		{
			value = getDouble(ask + formatMinMax);
		}
		while(value < min || value > max);
		return value;
	}
	
	//gets a character from the user that is in a given array
	public static char getChar (String ask, char [] arr)
	{
		char value = ' ';
		do
		{
			value = getChar(ask);
		}
		while(find(arr,value) == false);
		return value;
	}

	//this method looks for the uder input in the given max
	public static boolean find(char [] arr, char value)
	{
		for (int i = 0; i < arr.length; i++)
		{
			if (value == arr[i])
			{
				return true;
			}
		}
		return false;
	}
}
