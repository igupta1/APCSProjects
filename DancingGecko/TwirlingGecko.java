/**
 * TwirlingGecko.java
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 11/12/2019
 * 
 * The TwirlingGecko class is a child of the DancingGecko class
 * This class tells the gecko to spin right whenever the step method is called
 */

import java.awt.Color;

public class TwirlingGecko extends DancingGecko
{
	public TwirlingGecko (int x, int y, Color color)
	{
		super (x,y,color);
	}
	
	public TwirlingGecko (int x, int y, Color color, Point point)
	{
		super (x,y,color,point);
	}
	
	public void step()
	{
		spinRight();
	}
}
