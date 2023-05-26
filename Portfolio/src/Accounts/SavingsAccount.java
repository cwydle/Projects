package Accounts;

public class SavingsAccount extends Account {
	/*
	 * Constructor that initializes a new saving account
	 * @param balance the total amount of money in the account
	 */
	public SavingsAccount(double balance) {
		this.initializeAccount(balance, "SAVINGS");
	}
	/*
	 * method that returns the account number and balance of the saving account
	 * @return this.getAccountNumber() + " ($" + getValue() + ")"; The account number and the balance
	 */
	@Override
	public String getDescription() {
		return this.getAccountNumber() + " ($" + getValue() + ")";
	}
	/*
	 * method that returns the balance of the saving account
	 * @return this.getBalance(); The balance of the account
	 */
	@Override
	public double getValue() {
		return this.getBalance();
	}
}
