package Accounts;

public class CheckingAccount extends Account {
	/*
	 * Constructor that initializes a new checking account
	 * @param balance the total amount of money in the account
	 */
	public CheckingAccount(double balance) {
		this.initializeAccount(balance, "CHECKING");
	}

	/*
	 * method that returns the account number and balance of the checking account
	 * @return this.getAccountNumber() + " ($" + getValue() + ")"; The account number and the balance
	 */
	@Override
	public String getDescription() {
		return this.getAccountNumber() + " ($" + getValue() + ")";
	}
	/*
	 * method that returns the balance of the checking account
	 * @return this.getBalance(); The balance of the account
	 */
	@Override
	public double getValue() {
		return this.getBalance();
	}
}
