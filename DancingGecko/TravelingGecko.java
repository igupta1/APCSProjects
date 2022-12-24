/**
 * TravelingGecko.java
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 11/12/2019
 * 
 * The TravelingGecko class is a child of the DancingGecko class
 * this class is a leader so it implements leader
 * this gecko leads its partner/follower across the dance floor
 */

import java.awt.Color;
import java.awt.Font;

public class TravelingGecko extends DancingGecko implements Leader
{
	//this checks if the leader can move forward
	private boolean canMove;
	
	//this is the follower of this class
	private DancingGecko follower; 
	
	public TravelingGecko (int x, int y, Color color)
	{
		super (x,y,color);
		canMove = true;
	}
	
	public TravelingGecko (int x, int y, Color color, Point point)
	{
		super (x,y,color,point);
		canMove = true;
	}
	
	//this method returns the location of this gecko
	public Point getLocation()
	{
		return new Point (getX(), getY());
	}
	
	public Point getDirection()
	{
		return super.getDirection();
	}
	
	//this method adds a follower to this gecko
	public void addFollower(DancingGecko g)
	{
		follower = g;
	}
	
	
	//this method changes the direction if the leader cannot keep moving
	//in the same direction
	
	public void changeDirection()
	{
		if (canMove)
			canMove = false;
		else
			canMove = true;
	}
	
	
	//this method checks whether the leader and the follower
	//can continue moving in the same direction and then moves them if they can. 
	//If they cannot then it changes the direction. 
	public void step()
	{
		if (canMove)
		{
			if(follower.moveForward(-64))
			{
				moveForward(64);
			}
			else
			{
				changeDirection();
				step();
			}
		}
		else
		{
			if(moveForward(-64))
			{
				follower.moveForward(64);
			}
			else
			{
				changeDirection();
				step();
			}
		}
	}
	
	//this method removes the follower from this leader
	public void removeFollower(DancingGecko g)
	{
		follower = null;
	}
	
	//this method draws an "L" on the follower to indentify which one is
	//the leader
	public void draw()
	{
		super.draw();
		StdDraw.setPenColor(StdDraw.BLACK);
		Font font = new Font("Arial", Font.BOLD, 40);
		StdDraw.setFont(font);
		StdDraw.text(getX(), getY(), "L");
	}
}
