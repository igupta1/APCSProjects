 /**
 * FollowingGecko.java
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 11/12/2019
 * 
 * The FollowingGecko class is a child of the DancingGecko class
 * this class is intended to follow a leader
 */

import java.awt.Color;
import java.awt.Font;

public class FollowingGecko extends DancingGecko
{
	
	public FollowingGecko (Leader newLead, Color newColor)
	{
		super(newLead.getLocation().getX(), newLead.getLocation().getY(), newColor);
		
		//this is the direction of the leader
		Point dir = newLead.getDirection();
		
		//this is the xpos of the leader
		int leadX = newLead.getLocation().getX();
		
		//this is the ypos of the leader
		int leadY = newLead.getLocation().getY();
		
		//THESE 4 CONDITIONS
		//check what way the leader is facing and based on that the follower
		//makes it own direction opposite so it can face the leader
		//In addition it changes its x or y depending on the direction so it
		//can travel directly in front of the leader
		
		if (dir.equals(GeckoConstants.NORTH))
		{
			setDirection(GeckoConstants.SOUTH);
			setY(leadY + 80);
		}
		
		else if (dir.equals(GeckoConstants.SOUTH))
		{
			setDirection(GeckoConstants.NORTH);
			setY(leadY - 80);
		}
		
		else if (dir.equals(GeckoConstants.EAST))
		{
			setDirection(GeckoConstants.WEST);
			setX(leadX - 80);
		}
		
		else if (dir.equals(GeckoConstants.WEST))
		{
			setDirection(GeckoConstants.EAST);
			setX(leadX + 80);
		}
		newLead.addFollower(this);
	}
	
	public FollowingGecko (Leader newLead, int x, int y, Color color, Point point)
	{
		super(x, y, color, point);
		newLead.addFollower(this);
	}
	
	public void step()
	{
	}
	
	//this method draws an "F" on the follower to indentify which one is
	//the follower
	public void draw()
	{
		super.draw();
		StdDraw.setPenColor(StdDraw.BLACK);
		Font font = new Font("Arial", Font.BOLD, 40);
		StdDraw.setFont(font);
		StdDraw.text(getX(), getY(), "F");
	}
}
