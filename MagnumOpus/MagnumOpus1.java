//Ishaan Gupta
//This program uses recursion to draw a sierpinski carpet
//A sierpinski carpet is a collection of squares with 8 squares around the
//original square
//Because there are 8 squares there are 8 recursive calls
//The user can enter how many times they want the recursive algorithm to run
//1-16-20
import java.awt.Color;

public class MagnumOpus
{
	public static void main (String [] args)
	{
		int temp = 3;
		if (args.length > 0)
		{
			temp = Integer.parseInt(args[0]);
		}
		
		MagnumOpus recursion = new MagnumOpus();
		recursion.draw(temp);
	}
	
	public void draw (int n)
	{
		StdDraw.setCanvasSize(800,800);
		StdDraw.setPenColor(Color.BLACK);
		
		double [] x = {0.6, 1.0, 0.0};
		double [] y = {0.1, 0.4, 0.9};
		
		StdDraw.filledPolygon(x,y);
		
		double x1 = 0.6, y1 = 0.1, x2 = 0.8, y2 = 0.5;
		
		sierpinski(n, x1, y1, x2, y2);
	}	
	
	public void sierpinski(int n, double x1, double y1, double x2, double y2)
	{
		if (n == 0)
		{
			return;
		}
		
		drawLine(x1, y1, x2, y2);
		sierpinski(n-1, x2, y2, y);
		//~ sierpinski(n-1, length/3.0, xc - length, yc);
		//~ sierpinski(n-1, length/3.0, xc - length, yc - length);
		//~ sierpinski(n-1, length/3.0, xc, yc - length);
		//~ sierpinski(n-1, length/3.0, xc + length, yc - length);
		//~ sierpinski(n-1, length/3.0, xc + length, yc);
		//~ sierpinski(n-1, length/3.0, xc + length, yc + length);
		//~ sierpinski(n-1, length/3.0, xc, yc + length);	
	}
	
	public void drawLine(double x1, double y1, double x2, double y2)
	{
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.line(x1,y1,x2,y2);
	}
}
