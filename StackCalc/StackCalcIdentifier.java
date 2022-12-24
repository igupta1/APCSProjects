/**
 *	This class stores all the identifiers and is a helper class of StackCalcEC
 * 
 *	@author	Ishaan Gupta
 *	@since	3-3-20
 */

public class StackCalcIdentifier
{
	private String name;
	private double value;
	
	public StackCalcIdentifier(String name1, double value1)
	{
		name = name1;
		value = value1;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getValue()
	{
		return value;
	}
	
	public void setValue(double value2)
	{
		value = value2;
	}
	
	public void printValue()
	{
		System.out.printf("  %s = %.2f\n", name, value);
	}
}
