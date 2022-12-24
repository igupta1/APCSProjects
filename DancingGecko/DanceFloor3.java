
/**
 * DanceFloor3.java
 * 
 * A "dance floor", where we can test the different types of
 * DancingGeckos that have been created.  The dance floor uses the
 * dimensions found in the GeckoConstants file, to set up a 
 * screen width and height.  An array of 50 DancingGeckos is
 * created, and the Geckos are instantiated and added to this array.
 * An endless loop is created, so that the Geckos can dance without
 * ceasing.
 *
 * @author Scott DeRuiter
 * @version 1.0
 * @since 11/15/2019
 */

import java.awt.Color;
import java.awt.Font;

public class DanceFloor3
{ 
	/**  An array that stores all of the DancingGecko variants.               */
	DancingGecko [] gecko;
	
	/** 
	 *  Creates a DanceFloor object.  A no-args constructor.  
	 *  Sets up the size of the DancingGecko array.
	 */
	public DanceFloor3 ( )
	{
		gecko = new DancingGecko[50];
	}

	/**
	 *  The main method, to create an instance of the DanceFloor.  The canvas
	 *  is set up, and the GUI runs.
	 */
	public static void main(String[] args)
	{
		DanceFloor3 dance = new DanceFloor3();
		dance.setUpCanvas();
		dance.runGUI();
	}
	
	/** 
	 *  Sets up the canvas, using methods from StdDraw.  This includes
	 *  setting up the canvas size, the horizontal scale (Xscale), and
	 *  the vertical scale (Yscale).  Double buffering is enabled, so that
	 *  the animation will run smoothly.
	 */
	public void setUpCanvas ( )
	{
		StdDraw.setCanvasSize(GeckoConstants.SCREEN_WIDTH, GeckoConstants.SCREEN_HEIGHT);
		StdDraw.setXscale(0, GeckoConstants.SCREEN_WIDTH);
		StdDraw.setYscale(0, GeckoConstants.SCREEN_HEIGHT);

		StdDraw.enableDoubleBuffering();
	}
	
	/** 
	 *  Runs the Graphical User Interface.  Geckos are added, and the loop runs without
	 *  stopping.  With each iteration of the loop the Geckos are drawn, and then
	 *  the next dance step is taken by all Geckos on the DanceFloor.
	 */
	public void runGUI ( )
	{
		boolean partyOn = true;
		int counter = 0;
		int rememberThisOne = -1;
		addGeckos();
		while(partyOn)
		{
			StdDraw.clear(new Color(0,0,95));
			drawGeckos();
			StdDraw.show();
			StdDraw.pause(GeckoConstants.DRAW_DELAY);
			allGeckosTakeNextDanceStep();
			counter++;
			if(counter == 17)
			{
				counter = 0;
				if(rememberThisOne != -1)
				{
					((RoutineLeaderGecko)gecko[4]).addFollower(gecko[rememberThisOne]);
				}
				rememberThisOne = (int)(Math.random() * (18 - 5 + 1) + 5);
				((RoutineLeaderGecko)gecko[4]).removeFollower(gecko[rememberThisOne]);
			}
		}
	}
	
	/** 
	 *  Adds DancingGeckos to the array.
	 */
	public void addGeckos ( )
	{
		gecko[0] = new TravelingGecko(40, 40, Color.RED, GeckoConstants.WEST);
		gecko[1] = new FollowingGecko((Leader)gecko[0], Color.WHITE);
		gecko[2] = new TravelingGecko(1160, 760, Color.RED, GeckoConstants.EAST);
		gecko[3] = new FollowingGecko((Leader)gecko[2], Color.WHITE);
		gecko[4] = new RoutineLeaderGecko(600, 400, Color.RED, GeckoConstants.NORTH, new GeckoConstants.DanceStep[]{GeckoConstants.DanceStep.LEFT, GeckoConstants.DanceStep.FORWARD, 
			GeckoConstants.DanceStep.TURN_RIGHT, GeckoConstants.DanceStep.FORWARD, GeckoConstants.DanceStep.TURN_LEFT, GeckoConstants.DanceStep.TURN_LEFT, GeckoConstants.DanceStep.PAUSE,
			GeckoConstants.DanceStep.BACKWARD, GeckoConstants.DanceStep.TURN_RIGHT, GeckoConstants.DanceStep.BACKWARD, GeckoConstants.DanceStep.BACKWARD, GeckoConstants.DanceStep.LEFT,
			GeckoConstants.DanceStep.LEFT, GeckoConstants.DanceStep.FORWARD, GeckoConstants.DanceStep.RIGHT, GeckoConstants.DanceStep.PAUSE, GeckoConstants.DanceStep.PAUSE});
		gecko[5] = new FollowingGecko((Leader)gecko[4], 600, 560, Color.WHITE, GeckoConstants.NORTH);
		gecko[6] = new FollowingGecko((Leader)gecko[4], 760, 560, Color.WHITE, GeckoConstants.NORTH);
		gecko[7] = new FollowingGecko((Leader)gecko[4], 760, 400, Color.WHITE, GeckoConstants.NORTH);
		gecko[8] = new FollowingGecko((Leader)gecko[4], 760, 240, Color.WHITE, GeckoConstants.NORTH);
		gecko[9] = new FollowingGecko((Leader)gecko[4], 600, 240, Color.WHITE, GeckoConstants.NORTH);
		gecko[10] = new FollowingGecko((Leader)gecko[4], 440, 240, Color.WHITE, GeckoConstants.NORTH);
		gecko[11] = new FollowingGecko((Leader)gecko[4], 440, 400, Color.WHITE, GeckoConstants.NORTH);
		gecko[12] = new FollowingGecko((Leader)gecko[4], 440, 560, Color.WHITE, GeckoConstants.NORTH);
		gecko[13] = new FollowingGecko((Leader)gecko[4], 920, 240, Color.WHITE, GeckoConstants.NORTH);
		gecko[14] = new FollowingGecko((Leader)gecko[4], 920, 400, Color.WHITE, GeckoConstants.NORTH);
		gecko[15] = new FollowingGecko((Leader)gecko[4], 920, 560, Color.WHITE, GeckoConstants.NORTH);
		gecko[16] = new FollowingGecko((Leader)gecko[4], 280, 240, Color.WHITE, GeckoConstants.NORTH);
		gecko[17] = new FollowingGecko((Leader)gecko[4], 280, 400, Color.WHITE, GeckoConstants.NORTH);
		gecko[18] = new FollowingGecko((Leader)gecko[4], 280, 560, Color.WHITE, GeckoConstants.NORTH);
	}
	
	/** 
	 *  Draws DancingGeckos on the DanceFloor.
	 */
	public void drawGeckos ( )
	{			
		for(int i = 0; i < gecko.length; i++)
		{
			if(gecko[i] != null)
			{
				gecko[i].draw();
			}
		}
	}
	
	/** 
	 *  All of the DancingGeckos in the array take the next dance step.
	 */
	public void allGeckosTakeNextDanceStep ( )
	{			
		for(int i = 0; i < gecko.length; i++)
		{
			if(gecko[i] != null)
			{
				gecko[i].step();
			}
		}
	}
}
