package dna;

import java.io.*;
import java.util.*;


public class FileConverter 
{
	//
	// Writes a fasta file consisting of conversion of all records from the fastq with
	// sufficient quality and unique defline.	
	//
	// This method only throws IOException. Other exceptions throws by the code should
	// be handled inside the method, using try/catch.
	//                
	//
	File fastq;
	File fasta;
	public FileConverter(File fastq, File fasta) {
		this.fastq=fastq;
		this.fasta=fasta;
	}
	public void convert() throws IOException,RecordFormatException
	{
		
		FileReader fr = new FileReader(fastq); 
		BufferedReader br = new BufferedReader(fr);
		FastqReader fqr = new FastqReader(br);
		
		PrintWriter printer= new PrintWriter(fasta); //creates new fasta file
		FastaWriter x= new FastaWriter(printer);
		
		FastqRecord f=fqr.readRecord(); //a new record is read
		FastaRecord fuck; //fastqrecord coverted to fastarecord
		int counter=0;
		while(f.getDefline()!=null) {
			try {
				if(counter==0) {
						//f= fqr.readRecord(); //a new record is read
						if(f.qualityIsLow()) {
							continue;
						}
						fuck= new FastaRecord(f); //fastqrecord coverted to fastarecord
						x.writeRecord(fuck);
						counter++;
						//f= fqr.readRecord(); //a new record is read
						//fuck= new FastaRecord(f); //fastqrecord coverted to fastarecord
				}
				else {
						f= fqr.readRecord(); //a new record is read
						if(f.qualityIsLow()) {
							continue;
						}
						fuck= new FastaRecord(f); //fastqrecord coverted to fastarecord
						x.writeRecord(fuck);
				}
				}
			catch(NullPointerException h) {
				break;
			}
			catch (Exception e) {
				continue;
			}
		}
		
		printer.close();
		br.close();
		fr.close();


	}
	
		
	
	public static void main(String[] args) throws RecordFormatException
	{
		System.out.println("Starting");
		try
		{
			File fastq = new File("data/HW4.fastq");
			if (!fastq.exists())
			{
				System.out.println("Can't find input file " + fastq.getAbsolutePath());
				System.exit(1);
			}
			File fasta = new File("data/HW4.fasta");
			FileConverter converter = new FileConverter(fastq, fasta);
			converter.convert();
		}
		catch (IOException x)
		{
			System.out.println(x.getMessage());
		}
		System.out.println("Done");
	}
}
/*while(f!=null) {
try {
	if(!f.qualityIsLow()) {
		if(!fasta.exists()) {
			fasta.createNewFile();
		}
		PrintWriter pbaby= new PrintWriter(fasta);
		FastaRecord horseradish101= new FastaRecord(f);
		System.out.println("horsradish getdefline"+horseradish101.getDefline());
		pbaby.println(horseradish101.getDefline());
		System.out.println("horsradish getsequence"+horseradish101.getSequence());
		pbaby.println(horseradish101.getSequence());
		pbaby.close();
	}
	f=fqr.readRecord();
}
catch(Exception e) {
	System.out.println(e.getMessage());
	f=fqr.readRecord();
	continue;
}

}*/
//Build chain of writers for writing fasta records to the fasta file.
		/*FileWriter fw = new FileWriter(fasta);
		PrintWriter pw = new PrintWriter(fw);
		FastaWriter faw = new FastaWriter(pw);
		pw.close();
		fw.close();*/
		
		// Read, translate, write.
		
		
		// Close fr, br, fw, and pw in reverse order of creation.