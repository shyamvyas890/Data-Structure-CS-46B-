package dna;

import java.io.*;

// Reads lines from a BufferedReader and builds a FastqRecord.


public class FastqReader 
{
	private BufferedReader   theBufferedReader;

	
	public FastqReader(BufferedReader br)
	{
		theBufferedReader=br;
	}
	
	// Returns next record in the file, or null if EOF (end-of-file).
	public FastqRecord readRecord() throws IOException, RecordFormatException
	{
		// Read the defline from the BufferedReader. Return null if you read null, 
		// indicating end of file.

		
		// Read the next 3 lines from the buffered reader. Construct and return
		// a FastqRecord.
		String def= theBufferedReader.readLine();
		//System.out.println("def"+def);
		if (def.equals(null))
		{
			theBufferedReader.close();
			return null;
		}
		if (!def.substring(0,1).equals("@")) {
			theBufferedReader.readLine();
			theBufferedReader.readLine();
			theBufferedReader.readLine();
			//theBufferedReader.readLine();
			throw new RecordFormatException("You gotta start with a @ bro but you had"+ def);
			
		}
		String seq= theBufferedReader.readLine();
		//System.out.println("seq"+seq);

		for(int i=0;i<seq.length()-1;i++) {
			if(!(seq.substring(i,i+1).equals("A")|| seq.substring(i,i+1).equals("C")||seq.substring(i,i+1).equals("T")||seq.substring(i,i+1).equals("G"))) {
				theBufferedReader.readLine();
				theBufferedReader.readLine();
				//theBufferedReader.readLine();
				throw new RecordFormatException("You can only have ACTG bro!");
			}
		}
		String plus= theBufferedReader.readLine();
		if (!plus.equals("+")) {
			throw new RecordFormatException("Third line has to be a plus bro!");
		}
		String quality= theBufferedReader.readLine();
		//System.out.println("quality"+quality);

		/*if(quality.length()!=seq.length()) {
			throw new RecordFormatException("This line has to be the same length as the sequence bro!");
		}*/
		//theBufferedReader.close();
		return new FastqRecord(def,seq,quality);
		

	}
	/*public static void main(String[] args) throws FileNotFoundException,IOException {
		File x= new File("mygod.txt");
		PrintWriter y= new PrintWriter(x);
		y.println("Hello bruh");
		y.println("I like python better");
		y.println("Idk what else to write");
		y.println("hshshs");
		y.close();
		BufferedReader gre=new BufferedReader(new FileReader(x));
		gre.readLine();
		gre.readLine();
		if(gre.readLine().equals(null)) {
			System.out.println("d");
		}
		System.out.println(gre.readLine());
		gre.close();
	}*/
}
