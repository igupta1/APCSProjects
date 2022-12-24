/**
 * DancingGecko.java
 * 
 * An abstract DancingGecko.  Contains the basic move methods for a 
 * DancingGecko.  This class is to be extended, so that the methods 
 * can be used.  Each DancingGecko has a position (x and y), a direction
 * (a Point), and a Color.  There are 2 constructors (3 args and 4 args).
 * A DancingGecko can spinRight, spinLeft, moveForward (and backward),
 * moveSide (left or right), and be drawn.  The abstract method step will
 * need to be defined by subclasses of the DancingGecko.
 *
 * @author Scott DeRuiter and Ishaan Gupta
 * @version 1.0
 * @since 11/12/2019
 */

import java.awt.Color;

public abstract class DancingGecko
{
	private Point direction;
	private int x;
	private int y;
	private Color color;

	/**
	 * Creates a new DancingGecko at the given location with the given color. DancingGecko defaults to facing SOUTH.
	 * @param x the DancingGecko's x location
	 * @param y the DancingGecko's y location
	 * @param color the DancingGecko's color
	 */
	public DancingGecko(int x, int y, Color color)
	{
		this.x = x;
		this.y = y;
		this.direction = GeckoConstants.SOUTH;
		this.color = color;
	}

	/**
	 * Creates a new DancingGecko at the given location, with the given color, facing in the given direction.
	 * @param x the DancingGecko's x location
	 * @param y the DancingGecko's y location
	 * @param color the DancingGecko's color
	 * @param direction the DancingGecko's direction
	 */
	public DancingGecko(int x, int y, Color color, Point p)
	{
		this.x = x;
		this.y = y;
		this.direction = p;
		this.color = color;
	}

	/**
	 * An abstract method (that must be overridden in a subclass) that causes the DancingGecko to perform a single dance step.
	 * The details of what that step is should be defined by the concrete child class.
	 */
	public abstract void step();

	/**
	 * A method that spins the DancingGecko 90 degrees to the right (clockwise) 
	 * @return whether or not the DancingGecko successfully turned.
	 */
	public boolean spinRight()
	{
		if(this.direction == GeckoConstants.NORTH)
		{
			this.direction = GeckoConstants.WEST;
		}
		else if(this.direction == GeckoConstants.EAST)
		{
			this.direction = GeckoConstants.NORTH;
		}
		else if(this.direction == GeckoConstants.SOUTH)
		{
			this.direction = GeckoConstants.EAST;
		}
		else if(this.direction == GeckoConstants.WEST)
		{
			this.direction = GeckoConstants.SOUTH;
		}

		return true;
	}

	/**
	 * A method that spins the DancingGecko 90 degrees to the left (counter-clockwise) 
	 * @return whether or not the DancingGecko successfully turned.
	 */
	public boolean spinLeft()
	{
		if(this.direction == GeckoConstants.NORTH)
		{
			this.direction = GeckoConstants.EAST;
		}
		else if(this.direction == GeckoConstants.EAST)
		{
			this.direction = GeckoConstants.SOUTH;
		}
		else if(this.direction == GeckoConstants.SOUTH)
		{
			this.direction = GeckoConstants.WEST;
		}
		else if(this.direction == GeckoConstants.WEST)
		{
			this.direction = GeckoConstants.NORTH;
		}

		return true;
	}

	/**
	 * Moves the DancingGecko along the line it is facing. Note that the DancingGecko can move backwards.
	 * The DancingGecko will only move if it stays within the bounds of the dance floor.
	 * @param dist the number of pixels the DancingGecko should move. A negative number causes the DancingGecko to step backwards.
	 * @return whether or not the DancingGecko successfully moved.
	 */
	public boolean moveForward(int dist)
	{
		int newX = x + dist * this.direction.getX();
		int newY = y + dist * this.direction.getY();
		
		if(newX >= 0 && newX <= GeckoConstants.SCREEN_WIDTH && newY >= 0 && newY <= GeckoConstants.SCREEN_HEIGHT)
		{
			x = newX;
			y = newY;
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Moves the DancingGecko sideways relative to its facing direction.  A positive parameter means it moves to the right, 
	 * a negative parameter means it moves to the left.
	 * The DancingGecko will only move if it stays within the bounds of the dance floor.
	 * @param dist the number of pixels the DancingGecko should move. A negative number causes the DancingGecko to move right, a negative to move left.
	 * @return whether or not the DancingGecko successfully moved.
	 */
	public boolean moveSide(int dist)
	{
		int newX = x + dist * this.direction.getY();
		int newY = y - dist * this.direction.getX();
		
		if(newX >= 0 && newX <= GeckoConstants.SCREEN_WIDTH && newY >= 0 && newY <= GeckoConstants.SCREEN_HEIGHT)
		{
			x = newX;
			y = newY;
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Draws the DancingGecko, with correct position, orientation (direction), and color.
	 */
	public void draw ( )
	{
		StdDraw.setPenColor(color); 
		StdDraw.filledCircle(x, y, 32);
		if(this.direction == GeckoConstants.NORTH)
		{
			StdDraw.picture(x, y, "gecko.png", 180);
		}
		else if(this.direction == GeckoConstants.WEST)
		{
			StdDraw.picture(x, y, "gecko.png", 90);
		}
		else if(this.direction == GeckoConstants.SOUTH)
		{
			StdDraw.picture(x, y, "gecko.png", 0);
		}
		else if(this.direction == GeckoConstants.EAST)
		{
			StdDraw.picture(x, y, "gecko.png", 270);
		}
	}
	
	
	//this method will return the location of the gecko
	public Point getLocation()
	{
		return new Point(x,y);
	}
	
	//this method will return the direction of the gecko
	public Point getDirection()
	{
		return direction;
	}
	
	//this method will set the direction of the gecko
	public void setDirection(Point dir)
	{
		direction = dir;
	}
	
	//this method will set the x value of the location of the gecko
	public void setX(int xpos)
	{
		x = xpos;
	}
	
	//this method will set the y value of the location of the gecko
	public void setY(int ypos)
	{
		y = ypos;
	}
	
	//this method will return the x value of the location of the gecko
	public int getX()
	{
		return x;
	}
	
	//this method will return the y value of the location of the gecko
	public int getY()
	{
		return y;
	}
}
