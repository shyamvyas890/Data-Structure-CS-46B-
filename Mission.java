package space;

import java.util.ArrayList;


public class Mission implements Comparable<Mission>
{
	private String			destination;
	private float			cost;
	
	
	public Mission(String destination, Float cost)
	{
		this.destination = destination;
		this.cost = cost;
	}
	
	
	public String getDestination()
	{
		return destination;
	}
	
	
	public float getCost()
	{
		return cost;
	}


	// Compare by cost, then by destination.
	public int compareTo(Mission that) 
	{
		int num= this.getDestination().compareTo(that.getDestination());
		if(this.getCost()<that.getCost()) {
			return -1;
		}
		else if (this.getCost()>that.getCost()) {
			return 1;
		}
		else {
			if(num>0) {
				return 1;
			}
			else if(num<0) {
				return -1;
			}
			else {
				return 0;
			}
			
		}


	}
	
	
	// Let compareTo() do the work. This method should just be 1 line.
	public boolean equals(Object x)
	{
		Mission d= (Mission)x;
		return this.compareTo(d)==0;

	}
	
	
	// Return the destination’s hash code.
	public int hashCode()
	{
		return getDestination().hashCode();
	}
	
	
	public String toString()
	{
		return "Mission to " + destination + " will cost " + cost;
	}
}
