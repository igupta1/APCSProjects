
/**
 * DanceFloor.java
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
 * @since 11/12/2019
 */

import java.awt.Color;
import java.awt.Font;

public class DanceFloor 
{ 
	/**  An array that stores all of the DancingGecko variants.               */
	DancingGecko [] gecko;
	
	/** 
	 *  Creates a DanceFloor object.  A no-args constructor.  
	 *  Sets up the size of the DancingGecko array.
	 */
	public DanceFloor ( )
	{
		gecko = new DancingGecko[50];
	}

	/**
	 *  The main method, to create an instance of the DanceFloor.  The canvas
	 *  is set up, and the GUI runs.
	 */
	public static void main(String[] args)
	{
		DanceFloor dance = new DanceFloor();
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
		addGeckos();
		while(partyOn)
		{
			StdDraw.clear(new Color(0,0,95));
			drawGeckos();
			StdDraw.show();
			StdDraw.pause(GeckoConstants.DRAW_DELAY);
			allGeckosTakeNextDanceStep();
		}
	}
	
	/** 
	 *  Adds DancingGeckos to the array.
	 */
	public void addGeckos ( )
	{
		gecko[0] = new TwirlingGecko(920, 760, Color.BLUE);
		gecko[1] = new TwirlingGecko(1000, 760, Color.GREEN, GeckoConstants.WEST);
    	gecko[2] = new TwirlingGecko(1080, 760, Color.GRAY, GeckoConstants.NORTH);
		gecko[3] = new TwirlingGecko(1160, 760, Color.PINK, GeckoConstants.EAST);
		gecko[4] = new ForwardAndBackGecko(840, 760, Color.CYAN);
		gecko[5] = new ForwardAndBackGecko(760, 760, Color.ORANGE, GeckoConstants.EAST);
		gecko[6] = new SideToSideGecko(680, 760, Color.MAGENTA);
		gecko[7] = new SideToSideGecko(520, 760, Color.WHITE, GeckoConstants.WEST);
		gecko[8] = new RoutineGecko(1160, 40, Color.RED, GeckoConstants.WEST, new GeckoConstants.DanceStep[]{GeckoConstants.DanceStep.LEFT, GeckoConstants.DanceStep.TURN_LEFT, 
			GeckoConstants.DanceStep.LEFT, GeckoConstants.DanceStep.BACKWARD, GeckoConstants.DanceStep.TURN_RIGHT, GeckoConstants.DanceStep.FORWARD, GeckoConstants.DanceStep.PAUSE});
		gecko[9] = new RoutineGecko(1000, 40, Color.YELLOW, GeckoConstants.NORTH, new GeckoConstants.DanceStep[]{GeckoConstants.DanceStep.FORWARD, GeckoConstants.DanceStep.TURN_LEFT, 
			GeckoConstants.DanceStep.FORWARD, GeckoConstants.DanceStep.RIGHT, GeckoConstants.DanceStep.TURN_RIGHT, GeckoConstants.DanceStep.LEFT, GeckoConstants.DanceStep.BACKWARD, 
			GeckoConstants.DanceStep.BACKWARD, GeckoConstants.DanceStep.RIGHT, GeckoConstants.DanceStep.PAUSE, GeckoConstants.DanceStep.RIGHT, GeckoConstants.DanceStep.PAUSE, GeckoConstants.DanceStep.PAUSE});
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
