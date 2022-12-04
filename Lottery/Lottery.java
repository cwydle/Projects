import java.util.ArrayList;
import java.util.Random;

public class Tester {
	public static void main(String[] args) {
		Random ran = new Random(); 
		int powerball = ran.nextInt(100)+100;
		ArrayList<Integer> hopper = new ArrayList<>();
		ArrayList<Integer> pickMe = new ArrayList<>();
		// both arraylist are empty
		int range = 100;
		for (int i=0; i<range; i++) {
			hopper.add(i);
			//adds individual numbers from 0-99 into the arraylist
		}
		// test if hopper has all numbers from 0-99	(which it does)
//		System.out.println(hopper);
		int stop = 6;
		for (int i=0; i<stop; i++) {
			// tested the code by assigning the range to 10 to see if there were duplicates
			// if i ran the code below, there would have been duplicates like [3, 5, 3, 1, 5, 8]
//			pickMe.add(ran.nextInt(hopper.size()));
			// by adding the .remove to hopper. it removes the selected number from the arraylist 
			// and the for loop will continue without the removed selected number
			//pickMe.add adds a number from hopper into its arraylist
			pickMe.add(hopper.remove((ran.nextInt(hopper.size()-1))));

		}
		System.out.println("Powerball: " + powerball + "\n" + "Regular balls: " + pickMe);
	}
}
