package dotlab;
import java.io.*;
import java.util.*;

public class DotReader 
{
	private BufferedReader			br;
	
	public DotReader(BufferedReader br)
	{
		this.br = br;
	}

public Dot readDot() throws IOException, DotException
{
		//File f= new File("C:\\Users\\smvya\\OneDrive\\Desktop\\46B_F20_Lab4_fixed.zip\\3dots.txt");
		//Scanner in= new Scanner(f);
		String line= br.readLine();
		if (line==null) {
			return null;
		}
		//while(in.hasNextLine()) {
		//String container= in.nextLine();
		//String[] hi= container.split(",");	
			String[] current= line.split(",");
			if (current.length==4) {
				String currentColor= current[0];
				int currentX= Integer.parseInt(current[1]);
				int currentY= Integer.parseInt(current[2]);
				int currentRadius= Integer.parseInt(current[3]);	
				//line=br.readLine();
				return new Dot(currentColor,currentX,currentY,currentRadius);
			}
			else {
				DotException g= new DotException("You have more than 4 arguments and that is not allowed in this line "+line);
				throw g;
			}
		
		
	}
}