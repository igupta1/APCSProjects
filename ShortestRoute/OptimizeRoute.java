/**
 * OptimizeRoute.java
 *
 * This program is a tester class for the extra credit heuristic which
 * is to optimize the route and make the route length shorter for the
 * points in the file p1000.txt.
 * 
 * @author Ishaan Gupta
 * @version 1.0
 * @since 3/22/2019
 */


import java.util.Scanner;
import java.awt.Font;
import java.awt.Color;

public class OptimizeRoute
{
	private int HEIGHT, WIDTH, BORDER;
	private String file;
	private ShortestRoute route;
	
	public OptimizeRoute(String fileName)
	{
		file = new String(fileName);
		route = new ShortestRoute();
	}
	public static void main(String[] args) 
	{
		// get dimensions, set up canvas and drawing
		String f = new String("p1000.txt");
		//takes in a command line argument for the file name
		if(args.length > 0)
		{
			f = args[0];
		}
		OptimizeRoute run = new OptimizeRoute(f);
		run.getFileInputAndCreateLinkedList();
		run.showFinalRouteAndInfo();
	}
	
	//this method goes through the file and gets the x and y value and makes
	//them a point. Then it passes the point to the optimize method which
	//creates a route using a single linked list.
	public void getFileInputAndCreateLinkedList ( )
	{
		Scanner inFile = OpenFile.openToRead(file);
		WIDTH = inFile.nextInt();
		HEIGHT = inFile.nextInt();
		BORDER = 20;
		StdDraw.setCanvasSize(WIDTH, HEIGHT + BORDER);
		StdDraw.setXscale(0, WIDTH);
		StdDraw.setYscale(-BORDER, HEIGHT);
		Font font = new Font("Arial", Font.BOLD, 18);
		StdDraw.setFont(font);

		// turn on double buffering
		StdDraw.enableDoubleBuffering();
		System.out.println("\n\n");

		// run smallest insertion heuristic
		while(inFile.hasNext())
		{
			double x = inFile.nextDouble();
			double y = inFile.nextDouble();
			Point p = new Point(x, y);
			route.insertPointAtOptimizedRoute(p);
		}
	}
	
	//this method prints the final route and calls the method that draws the
	//route using StdDraw
	public void showFinalRouteAndInfo ( )
	{
		// draw to standard draw 
		route.draw();
		//route.drawFinal();
		Font font = new Font("Arial", Font.BOLD, 18);
		StdDraw.setFont(font);
		StdDraw.textLeft(20, 0, "length = " + route.length());
		StdDraw.show();
        
		// print tour to standard output
		System.out.println("\n\n\nROUTE IN ORDER OF POINTS VISITED: \n\n" + route);
		System.out.printf("Route length = %.4f\n", route.length());
		System.out.printf("Number of points = %d\n", route.size());
		System.out.println("\n\n\n");
	}
}
