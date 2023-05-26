package Entity;

public interface Valuable {
	/*
	 * abstract method that is of type String and is suppose to get the description, whether the account is a savings or checking.
	 */
	public String getDescription();
	/*
	 * abstract method that is of type double an is suppose to get the money amount in the account
	 */
	public double getValue();
}
