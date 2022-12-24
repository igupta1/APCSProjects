import java.awt.Color;

public class Tree1
{
	private static int orig;
	public static void main(String [] args)
	{
		int temp = 5;
		orig = 6;
		double use = 0;
		
		if(args.length > 0)
		{
			temp = Integer.parseInt(args[0]);
		}
		
		use = 360.0/orig;
		
		Tree1 recursion = new Tree1();
		recursion.draw(use, temp);
	}
	
	public void draw(double use, int n)
	{
		StdDraw.setCanvasSize(700,700);
		StdDraw.setPenColor(Color.BLACK);
		
		tree(use, n, 0.5, 0, Math.PI/2, 0.3);
        StdDraw.show();
	}
	
	public void tree(double use, int n, double x, double y, double a, double branchRadius) {
        double bendAngle   = Math.toRadians(0);
        double branchAngle = Math.toRadians(use);
        double branchRatio = 0.5;

        double cx = x + Math.cos(a) * branchRadius;
        double cy = y + Math.sin(a) * branchRadius;
        StdDraw.setPenRadius(0.001 * Math.pow(n, 1.2));
        
        if (x < 0.35 && cx < 0.35)
        {
			StdDraw.setPenColor(Color.RED);
		}
		else if (x < 0.45 && cx < 0.45)
        {
			StdDraw.setPenColor(Color.ORANGE);
		}
		else if (x < 0.55 && cx < 0.55)
        {
			StdDraw.setPenColor(Color.YELLOW);
		}
		else if (x < 0.65 && cx < 0.65)
        {
			StdDraw.setPenColor(Color.GREEN);
		}
		else if (x < 0.75 && cx < 0.75)
        {
			StdDraw.setPenColor(Color.BLUE);
		}
		else if (x < 0.85 && cx < 0.85)
        {
			StdDraw.setPenColor(Color.MAGENTA);
		}
		else
		{
			StdDraw.setPenColor(Color.BLACK);
		}
        
        StdDraw.line(x, y, cx, cy);
        if (n == 0) return;


        //tree(use, n-1, cx, cy, a - branchAngle, branchRadius * branchRatio);
        //tree(use, n-1, cx, cy, a + branchAngle, branchRadius * branchRatio);
        //tree(use, n-1, cx, cy, a,               branchRadius * branchRatio);
        
        for (int i = 1; i <= orig; i++)
        {
			tree(use, n-1, cx, cy, a - (i-1)*branchAngle,  branchRadius * branchRatio);
		}
    }
	
}
