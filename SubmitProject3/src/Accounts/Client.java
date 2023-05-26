package Accounts;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Client implements Serializable {
	private static final long serialVersionUID = 3L;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private ArrayList<Portfolio> portfolios;
	
	/*
	 * Constructor Creates a new Client class object that makes a portfolio ArrayList
	 */
	public Client() {
		this.portfolios = new ArrayList<>();
	}
	/*
	 * Constructor Creates a new Client class object with the user's input of their first name, last name, and phone number. 
	 * It also makes an ArrayList with said information.  
	 */
	public Client(String firstName, String lastName, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.portfolios = new ArrayList<>();
	}
	/*
	 * Constructor Creates a new Client class object with the user's input of their first name, last name, and phone number. 
	 * It also makes an ArrayList with said information.  
	 */
	public Client(String firstName, String lastName, String phoneNumber, Portfolio ...portfolios) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.portfolios = new ArrayList<>(Arrays.asList(portfolios));
	}
	/*
	 * Method that adds a new portfolio that the user created to the ArrayList of portfolios. 
	 * @param portfolio The ArrayList of type Portfolio that contains user's first name, last name, and phone number.  
	 */
	public void addPortfolio(Portfolio portfolio) {
		this.portfolios.add(portfolio);
	}
	/*
	 * method that iterates through the portfolios and checks if it equals the portfolio name the user wishes to see.
	 * If the portfolio does not exist with the given name, then method will return null
	 * @param porfolioName The String name of the portfolio that the user wants to get 
	 * @return p The portfolio that the user wants to obtain
	 */
	public Portfolio getClientPortfolioByName(String portfolioName) {
		for (Portfolio p: portfolios) {
			if (p.getName().equals(portfolioName)) {
				return p;
			}
		}
		return null;
	}
	/*
	 * method that creates a new ArrayList of type string, iterates through the ArrayList Portfolio, and add the names of the portfolios in the arraylist
	 * of type Portfolio into the ArrayList of type string.  
	 * 
	 * @return names The names of the portfolios 
	 */
	public ArrayList<String> getClientPortfolioNames() {
		ArrayList<String> names = new ArrayList<>();
		
		for (Portfolio p: portfolios) {
			names.add(p.getName());
		}
		return names;
	}
	/*
	 * method that adds up the total amount of money of each portfolios and returns the sum of all of it
	 * @return value The total amount of all the portfolios' balance
	 */
	public double getClientPortfolioValue() {
		double value = 0;
		
		for (Portfolio p: portfolios) {
			value += p.getValue();
		}
		return value;
		
	}
	/*
	 * method that returns the first name of the user
	 * @return firstName the first name in which the user had input
	 */
	public String getFirstName() {
		return firstName;
	}
	/*
	 * method that returns the phone number of the user
	 * @return lastName the last name in which the user had input
	 */
	public String getLastName() {
		return lastName;
	}
	/*
	 * method that returns the phone number of the user
	 * @return phoneNumber the number in which the user had input
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/*
	 * method that returns the ArrayList of portfolios
	 * @return portfolios The ArrayList of type Portfolio
	 */
	public ArrayList<Portfolio> getPortfolios() {
		return portfolios;
	}
	/*
	 * method that returns the user's ID
	 * @return firstName + " " + lastName; The user's ID that is a combination of the user's first name and last name. 
	 */
	public String getId() {
		return firstName + " " + lastName;
	}
	/*
	 * method that returns the amount of portfolios in the ArrayList
	 * @return portfolios.size(); The size of the ArrayList
	 */
	public int getPortfolioCount() {
		return portfolios.size();
	}
	/*
	 * method that removes the portfolio that the user picks 
	 * @param portfolio The portfolio name that the user wishes to remove
	 * @return portfolios.removeIf(p -> p.equals(portfolio)); Removes the portfolio if the portfolio name equals what the user wants to remove
	 */
	public boolean removePortfolio(Portfolio portfolio) {
		return portfolios.removeIf(p -> p.equals(portfolio));
	}
	/*
	 * method that allows the user to set the first name
	 * @param firstName The first name that the user inputs
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/*
	 * method that allows the user to set the last name
	 * @param lastName The last name that the user inputs
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/*
	 * method that allows the user to set the phone number
	 * @param phoneNumber The phone number that the user inputs
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/*
	 * method that allows the user to set what goes into the portfolios
	 * @param portfolios The portfolios that the user inputs
	 */
	public void setPortfolios(ArrayList<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}
	
	
}
