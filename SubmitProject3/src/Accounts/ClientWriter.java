package Accounts;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ClientWriter {
	/*
	 * method that creates a new client with the user's given information and adds it to the ArrayList of type object and writes 
	 * a binary file of the arraylist 
	 * @param client A reference to the object Client
	 * @param filename The name of the file name
	 * @return true or false depending on whether the user successfully creates a binary file of their Client file
	 */
	public static boolean writeClient(Client client, String filename) {
		// Create a reference to a FileOutputStream object
		FileOutputStream file;
		
		try {
			
			// Create the FileOutputStream object and pass in the the file
			file = new FileOutputStream(filename);
			
			// Create the writer
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			// Create a new empty list of type Object
			ArrayList<Object> myList = new ArrayList<>();
	
			// Add your client object to the list
			myList.add(client);
			
			// Write the list out to file (binary)
			out.writeObject(myList);
	
			// Close the file
			file.close();
			
			return true;
		
		} catch (Exception e) {
			
			return false;
			
		}
		
	}
}
