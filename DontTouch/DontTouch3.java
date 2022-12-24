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

public class DontTouch2
{
	/**  The array for the Circles to be drawn.                         */
	private Circle [] circles;
	private double [] sizes;
	//private double radius;
	private double [] posx;
	private double [] posy;
	private double diffx;
	private double diffy;
	private double xpos;
	private double ypos;
	private double size;
	
	/**  Constructs the size (1000) for the array of Circles.           */
	public DontTouch2 ( )
	{
		circles = new Circle [1000]; 
		sizes = new double [1000];
		posx = new double [1000];
		posy = new double [1000];
	}
	
	public static void main(String [] args)
	{
		DontTouch2 run = new DontTouch2();
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
		double x = 0;
		double y = 0;
		double r = 1;
		
		boolean made;
		
		
		
		for(int i = 0; i<circles.length;i++)
		{
			made = false;
			counter = 0;

			r = 1;
			
			while(!made)
			{
				counter++;
				made = true;
				
				x = Math.random()*12-6;
				y = Math.random()*8-4;
				
				for(int j=0; j<i; j++)
				{
					if(!(  Math.sqrt( (x-circles[j].getX())*(x-circles[j].getX()) + (y-circles[j].getY())*(y-circles[j].getY()) )   >   r + circles[j].getRadius()    ) || !(withinBounds(x,y,r)))
					{
						made = false;
						break;
					}
				}
				r*=0.9999;
			}
			circles[i] = new Circle(x,y,r);
		}
		
		
		for(int i = 0; i<circles.length;i++)
		{
			StdDraw.setPenColor(new Color((int)(255 * Math.random()), (int)(255 * Math.random()), (int)(255 * Math.random()))); 
			StdDraw.filledCircle(circles[i].getX(), circles[i].getY(), circles[i].getRadius());
		}
	}
	
	
	public boolean good()
	{
	
	
	public boolean withinBounds(double x, double y, double r)
	{
		if( (x+r>=6)   || (x-r<=-6)  || (y+r>=4)  || (y-r<=-4) ) 
			return false;
		return true;
	}
	
	public double findDist(double x, double y, double x2, double y2)
	{
		return Math.sqrt( (x-x2)*(x-x2) + (y-y2)*(y-y2) );
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
			area = area + (circles[i].getRadius());
		}
		
		System.out.println("\n\n\nTotal Area: " + area + "\n\n\n");
	}
}
