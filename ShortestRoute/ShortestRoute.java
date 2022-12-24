/**
 * ShortestRoute.java
 *
 * This program finds the shortest route between a set of points. This
 * program calculates the total distance it would take to reach all the points
 * and then draws the shortest route to touch all the points using StdDraw.
 * This program uses 3 heruristics to find the shortest route.
 * The first heuristic is to connect the point to the nearest neighbor in the list.
 * The second heuristic is to place the point in the list where the total distance
 * will increase the least.
 * The third method is a combination of both these heuristics in order to get
 * a shorter route than the two.
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 3/22/2019
 */

import java.awt.Color;

public class ShortestRoute
{
	//this is the head of the LinkedList that holds the route.
	private ListNode first;

	public ShortestRoute()
	{
		first = null;
	}
	
	//this method calculates the size of the LinkedList.
	public int size ( )
	{
		int count = 0;
		ListNode temp = first;
		while (temp != null) 
		{
			count++;
			temp = temp.getNext();
		}
		return count;
	}
	
	//this method calculates the length of the entire route starting from the
	//beginning to the end back to the beginning.
	public double length ( )
	{
		double dist = 0.0;
		ListNode temp = first;
		Point cur = null;
		Point next = null;
		while (temp.getNext() != null) 
		{
			cur = (Point)(temp.getValue());
			next = (Point)(temp.getNext().getValue());
			dist += cur.getDist(next);
			temp = temp.getNext();
		}
		dist += next.getDist((Point)(first.getValue()));
		return dist;
	}
	
	//this method is the algorithm for the first heuristic
	//this method looks for the nearest neighbor in the current route and then
	//inserts the point after the nearest neighbor.
	public void insertPointAtNearestNeighbor(Point p)
	{		
		//if the route is empty then add the point as first
		if (first == null)
		{
			first = new ListNode(p);
			return;
		}
		ListNode close = first;
		double min = p.getDist((Point)(first.getValue()));	
		
		double tempDistance;
		
		//goes through the entire list to find the closest point to p
		for (ListNode temp = first; temp != null; temp = temp.getNext())
		{
			tempDistance = p.getDist((Point)(temp.getValue()));
			if (tempDistance < min)
			{
				close = temp;
				min = tempDistance;
			}
		}
		//adds p after the closest point	
		close.setNext(new ListNode(p, close.getNext()));
	}
	
	//this method is algorithm for the second heuristic to calculate the smallest
	//increase in the total route length
	//this method looks for where to place the point in the route in order
	//to have the smallest increase in the total route length.
	public void insertPointAtSmallestIncrease(Point p)
	{	
		ListNode insertAfter = first;
		
		//if the point is the first or second node, then place it in the route
		if (first == null)
		{
			first = new ListNode(p);
			return;
		}
		else if (first.getNext() == null)
		{
			first.setNext(new ListNode(p));
			return;
		}
		double diff;
		double min = p.getDist((Point)(first.getValue())) + p.getDist((Point)(first.getNext().getValue())) - ((Point)(first.getValue())).getDist((Point)(first.getNext().getValue()));
		ListNode iterator = first.getNext();
		ListNode next = null;
		
		//goes through the entire route and keep track of the current node and the next node
		for (; iterator.getNext() != null; iterator = iterator.getNext())
		{	
			next = iterator.getNext();
			//checks the change in distance if the point is placed between the current point and the next point.
			diff = (p.getDist((Point)(iterator.getValue()))) + (p.getDist((Point)(next.getValue()))) - (((Point)(iterator.getValue())).getDist((Point)(next.getValue())));
			if (diff < min)
			{
				min = diff;
				insertAfter = iterator;
			}
		}
		//we also have to check the special case if the point should be placed after the last node
		//and before the first node, because the entire path starts and ends in the same place.
		diff = (p.getDist((Point)(first.getValue()))) + (p.getDist((Point)(iterator.getValue()))) - (((Point)(first.getValue())).getDist((Point)(iterator.getValue())));
		if (diff < min)
		{
			iterator.setNext(new ListNode(p));
			return;
		}
		
		//adds the point where the path will increase the least
		insertAfter.setNext(new ListNode(p, insertAfter.getNext()));
	}
	
	//this method is the third heuristic which combines the nearest neighbor
	//heurisitic and the smallest increase heuristic to get a better result than the two
	public void insertPointAtOptimizedRoute(Point p)
	{	
		//in this algorithm both methods are used but as the smallest increase
		//method is much better than the nearest neighbor method the smallest increase
		//method is weighted 97% of the total while the nearest neighbor method is weighted 3% of the total.
		double r1 = 0.97;
		double r2 = 0.03;
		ListNode insertAfter = first;
		
		//if the point is the first or second node, then place it in the route
		if (first == null)
		{
			first = new ListNode(p);
			return;
		}
		else if (first.getNext() == null)
		{
			first.setNext(new ListNode(p));
			return;
		}
		double diff;
		double min = r1*(p.getDist((Point)(first.getValue())) + p.getDist((Point)(first.getNext().getValue())) - ((Point)(first.getValue())).getDist((Point)(first.getNext().getValue()))) + r2*(p.getDist((Point)(first.getValue())));
		ListNode iterator = first.getNext();
		ListNode next = null;
		//goes through the list and finds the combined sum of the weighted values where each
		//point is placed. The change in distance of weighted 97% and the distance to the nearest
		//neighbor is weighted 3%
		for (; iterator.getNext() != null; iterator = iterator.getNext())
		{	
			next = iterator.getNext();
			diff = r1*((p.getDist((Point)(iterator.getValue()))) + (p.getDist((Point)(next.getValue()))) - (((Point)(iterator.getValue())).getDist((Point)(next.getValue())))) + r2*(p.getDist((Point)(iterator.getValue())));
			if (diff < min)
			{
				min = diff;
				insertAfter = iterator;
			}
		}
		//checks for a special place where the point should be placed at the very end of the list and
		//before the first node
		diff = r1*((p.getDist((Point)(first.getValue()))) + (p.getDist((Point)(iterator.getValue()))) - (((Point)(first.getValue())).getDist((Point)(iterator.getValue())))) + r2*(p.getDist((Point)(iterator.getValue())));
		if (diff < min)
		{
			iterator.setNext(new ListNode(p));
			return;
		}
		//adds the node at the place where the score is the least.
		insertAfter.setNext(new ListNode(p, insertAfter.getNext()));
	}
	
	
	//this method draws the final path and points onto the screen using StdDraw
	//this method loops through all the ListNodes to get the coordinates
	//for the circles and the lines
	public void draw ( )
	{
		ListNode node = first;
		while(node.getNext() != null)
		{
			Point cur = (Point)(node.getValue());
			Point next = (Point)(node.getNext().getValue());
			
			StdDraw.setPenColor(new Color(255,0,0));
			StdDraw.filledCircle(cur.getX(),cur.getY(),4);
			StdDraw.setPenColor(new Color(0,0,255));
			StdDraw.line(cur.getX(),cur.getY(),next.getX(),next.getY());
			node = node.getNext();
		}
		
		Point tmp = (Point)node.getValue();
		Point temp = (Point)first.getValue();
		StdDraw.setPenColor(new Color(255,0,0));
		StdDraw.filledCircle(tmp.getX(),tmp.getY(),4);
		StdDraw.setPenColor(new Color(0,0,255));
		StdDraw.line(tmp.getX(),tmp.getY(),temp.getX(),temp.getY());
	}
	
	
	//this method is called when the Linked List is being printed
	//and this method prints the LinkedList
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
