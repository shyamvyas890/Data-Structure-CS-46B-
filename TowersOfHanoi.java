package trees;

public class TowersOfHanoi {

	public void hanoi(String start, String spare, String destination, int plates) {
		if(plates==1) {
			printHelper(start, destination);
			return;
		}
		hanoi(start, destination, spare, plates-1);
		printHelper(start, destination);
		hanoi(spare, start, destination, plates-1);
		
	}
	
	
	
	

	public void printHelper(String a, String b) {
		System.out.println("Move a disc from rod "+a+" to rod "+b+".");
	}
	public static void main(String[] args) {
		TowersOfHanoi a= new TowersOfHanoi();
		a.hanoi("1", "2", "3", 15);
	}

}
