
/**
 * Circle.java
 *
 * The Circle objects used in DontTouch.java are from this class. 
 * This class is contains the getter and setter methods for the Circle objects.
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 10/25/2019
 */

public class Circle
{
	private double x, y;
	private double radius;
	
	public Circle()
	{
		
	}
	
	public Circle (double x, double y, double radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public Circle (double radius)
	{
		this.radius = radius;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getRadius()
	{
		return radius;
	}
	
	public double getArea ( )
	{
		return 3.1416*radius*radius;
	}
	
	public void setRadius(double r)
	{
		this.radius = r;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
}
