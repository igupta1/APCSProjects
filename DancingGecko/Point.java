
/**
 * Point.java
 * 
 * A simple Point class, with x and y values, to indicate
 * the direction of the DancingGecko.
 *
 * @author Scott DeRuiter
 * @version 1.0
 * @since 11/12/2019
 */

public class Point
{
	private int x, y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX ( )
	{
		return x;
	}
	
	public int getY ( )
	{
		return y;
	}
}
