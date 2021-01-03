package simpledoku;

import java.util.*;
import java.lang.Math;


public class SimpledokuGrid 
{
	private int				nCellsPerSide;
	private int[][]			values;
	
	public int[][] getValues(){
		return values;
	}
	public int getnCellsPerSide() {
		return nCellsPerSide;
	}
	public SimpledokuGrid(int nCellsPerSide)
	{
		this.nCellsPerSide = nCellsPerSide;
		values = new int[nCellsPerSide][nCellsPerSide];
	}
	public void changeCell(int[][] x, int y, int z, int too) {// mutator method gang
		x[y][z]=too;
	}
	
	
	// This is called a "copy constructor". A copy ctor has 1 arg, which is the same type as
	// the current class. The ctor should make the new instance look just like the "source"
	// instance. CAUTION: The source and the current grid should each have their own "values"
	// array. So don't say "this.values = source.values". You have to create a new values
	// array, and copy numbers from source.values into the new array, one at a time.
	public SimpledokuGrid(SimpledokuGrid source)
	{	
		this.nCellsPerSide= source.nCellsPerSide;
		values=new int[nCellsPerSide][nCellsPerSide];
		for(int i=0;i<this.nCellsPerSide;i++) {
			for(int j=0;j<this.nCellsPerSide;j++) {
				values[i][j]=source.values[i][j];
			}
		}
	}	
	
	
	//
	// Returns true if the input list contains a repeated value that isn't zero.
	// Call this from isLegal(). DON’T CHANGE THIS METHOD, BUT UNDERSTAND HOW IT WORKS.
	//
	private boolean containsNonZeroRepeats(ArrayList<Integer> checkUs)
	{
		HashSet<Integer> set = new HashSet<>();
		for (Integer i: checkUs)
		{
			if (i != 0)
			{
				if (set.contains(i))
					return true;
				else
					set.add(i);
			
			}
		}
		
		return false;
	}
	
	
	public boolean isLegal()
	{
		
		// Check all rows. For each row, put the corresponding numbers from
		// the "values" array into an ArrayList<Integer>. Then pass the array
		// list to containsNonZeroRepeats(). If containsNonZeroRepeats() return true,
		// then this grid isn't legal, so return false.
		for(int i=0;i<nCellsPerSide;i++) {
			ArrayList<Integer> x= new ArrayList<Integer>();
			for(int j=0;j<nCellsPerSide;j++) {
				x.add(this.getValues()[i][j]);
			}
			if(containsNonZeroRepeats(x)==true) {
				return false;
			}
			x.clear();
		}
		// Check all columns. For each column, put the corresponding numbers from
		// the "values" array into an ArrayList<Integer>. Then pass the array
		// list to containsNonZeroRepeats(). If containsNonZeroRepeats() return true,
		// then this grid isn't legal, so return false.
		
		for(int i=0;i<this.getnCellsPerSide();i++) {
			ArrayList<Integer> x= new ArrayList<Integer>();
			for(int j=0;j<this.getnCellsPerSide();j++) {
				x.add(this.getValues()[j][i]);
			}
			if(containsNonZeroRepeats(x)==true) {
				return false;
			}
			x.clear();
		}
		
		// Check top-left to bottom-right diagonal. 
		//System.out.println("");
		ArrayList<Integer> rando= new ArrayList<Integer>();
		for(int i=0;i<nCellsPerSide;i++) {
			rando.add(values[i][i]);
			//System.out.println(values[i][i]);
			if(containsNonZeroRepeats(rando)==true) {
				return false;
			}
		}
		// Check top-right to bottom-left diagonal. 
		ArrayList<Integer> rightleft= new ArrayList<Integer>();
		for(int i=0;i<nCellsPerSide;i++) {
			rightleft.add(values[i][Math.abs(nCellsPerSide-1-i)]);
			if(containsNonZeroRepeats(rightleft)==true) {
				return false;
			}
		}
		return true;
		// If we haven't returned yet, this grid must be legal.
	}
	// Returns true if all members of the values[][] array are non-zero.
	public boolean isFull()
	{
		for(int i=0;i<this.getValues().length;i++) {
			for(int j=0;j<this.getValues()[i].length;j++) {
				if(this.getValues()[i][j]==0) {
					return false;
				}
			}
		}
		return true;
	}
	public static void sop(Object x) {
		System.out.println(x);
	}
	
	
	
	// Returns the Evaluation corresponding to the state of this grid. The return will be
	// Evaluation.ABANDON, Evaluation.ACCEPT, or Evaluation.CONTINUE. Abandon if illegal,
	// accept if legal and complete, continue if legal and incomplete.
	public Evaluation evaluate()
	{
		if(this.isLegal()==false) {
			return Evaluation.ABANDON;
		}
		if(this.isLegal()==true && this.isFull()==true) {
			return Evaluation.ACCEPT;
		}
		if(this.isLegal()==true && this.isFull()==false) {
			return Evaluation.CONTINUE;
		}
		return null;
		
		

	}
	
	
	// Returns a size=2 array of ints that index the next empty cell in the values[][] array.
	// The 2 ints in the returned array are the first and second indices into the values[][] array.
	// Returns null if this grid is full.  DON’T CHANGE THIS METHOD, BUT UNDERSTAND HOW IT WORKS.
	private int[] getIndicesOfNextEmptyCell()
	{
		for (int x=0; x<nCellsPerSide; x++)
			for (int y=0; y<nCellsPerSide; y++)
				if (values[x][y] == 0)
					return new int[] {x, y};
		
		return null;
	}
	
	
	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of "values". Returns an array list containing nCellsPerSide grids that look 
	// like the current grid, except the empty member contains 1, 2, 3 .... nCellsPerSide. 
	// 
	//
	// Example: if this grid = 12300
	//                         00000
	//                         00000
	//                         00000
	//                         00000
	//
	// Then the returned array list contains:
	//
	//      12310        12320        12330        12340        12350
	//      00000        00000        00000        00000        00000
	//      00000        00000        00000        00000        00000
	//      00000        00000        00000        00000        00000
	//      00000        00000        00000        00000        00000
	//
	// To create each new grids, use the copy ctor and pass in "this" grid. Then
	// change one member of the "values" of the new grid.
	// CAUTION: 
	public ArrayList<SimpledokuGrid> generateAllNextGrids()
	{		
		int[] indicesOfNext = getIndicesOfNextEmptyCell();
		ArrayList<SimpledokuGrid> nextGrids = new ArrayList<SimpledokuGrid>();
		for(int i=1;i<=getnCellsPerSide();i++) {
			SimpledokuGrid x= new SimpledokuGrid(this);
			x.changeCell(x.getValues(), indicesOfNext[0], indicesOfNext[1], i);
			nextGrids.add(x);
		}
		
		
		
		
		// Generate some grids and put them in nextGrids. Be sure that every
		// grid is a separate object.
		
		
		return nextGrids;
	}
	
	
	// Use this for debugging if it's helpful.  DON’T CHANGE THIS METHOD, BUT UNDERSTAND HOW IT WORKS.
	public String toString()
	{
		String s = "";
		for (int j=0; j<nCellsPerSide; j++)
		{
			for (int i=0; i<nCellsPerSide; i++)
			{
				if (values[j][i] == 0)
					s += ".";
				else
					s += ("" + values[j][i]);
			}
			s += "\n";
		}
		return s;
	}
}
	