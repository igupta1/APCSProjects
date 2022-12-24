/**
 * Leader.java
 *
 * @author Ishaan Gupta
 * @version 1.0
 * @since 11/12/2019
 * 
 * This class is an interface that other leaders can implement
 */


public interface Leader
{
	//so the leader can report its position to its follower(s) 
	public abstract Point getLocation();
	
	//so the leader can report its direction to its follower(s)  
	public abstract Point getDirection();
	
	//so the leader can add a follower
	public abstract void addFollower(DancingGecko gecko);
	
	//so the leader can remove a follower
	public abstract void removeFollower(DancingGecko gecko);
}

