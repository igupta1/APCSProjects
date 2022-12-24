/**
 * ForwardAndBackGecko.java
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 11/12/2019
 * 
 * The ForwardAndBackGecko class is a child of the DancingGecko class
 * This class tells the gecko to move forward the first time and move backward 
 * the second time whenever the step method is called.
 */

import java.awt.Color;

public class ForwardAndBackGecko extends DancingGecko
{
	//this is count to see which way the gecko should move
	private int count;
	
	public ForwardAndBackGecko (int x, int y, Color color)
	{
		super (x,y,color);
	}
	
	public ForwardAndBackGecko (int x, int y, Color color, Point point)
	{
		super (x,y,color,point);
	}
	
	//this method uses a counter to check whether the gecko is on the 
	//first step or the second step and to decide whether to move
	//forward or backward.
	
	public void step()
	{
		if (count%2 == 0)
		{
			moveForward(64);
			count = 0;
		}

		else
			moveForward(-64);
		count++;
		
	}
}
