package dna;

//
// FastqRecord contains the defline, sequence, and quality string
// from a record in a fastq file.
//


public class FastqRecord implements DNARecord 
{
	private String   defline;
	private String   sequence;
	private String   quality;

	
	//
	// Add a precondition check: throw RecordFormatException if the 1st char of the defline is 
	// not '@'. You will have to change the ctor declaration to say that it throws
	// the exception. The exception should contain a useful informative message.
	//
	public FastqRecord(String defline, String sequence, String quality) throws RecordFormatException
	{
		
		this.defline=defline;
		if (!this.defline.substring(0,1).equals("@")) {
			RecordFormatException a= new RecordFormatException("The first character has to be a @");
			
			throw a;
			
		}
		this.sequence= sequence;
		this.quality= quality;
	}
	
	
	// 
	// Provide the 2 methods that satisfy the interface.
	//

	

	//
	// Provide an equals() method that checks for deep equality of all 3 instance variables. 
	// When checking string variables, be sure to do it like this:  
	//              this.defline.equals(that.defline) 
	// and not like this:  
	//              this.defline == that.defline
	//
	public boolean equals(FastqRecord that) {
		if((this.defline.equals(that.defline))&&(this.sequence.equals(that.sequence))&&(this.quality.equals(that.quality))) {
			return true;
		}
		return false;
	}
	public String getDefline() {
		return defline;
	}
	public String getSequence() {
		return sequence;
	}
	public String getQuality() {
		return quality;
	}
	
	//
	// Complete this according to the assignment instructions.
	//
	public boolean qualityIsLow()
	{
		if(quality.contains("#") && quality.contains("$")) {
			return true;
		}
		return false;
	}
	
	
	//
	// Complete this. Return the sum of the hash codes of defline, sequence, and quality.
	//
	public int hashCode()
	{
		return defline.hashCode()+sequence.hashCode()+quality.hashCode();

	}
}
