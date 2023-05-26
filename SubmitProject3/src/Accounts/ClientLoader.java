package Accounts;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ClientLoader {
	/*
	 * method that loads the client file that the user inputs
	 * @param input The file name that the user calls upon
	 * @return client A reference to the client
	 */
	public static Client loadClient(String input) {

		// Setup a reference to the FileInputStream
	    FileInputStream file;
		
		try {
		
			// Open the file
			file = new FileInputStream(input);
			
			// Create the writer
			ObjectInputStream in = new ObjectInputStream(file);

			// Setup a reference to an ArrayList of type object and read in the list
			@SuppressWarnings("unchecked")
			ArrayList<Object> objList = (ArrayList<Object>) in.readObject();

			// Load the Client object
			Client client = (Client) objList.get(0);
				
			// Close the file
			file.close();
			
			// Return a reference to the client
			return client;
		
		} catch (Exception e) {
			
			return null;
			
		}
	}
}
