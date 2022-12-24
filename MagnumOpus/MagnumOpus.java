/**
 *  MagnumOpus.java
 *  @author Ishaan Gupta
 *  @version 1.0
 *  @since 1/24/2020
 * 
 * This program creates a pythagoras tree using recursion. It draws squares
 * and 45-45-90 triangles that keep getting smaller as the tree grows.
 * This program also uses rainbow colors from left to right to give the 
 *  tree an artistic look.
 */


import java.awt.Color;

public class MagnumOpus
{
	public static void main (String [] args)
	{
		int temp = 14;
		if (args.length > 0)
		{
			temp = Integer.parseInt(args[0]);
		}
		
		MagnumOpus recursion = new MagnumOpus();
		recursion.draw(temp);
	}
	
	public void draw (int n)
	{
		StdDraw.setCanvasSize(900,900);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.filledRectangle(0.5,0.5,0.5,0.5);
		drawTree(n, 0.45, 0.625, 0.57, 0.625);
	}	
	
	//this method is a recursive method that calls itself
	//n is how many levels of recursion are left
	//x1,y1,x2,y2 are the points at the bottom of the square.
	
	private void drawTree(int n, double x1, double y1, double x2, double y2) 
	{
 
        if (n == 0)
        {
            return;
        }
 
        double dx = x2 - x1;
        double dy = y1 - y2;
        
 
        double x3 = x2 - dy;
        double y3 = y2 - dx;
        
        double x4 = x1 - dy;
        double y4 = y1 - dx;
        
        double x5 = x4 + 0.5 * (dx - dy);
        double y5 = y4 - 0.5 * (dx + dy);
        
        double [] xval1 = {x1,x2,x3,x4};
        double [] yval1 = {1-y1,1-y2,1-y3,1-y4};
        double [] xval2 = {x3,x4,x5};
        double [] yval2 = {1-y3,1-y4,1-y5};
        
        double use1 = (x1+x2+x3+x4)/4.0;
        double use2 = (x3+x4+x5)/3.0;
        
        
        if (use1 <= 0.2)
        {
			StdDraw.setPenColor(new Color((float)(use1/0.2), 0.0F, 0.0F));
		}
		
		else if (use1 <= 0.5)
        {
			StdDraw.setPenColor(new Color(1.0F, (float)((use1-0.2)/0.3), 0.0F));
		}
		
		else if (use1 <= 0.6)
        {
			
			StdDraw.setPenColor(new Color(0.0F, 1-(float)((use1-0.5)/0.25), 0.0F));
		}
		
		else if (use1 <= 0.61)
        {
			StdDraw.setPenColor(new Color(0.0F, 0.7F, 0.3F));
		}
		else if (use1 <= 0.62)
        {
			StdDraw.setPenColor(new Color(0.0F, 0.6F, 0.4F));
		}
		else if (use1 <= 0.63)
        {
			StdDraw.setPenColor(new Color(0.0F, 0.5F, 0.5F));
		}
		else if (use1 <= 0.64)
        {
			StdDraw.setPenColor(new Color(0.0F, 0.4F, 0.6F));
		}
		else if (use1 <= 0.65)
        {
			StdDraw.setPenColor(new Color(0.0F, 0.3F, 0.7F));
		}
		
		else if (use1 <= 0.67)
        {
			StdDraw.setPenColor(new Color(0.0F, 0.0F, 1-(float)((use1-0.65)/0.3)));
		}
		else if (use1 <= 0.9)
		{
			StdDraw.setPenColor(new Color((float)((use1-0.67)/0.23), 0.0F, 1.0F));
		}
		
        
        StdDraw.filledPolygon(xval1,yval1);
        
        
        
        
        
        if (use2 <= 0.2)
        {
			StdDraw.setPenColor(new Color((float)(use2/0.2), 0.0F, 0.0F));
		}
		
		else if (use2 <= 0.5)
        {
			StdDraw.setPenColor(new Color(1.0F, (float)((use2-0.2)/0.3), 0.0F));
		}
		
		else if (use2 <= 0.6)
        {
			
			StdDraw.setPenColor(new Color(0.0F, 1-(float)((use2-0.5)/0.25), 0.0F));
		}
		
		else if (use2 <= 0.61)
        {
			StdDraw.setPenColor(new Color(0.0F, 0.7F, 0.3F));
		}
		else if (use2 <= 0.62)
        {
			StdDraw.setPenColor(new Color(0.0F, 0.6F, 0.4F));
		}
		else if (use2 <= 0.63)
        {
			StdDraw.setPenColor(new Color(0.0F, 0.5F, 0.5F));
		}
		else if (use2 <= 0.64)
        {
			StdDraw.setPenColor(new Color(0.0F, 0.4F, 0.6F));
		}
		else if (use2 <= 0.65)
        {
			StdDraw.setPenColor(new Color(0.0F, 0.3F, 0.7F));
		}
		
		else if (use2 <= 0.67)
        {
			StdDraw.setPenColor(new Color(0.0F, 0.0F, 1-(float)((use2-0.65)/0.3)));
		}
		else if (use2 <= 0.9)
		{
			StdDraw.setPenColor(new Color((float)((use2-0.67)/0.23), 0.0F, 1.0F));
		}
		
		
		
        StdDraw.filledPolygon(xval2,yval2);
 
        drawTree(n-1, x4, y4, x5, y5);
        drawTree(n-1, x5, y5, x3, y3);
    }
}
