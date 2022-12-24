/**
 * ShortestRoute.java
 *
 * Describe the class
 *
 * @author Sahil Goel
 * @version 1.0
 * @since 3/22/2019
 */

import java.awt.Color;

public class ShortestRoute2
{
	private ListNode first;

	public ShortestRoute2()
	{
		first = null;
	}
	
	public int size ( )
	{
		int sum = 0;
		ListNode temp = first;
		
		while(temp != null)
		{
			sum++;
			temp = temp.getNext();
		}
		
		return sum;
	}
	
	public double length ( )
	{
		double sum = 0;
		ListNode temp = first;
		
		while(temp.getNext() != null)
		{
			sum += ((Point)(temp.getValue())).getDist((Point)(temp.getNext().getValue()));
			temp = temp.getNext();
		}
		
		return (sum + ((Point)(temp.getValue())).getDist((Point)(first.getValue())));
	}
	
	public void insertPointAtNearestNeighbor(Point p)
	{		
		//~ System.out.println(p.getX() + ", " + p.getY());
		
		if(first == null)
		{
			first = new ListNode(p);
			return;
		}
		//~ else if(first.getNext() == null)
		//~ {
			//~ first.setNext(new ListNode(p));
		//~ }
		//~ else
		//~ {
		ListNode temp = first.getNext();
		ListNode use = first;
		double least = p.getDist((Point)(first.getValue()));
		double dist = 0;
		
		while(temp != null)
		{
			dist = p.getDist((Point)(temp.getValue()));
			
			if(dist < least)
			{
				least = dist;
				use = temp;
			}
			
			temp = temp.getNext();
		}
		
		use.setNext(new ListNode(p, use.getNext()));
		//~ }
	}

	public void insertPointAtSmallestIncrease(Point p)
	{
		//~ if(first == null)
		//~ {
			//~ first = new ListNode(p);
		//~ }
		//~ else if(first.getNext() == null)
		//~ {
			//~ first.setNext(new ListNode(p));
		//~ }
		//~ else
		//~ {
			//~ ListNode temp = first.getNext();
			//~ ListNode use = first;
			//~ // double difference = getDist(getPoint(first));
			
			//~ while(temp != null)
			//~ {
				
			//~ }
		//~ }
	}

	
	public void draw ( )
	{
		ListNode node = first;
		
		while(node != null)
		{
			Point nodePoint = (Point)(node.getValue());
			
			if(node.getNext() != null)
			{
				Point nextNodePoint = (Point)(node.getNext().getValue());
				StdDraw.setPenColor(new Color(0,0,255));
				StdDraw.line(nodePoint.getX(),nodePoint.getY(),nextNodePoint.getX(),nextNodePoint.getY());
			}
			
			StdDraw.setPenColor(new Color(255,0,0));
			StdDraw.filledCircle(nodePoint.getX(),nodePoint.getY(), 4);
			node = node.getNext();
			
			//~ StdDraw.setPenColor(new Color(0,0,255));
			//~ StdDraw.line(node.getValue(0,45,600,725);
			//~ StdDraw.setPenColor(new Color(255,0,0));
			//~ StdDraw.filledCircle(400,250,4);
		}
			

	}
	
    public String toString()
    {
		int count = 0;
		ListNode node = first;
		String result = new String("");
		while(node != null)
		{
			result += String.format("%4d: %s%n",count,(Point)node.getValue());
			node = node.getNext();
			count++;
		}
		return result;
    }
}
