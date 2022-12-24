/**
 * SideToSideGecko.java
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 11/12/2019
 * 
 * The SideToSideGecko class is a child of the DancingGecko class
 * This class tells the gecko to move right the first time and move left 
 * the second time whenever the step method is called.
 */

import java.awt.Color;

public class SideToSideGecko extends DancingGecko
{
	//this is count to see which way the gecko should move
	private int count;
	
	public SideToSideGecko (int x, int y, Color color)
	{
		super (x,y,color);
	}
	
	public SideToSideGecko (int x, int y, Color color, Point point)
	{
		super (x,y,color,point);
	}
	
	//this method uses a counter to check whether the gecko is on the 
	//first step or the second step and to decide whether to move
	//right or left.
	
	public void step()
	{
		if (count%2 == 0)
		{
			moveSide(64);
			count = 0;
		}

		else
			moveSide(-64);
		count++;
		
	}
}
