import java.awt.Color;

public class Triangle
{
	public static void main(String [] args)
	{
		int temp = 60;
		int count = 0;
		if(args.length > 0)
		{
			temp = Integer.parseInt(args[0]);
		}
		Triangle recursion = new Triangle();
		recursion.draw(count, temp);
	}
	
	public void draw(int count, int n)
	{
		StdDraw.setCanvasSize(700,700);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.filledSquare(0.5,0.5,0.5);
		StdDraw.show();
		
		double x1 = 0.1, y1 = 0, x2 = 1, y2 = 0.1, x3 = 0.9, y3 = 1, x4 = 0, y4 = 0.9;
		sierpinski(count, n, x1, y1, x2, y2, x3, y3, x4, y4);
	}
	
	public void sierpinski(int count, int n, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4)
	{
		if(n == 0)
		{
			return;
		}
		
		double [] x = {x1,x2,x3,x4};
		double [] y = {y1,y2,y3,y4};
		
		if (count%7 == 0)
		{
			StdDraw.setPenColor(Color.RED);
		}	
		else if (count%7 == 1)
		{
			StdDraw.setPenColor(Color.ORANGE);
		}
		else if (count%7 == 2)
		{
			StdDraw.setPenColor(Color.YELLOW);
		}
		else if (count%7 == 3)
		{
			StdDraw.setPenColor(Color.GREEN);
		}
		else if (count%7 == 4)
		{
			StdDraw.setPenColor(Color.BLUE);
		}
		else if (count%7 == 5)
		{
			StdDraw.setPenColor(Color.MAGENTA);
		}
		else if (count%7 == 6)
		{
			StdDraw.setPenColor(Color.PINK);
		}
		
		
		//StdDraw.setPenColor(new Color(0, (int)(Math.random()*200), (int)(Math.random()*200), (int)(Math.random()*200)));
		StdDraw.filledPolygon(x,y);
		StdDraw.pause(50);
		StdDraw.show();
		count++;
		
		
		sierpinski(count, n-1,   (x2-x1)/10.0 + x1,   (y2-y1)/10.0 + y1,   (x3-x2)/10.0 + x2,   (y3-y2)/10.0 + y2,   (x4-x3)/10.0 + x3,   (y4-y3)/10.0 + y3,   (x1-x4)/10.0 + x4,   (y1-y4)/10.0 + y4);
		
	}
	
}
