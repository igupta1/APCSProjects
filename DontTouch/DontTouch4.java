/**
 * DontTouch.java
 *
 * The goal of this program is to try to randomly place 1000
 * circle of varying sizes in a 12 by 8 box using as much area as possible.
 * The placement of these circles are random
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 10/25/2019
 */

import java.awt.Color;
import java.awt.Font;

public class DontTouch4
{
	/**  The array for the Circles to be drawn.                         */
	private Circle [] circles;
	
	/**  Constructs the size (1000) for the array of Circles.           */
	public DontTouch4 ( )
	{
		circles = new Circle [1000]; 
	}
	
	public static void main(String [] args)
	{
		DontTouch4 run = new DontTouch4();
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
		
		for (int i = 0; i < 1000; i++)
		{
			StdDraw.setPenColor(new Color((int)(255 * Math.random()), (int)(255 * Math.random()), (int)(255 * Math.random()))); 
			
			circles[i] = new Circle(1.0);
			
			while (!(goodCoordinates(i)))
			{
				
			}
			
			circles[i] = new Circle(circles[i].getX(), circles[i].getY(), circles[i].getRadius());
			
			StdDraw.filledCircle(circles[i].getX(), circles[i].getY(), circles[i].getRadius());
			
		}
		StdDraw.show();
		
		
	}
	
	public boolean goodCoordinates (int currIndex)
	{
		double diffx;
		double diffy;
		
		double posx = Math.random()*(12)+(circles[currIndex].getRadius()-6);
		double posy = Math.random()*(8)+(circles[currIndex].getRadius()-4);
		
		for (int j = 0; j < currIndex; j++)
		{
			diffx = circles[j].getX() - posx;
			diffy = circles[j].getY() - posy;
			
			if ((Math.sqrt(diffx * diffx + diffy * diffy) <= circles[j].getRadius() + circles[currIndex].getRadius()) || ((posy + circles[currIndex].getRadius() >= 4) || (posy - circles[currIndex].getRadius() <= -4) || (posx + circles[currIndex].getRadius() >= 6) || (posx - circles[currIndex].getRadius() <= -6)))
			{
				circles[currIndex].setRadius(circles[currIndex].getRadius()*0.9999);
				circles[currIndex] = new Circle(posx, posy, circles[currIndex].getRadius());
				return false;
			}
		}
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
