/**
 * DontTouch.java
 *
 * The goal of this program is to try to randomly place 1000
 * circles of varying sizes in a 12 by 8 rectangle using as much area as possible.
 * The placement of these circles are random and none of the circles can
 * overlap or spill out of the rectangle
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 10/25/2019
 */

import java.awt.Color;
import java.awt.Font;

public class DontTouch
{
	/**  The array for the Circles to be drawn.                         */
	private Circle [] circles;
	
	/**  Constructs the size (1000) for the array of Circles.           */
	public DontTouch ( )
	{
		circles = new Circle [1000]; 
	}
	
	public static void main(String [] args)
	{
		DontTouch run = new DontTouch();
		run.setUpCanvas();
		run.drawCircles();
		run.drawAxes();
		run.printArea();
	}
	
	/** 
	 *  Sets up the canvas, using methods from StdDraw.  This includes
	 *  setting up the canvas size, the horizontal scale (Xscale), and
	 *  the vertical scale (Yscale).  
	 */
	public void setUpCanvas ( )
	{
		final int WIDTH = 1200;
		final int HEIGHT = 800;
		StdDraw.setCanvasSize(WIDTH, HEIGHT);
		StdDraw.setXscale(-6, 6);
		StdDraw.setYscale(-4, 4);
		StdDraw.clear(new Color(255,255,255));
		
		StdDraw.enableDoubleBuffering();
	}
	
	/**
	 *  Creates the Circles in the array of Circles.  Draws the Circles.
	 */
	public void drawCircles ( )
	{	
		//repeats a 1000 times for each circle
		for (int i = 0; i < 1000; i++)
		{
			//chooses a random color
			StdDraw.setPenColor(new Color((int)(255 * Math.random()), (int)(255 * Math.random()), (int)(255 * Math.random()))); 
			
			//sets the initial size of the circle to 1 because that is 
			//the largest size of the circle allowed
			circles[i] = new Circle(1.0);
			
			//calls this method until the circle can be placed
			//description about what canPlace() does in the method
			while (!(canPlace(i)))
			{
				
			}
			
			StdDraw.filledCircle(circles[i].getX(), circles[i].getY(), circles[i].getRadius());
			
		}
		StdDraw.show();
		
		
	}
	
	//the paramenter for this method is the current circle that is trying to be placed
	//this would be an integer from 1-1000 which is the index in the circle array
	public boolean canPlace (int currIndex)
	{		
		//gets a random x and y value for circle
		
		//FOR IT TO NOT EXTEND PAST THE BORDER, the range of values has to
		//be decreased by radius on both the positive and negative side.
		//This goes for the negative and positive side of both the x axis and y axis
		//In addition, the minimum value is the minimum value for the x or y axis plus the radius
		//because the radius cannot extend past the border
		
		double posx = Math.random()*(12 - 2*circles[currIndex].getRadius())+(circles[currIndex].getRadius()-6);
		double posy = Math.random()*(8-2*circles[currIndex].getRadius())+(circles[currIndex].getRadius()-4);
		
		//Goes through all other circle objects before the current circle
		for (int j = 0; j < currIndex; j++)
		{
			//check for overlap between the circle USING THE DISTANCE FORMULA and
			//checking if the distance is less than or equal to the sum of the
			//radiuses of the two circles
			if (Math.sqrt((circles[j].getX() - posx) * (circles[j].getX() - posx) + (circles[j].getY() - posy) * (circles[j].getY() - posy)) <= circles[j].getRadius() + circles[currIndex].getRadius())
			{
				//if the circles overlap, the SIZE IS DECREASED
				//the size is decreased using multiplication to ensure that the
				//radius stays positive because substracting a constant can lead to
				//negatives when the circle gets quite small;
				circles[currIndex].setRadius(circles[currIndex].getRadius()*0.99995);
				return false;
			}
		}
		//if there is no overlap, a new circle object is created with the 
		//found x and y position and the current radius.
		circles[currIndex] = new Circle(posx, posy, circles[currIndex].getRadius());
		return true;
	}
	
	
	
	
	/**
	 *  Draws a pair of axes, over the drawn Circles.  Grid lines are drawn and
	 *  the scale is shown, to help the viewer see the size of the Circles.
	 */
	public void drawAxes ( )
	{
		Font font = new Font("Arial", Font.PLAIN, 18);
		StdDraw.setFont(font);
		StdDraw.setPenColor(new Color(220,220,220)); 
		for(double integers = -6; integers <= 6; integers++)
		{
			StdDraw.line(integers,-4,integers,4);
			StdDraw.line(-6,integers,6,integers);
			StdDraw.setPenColor(new Color(0,0,0)); 	
			StdDraw.text(integers,-0.4,"" + (int)integers);
			StdDraw.text(-0.3,integers-0.05,"" + (int)integers);
		}
		StdDraw.show();
	}

	/**
	 *  Adds the area of each circle to a total area.  Prints this total 
	 *  area to the terminal window.
	 * 
	 * goes through every circle and gets the area keeping track of the total area
	 */
	public void printArea ( )
	{	
		double area = 0.0;
		
		for (int i = 0; i < 1000; i++)
		{
			area = area + circles[i].getArea();
		}
		
		System.out.println("\n\n\nTotal Area: " + area + "\n\n\n");
	}
}
