package space;

import java.util.*;


/*
 * During iteration, missions that cost < $1 trillion (1e12) are presented in insertion order.
 * Then expensive missions (that cost >= $1 trillion) are presented in the natural sorting order
 * of the Mission class.
 */


public class MissionOrganizer implements Iterable<Mission>
{
	private final static float			ONE_TRILLION = 1.0e12f;
	
	// Insert declarations for 2 collections: 1 for cheap missions, 1 for expensive missions.
	ArrayList<Mission> cheapMissions;
	TreeSet<Mission> expensiveMissions;

	MissionOrganizer()
	{
		// Create the 2 mission collections.
		cheapMissions= new ArrayList<Mission>();
		expensiveMissions= new TreeSet<Mission>();
	}
	
	
	// Returns true if either cheapMissions or expensiveMissions contains m.
	public boolean contains(Mission m)
	{
		boolean inCheapMissions= cheapMissions.contains(m);
		boolean inExpensiveMissions= expensiveMissions.contains(m);
		return inCheapMissions || inExpensiveMissions;
	}
	
	
	// Only adds if mission is not yet contained in this collection. Adds to
	// cheapMissions or expensiveMissions, depending on whether the mission's
	// cost is < $1 trillion or >= $1 trillion.
	public void add(Mission mission)
	{
		if(contains(mission)) {
			return;
		}
		else {
			if(mission.getCost()<ONE_TRILLION) {
				cheapMissions.add(mission);
			}
			else {
				expensiveMissions.add(mission);
			}
		}

	}
	
	
	// Create an ArrayList<Mission>. Add all the cheap missions to the ArrayList; then
	// add all the expensive missions. (Hint: look in the API page for ArrayList for a
	// method that adds all members of a collection.) The ArrayList will then contain
	// all the missions, in the desired order. Return the ArrayList's iterator. This
	// technique was shown in lecture, in the presentation of the Roster class.
	public Iterator<Mission> iterator()
	{
		ArrayList<Mission> baby= new ArrayList<Mission>(cheapMissions);
		for(Mission m:expensiveMissions) {
			baby.add(m);
		}
		return baby.iterator();
	}
	
	
	
	
	// All costs are uneducated guesses.
	private final static Mission[]		TEST_MISSIONS =
	{
        new Mission("Alderaan", 1E16f),
        new Mission("High Earth Orbit", 3E8f),
        new Mission("Moon", 2.5E10f),
        new Mission("Alpha Centauri", 1E14f),
        new Mission("Asteroids", 7E11f),
        new Mission("Uranus", 9E11f),
        new Mission("Jupiter", 2E11f),
        new Mission("Low Earth Orbit", 1E8f),
        new Mission("Cetaganda", 13E16f),
        new Mission("Mars", 8E10f),
        new Mission("Neptune", 1.0E12f),
        new Mission("Barrayar", 1E16f),
        new Mission("Saturn", 8E11f),
	};
	
	
	public static void main(String[] args)
	{

		MissionOrganizer organizer = new MissionOrganizer();
		for (Mission m: TEST_MISSIONS)
			organizer.add(m);
		
		for (Mission m: organizer)
			System.out.println(m);
	}
}
