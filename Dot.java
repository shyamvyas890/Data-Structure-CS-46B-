package dotlab;

import java.util.*;

public class Dot 
{
	private static String[] 	LEGAL_COLOR_NAMES =
	{
		"RED", "YELLOW", "BLUE", "CYAN", "GREEN", "MAGENTA", "ORANGE", "BLACK"
	};
	private String colorName;
	private int xCoordinate;
	private int yCoordinate;
	private int radius;
	public Dot(String colorName, int xCoordinate,int yCoordinate, int radius) {
		if(!Arrays.asList(LEGAL_COLOR_NAMES).contains(colorName)) {
			throw new IllegalArgumentException("This color "+colorName+" is not in the legal color names");
		}
		this.colorName= colorName;
		this.xCoordinate= xCoordinate;
		this.yCoordinate= yCoordinate;
		this.radius= radius;
	}
	public String getColorName() {
		return colorName;
	}
	public int getX() {
		return xCoordinate;
	}
	public int getY() {
		return yCoordinate;
	}
	public int getRadius() {
		return radius;
	}
	public static void main(String[] args) {
		Dot a= new Dot("YELLOW",1,2,3);
		System.out.println(a.toString());
		Scanner in= new Scanner(System.in);
		int x= in.nextInt();
			
		
	}
	public String toString() {
		return("Color "+colorName+" X: "+xCoordinate+" Y:"+yCoordinate+" radius: "+radius);
	}
	
	
	
	

}