import java.util.Iterator;

public class Tester {

	public static void main(String[] args) {
		SingleLinkedList<String> courses = new SingleLinkedList<String>();
		
		courses.add("Data Structures"); 
		courses.add("Discrete Math"); 
		courses.add("Calculus"); 
		courses.add("A.I."); 
		courses.add("Computer Architecture"); 
		courses.add("Yo mama"); 
		

		
		System.out.println();
		System.out.println("For get method");
		for (int i = 0; i <courses.size(); i++) {
			System.out.println(courses.get(i));
		}
		
		System.out.println();
		System.out.println("using for each");
		for(String e : courses) {
			System.out.println(e);
		}
		
		System.out.println();
		System.out.println("Using iterator:");
		Iterator<String> iter = courses.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		System.out.println("\n" + "Get the course in the first node: " + courses.get(0));

		
		
		
	}
}
