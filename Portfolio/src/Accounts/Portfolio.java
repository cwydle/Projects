package Accounts;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import Entity.Valuable;

public class Portfolio implements Valuable, Serializable {
	private static final long serialVersionUID = 2L;
	private String name;
	private ArrayList<Account> accounts;
	/*
	 * Constructor Creates a new Portfolio class object that makes a portfolio
	 * @param name The name of the portfolio
	 */
	public Portfolio(String name) {
		this.accounts = new ArrayList<>();
		this.name = name;
	}
	/*
	 * Constructor Creates a new Portfolio class object that makes a portfolio with the name of the portfolio
	 * and the accounts that the user wants to input
	 * @param name The name of the portfolio
	 * @param accounts the accounts of type Account that the created. It can be checking or savings accounts. 
	 */
	public Portfolio(String name, Account ...accounts) {
		this.accounts = new ArrayList<>(Arrays.asList(accounts));
		this.name = name;
	}
	/*
	 * method that adds the account the user wishes to add to the ArrayList of accounts
	 * @param account The account, whether it is a checking or savings, of type Accounts
	 */
	public void addAccount(Account account) {
		this.accounts.add(account);
	}
	/*
	 * method that find an account by its account number. It iterates through the arraylist accounts and checks if the account's account number
	 * is equal to the account number the user wishes to find. If the account number is not found then method returns null.
	 * @param accountNumber The account number the user wishes to find. 
	 * @return account The account the user wishes to find via its account number. 
	 */
	public Account getAccountByNumber(String accountNumber) {
		for (int i = 0; i < this.accounts.size(); i++) {
			Account account = this.accounts.get(i);
			if (account.getAccountNumber().equals(accountNumber)) {
				return account;
			}
		}
		return null;
	}
	/*
	 * method that gets the name of the portfolio
	 * @return this.name; The name of the portfolio 
	 */
	public String getName() {
		return this.name;
	}
	/*
	 * method that returns the ArrayList of type Account 
	 * @return this.accounts; The accounts in the portfolio
	 */
	public ArrayList<Account> getPortfolioAccounts() {
		return this.accounts;
	}
	/*
	 * method that creates an ArrayList of type String called descriptionList. It returns a list of the member's description 
	 * of the arraylist of type Portfolios. 
	 * A for loop is implemented to go through the ArrayList to get the description of each element in it
	 * and add the element to descriptionList	 
	 * @return descriptionList A list of the accounts' description in the portfolio
	 */
	public ArrayList<String> getPortfolioMemberDescriptions() {
		ArrayList<String> descriptionList = new ArrayList<>();
		
		for (int i = 0; i < this.accounts.size(); i++) {
			descriptionList.add(this.accounts.get(i).getDescription());
		}
		
		return descriptionList;
	}
	/*
	 * method that creates an ArrayList of type String called descriptionList. It returns a list of types, whether it is a savings or checkings account, 
	 * of the arraylist of type Portfolios. 
	 * A for loop is implemented to go through the ArrayList to get the description of each element in it with the account's account number 
	 * and add the element to descriptionList as well as the account's account number
	 * @return descriptionList An ArrayList of the accounts' account numbers and types in the portfolio
	 */
	public ArrayList<String> getPortfolioMemberNamesWithTypes() {
		ArrayList<String> descriptionList = new ArrayList<>();
		
		for (int i = 0; i < this.accounts.size(); i++) {
			Account account = this.accounts.get(i);
			descriptionList.add(account.getAccountNumber() + " " + account.getType());
		}
		
		return descriptionList;
	}
	/*
	 * method that creates an ArrayList of type String called descriptionList. It returns a list of types of the arraylist of type Portfolios. 
	 * A for loop is implemented to go through the ArrayList to get the type of each element in it and add the element to descriptionList	 
	 * @return descriptionList A list of the member's type in the portfolio
	 */
	public ArrayList<String> getPortfolioMemberTypes() {
		ArrayList<String> descriptionList = new ArrayList<>();
		
		for (int i = 0; i < this.accounts.size(); i++) {
			Account account = this.accounts.get(i);
			descriptionList.add(account.getType());
		}
		
		return descriptionList;
	}
	/*
	 * method that creates an ArrayList of type double called descriptionList. The method iterates through the accounts and adds the value of the accounts
	 * to the descriptionList 
	 * @return descriptionList An ArrayList of the values of the accounts
	 */
	public ArrayList<Double> getPortfolioMemberValues() {
		ArrayList<Double> descriptionList = new ArrayList<>();
		
		for (int i = 0; i < this.accounts.size(); i++) {
			Account account = this.accounts.get(i);
			descriptionList.add(account.getValue());
		}
		
		return descriptionList; 
	}
	/*
	 * method that adds up the total value of the portfolios. the method iterates through the accounts and adds up the values
	 * @return total The total value of money in all the accounts in the portfolio
	 */
	public double getPortfolioTotalValue() {
		double total = 0;
		
		for (int i = 0; i < this.accounts.size(); i++) {
			Account account = this.accounts.get(i);
			total += account.getValue();
		}
		
		return total;
	}
	/*
	 * method that removes an account by its account number
	 * @param accountNumber The number associated with the account
	 * @return this.accounts.removeIf(acct -> acct.getAccountNumber().equals(accountNumber)); The removal of the account if it equals the account number
	 */
	public boolean removeAccountByNumber(String accountNumber) {
		return this.accounts.removeIf(acct -> acct.getAccountNumber().equals(accountNumber));
	}
	/*
	 * method that sets the name of the portfolio 
	 * @param name The name in which the user inputs 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/*
	 * method that creates a String of the getDescription method
	 * @return this.getDescription(); The description of the account, which is the name of the account and the money in the account
	 */
	public String toString() {
		return this.getDescription();
	}

	/*
	 * method that gets the name of the account and the money that is in the account
	 * @return name + " ($" + getValue() + ")"; The description of the account, which is the name of the account and the money in the account
	 */
	@Override
	public String getDescription() {
		return name + " ($" + getValue() + ")";
	}
	/*
	 * method that returns the value of the portfolios
	 * @return this.getPortfolioTotalValue(); The amount of money in all the portfolios
	 */
	@Override
	public double getValue() {
		return this.getPortfolioTotalValue();
	}
}
