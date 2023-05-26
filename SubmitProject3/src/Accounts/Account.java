package Accounts;

import java.io.Serializable;

import Entity.Valuable;

public abstract class Account implements Valuable, Serializable {
	private static final long serialVersionUID = 1L;

	public static int numberOfAccounts = 0;
	
	private String accountNumber;
	private double balance;
	protected String type;
	
	/*
	 * method that returns a string of what the account number is 
	 * @return accountNumber 
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	/*
	 * method that returns a double of what the balance of the account is 
	 * @return balance The amount of money in the account
	 */
	public double getBalance() {
		return balance;
	}
	/*
	 * method that returns a string of what type the account is 
	 * @return type The type, whether it is a checking or savings account
	 */
	public String getType() {
		return type;
	}
	
	/*
	 * method creates a new account with the amount and type of account the user picked. For each account created, the total amount of account is increased 
	 * by one. The account number is created as well, which is the total of accounts made. 
	 * @param double balance The amount of money in the account
	 * @param type  Checking or savings account, depending on what the user picked 
	 */
	protected void initializeAccount(double balance, String type) {
		this.balance = balance;
		this.type = type;
		
		numberOfAccounts++;
		
		String accountNumber = String.format("%03d", numberOfAccounts);
		
		this.accountNumber = accountNumber;
	}
	/*
	 * method that creates a string of the description
	 * @return this.getDescription(); the type of account (checking or savings) 
	 */
	public String toString() {
		return this.getDescription();
	}
}
