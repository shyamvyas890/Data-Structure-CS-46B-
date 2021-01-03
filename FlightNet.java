package airlines;
import java.util.*;
public class FlightNet extends HashSet<Airport> {

	public boolean nameIsAvailable(String name) {
		for(Airport a: this) {
			 if(a.getName().equals(name)) {
				 return false;
			 }
		}
		return true;
	}
	public void connect(Airport a1, Airport a2) {
		a1.connectTo(a2);
		a2.connectTo(a1);
	}
	public void disconnect(Airport a1, Airport a2) {
		a1.disconnectFrom(a2);
		a2.disconnectFrom(a1);
	}
	public void removeAndDisconnect(Airport removeMe) {
		if(this.contains(removeMe)) {
			this.remove(removeMe);
		}
		for(Airport a: removeMe.getConnections()) {
			disconnect(a,removeMe);
		}
	}
	public Airport getAirportNearXY(int x, int y, int maximumDistance) {
		int xDist;
		int yDist;
		for(Airport a:this) {
			xDist=x-a.getX();
			yDist=y-a.getY();
			if(Math.hypot(xDist, yDist)<=maximumDistance) {
				return a;
			}
		}
		return null;
	}
	
	
	
	
}
