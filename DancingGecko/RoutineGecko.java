/**
 * RoutineGecko.java
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 11/12/2019
 * 
 * The RoutineGecko class is a child of the DancingGecko class
 * This class tells the gecko what step to take based on an array of dance steps
 * The gecko will take the step that it is currently on in the dance step array
 */

import java.awt.Color;

public class RoutineGecko extends DancingGecko
{
	//this is the count to see which step the gecko is on
	private int count;
	
	//this is the dance step array that has the dance moves of the gecko
	private GeckoConstants.DanceStep [] moves;
	
	//this is the current step the gecko is on
	private GeckoConstants.DanceStep curr;
	
	public RoutineGecko (int x, int y, Color color, GeckoConstants.DanceStep[] steps)
	{
		super (x,y,color);
		moves = steps;
		count = 0;
	}
	
	public RoutineGecko (int x, int y, Color color, Point point, GeckoConstants.DanceStep [] steps)
	{
		super (x,y,color,point);
		moves = steps;
		count = 0;
	}
	
	//this method finds what step the gecko is currently on and then
	//chooses what the gecko will do based on what the current step is
	
	public void step()
	{
		if (count == moves.length)
			count = 0;
			
		curr = moves[count];
		
		switch (curr)
		{
			case FORWARD:
				moveForward(64);
				break;
			case BACKWARD:
				moveForward(-64);
				break;
			case TURN_RIGHT:
				spinRight();
				break;
			case TURN_LEFT:
				spinLeft();
				break;
			case RIGHT:
				moveSide(64);
				break;
			case LEFT:
				moveSide(-64);
				break;
			case PAUSE:
				break;
		}
		count++;
	}
	
	
	//returns the current step the gecko is on
	public GeckoConstants.DanceStep getStep()
	{
		return curr;
	}
}
