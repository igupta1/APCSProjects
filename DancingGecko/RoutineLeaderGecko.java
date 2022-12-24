/**
 * RoutineLeaderGecko.java
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 11/12/2019
 * 
 * This class is a child of the RoutineGecko class
 * this class has an array of followers which follows the steps of this leader
 * Similar to the RoutineGecko class, this class gets it order of dance moves
 * from an array of dance steps
 */

import java.awt.Color;
import java.awt.Font;

public class RoutineLeaderGecko extends RoutineGecko implements Leader
{
	
	//this is the array of followers that follow the routine leader
	//it is of size 20 because there are a maximum of 20 geckos
	private DancingGecko [] followers = new DancingGecko[20];
	
	public RoutineLeaderGecko (int x, int y, Color color, GeckoConstants.DanceStep [] steps)
	{
		super (x,y,color,steps);
	}
	
	public RoutineLeaderGecko (int x, int y, Color color, Point point, GeckoConstants.DanceStep [] steps)
	{
		super (x,y,color,point,steps);
	}
	
	//this method goes through the array of followers and adds the gecko
	//in the first space that is null
	
	public void addFollower(DancingGecko dg)
	{
		for (int i = 0; i < 20; i++)
		{
			if (followers[i] == null)
			{
				followers[i] = dg;
				i = 20;
			}
		}
	}
	
	//this method goes through the array of followers and removes the gecko
	//that is the same as the intended gecko to remove
	
	public void removeFollower(DancingGecko dg)
	{
		for (int i = 0; i < 20; i++)
		{
			if (followers[i] == dg)
			{
				followers[i] = null;
				i = 20;
			}
		}
	}
	
	
	//this method calls the step method from RoutineGecko and then gets
	//the current step from RoutineGecko and based on that decides how 
	//it should move itself and all its followers
	
	public void step()
	{
		super.step();
		
		for (DancingGecko temp : followers)
		{
			if (temp != null)
			{
				GeckoConstants.DanceStep step = getStep();
				
				if (step == GeckoConstants.DanceStep.FORWARD)
				{
					temp.moveForward(64);
				}
				
				else if (step == GeckoConstants.DanceStep.BACKWARD)
				{
					temp.moveForward(-64);
				}
				
				else if (step == GeckoConstants.DanceStep.RIGHT)
				{
					temp.moveSide(64);
				}
				
				else if (step == GeckoConstants.DanceStep.LEFT)
				{
					temp.moveSide(-64);
				}
				
				else if (step == GeckoConstants.DanceStep.TURN_RIGHT)
				{
					temp.spinRight();
				}
				
				else if (step == GeckoConstants.DanceStep.TURN_LEFT)
				{
					temp.spinLeft();
				}
			}
		}
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
