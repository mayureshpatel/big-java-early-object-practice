package exercise_11_13;

/**
 * Modify the BanksAcount class to throw an IllegalArgumentException when the account is
 * constructed with a negative balance, when a negative amount is deposited, or when
 * an amount that is not between 0 and the current balance is withdrawn. Write a test
 * program that causes all three exceptions to occur and that catches them all.
 * 
 * This time throw exceptions of three exception types that you provide
 * 
 * @author Mayuresh
 *
 */
public class BankAccount
{
	// Instance Variables
	private double balance;
	
	// Constructors
	/**
	 * Constructs a BankAccount with a given initial balance
	 * @param initialBalance initial balance
	 */
	public BankAccount(double initialBalance)
	{
		// Throws an IllegalInitialBalanceException if the initial balance is less than 0
		if(initialBalance < 0)
		{
			throw new IllegalInitialBalanceException("Amount is Negative");
		}
		
		balance = initialBalance;
	}
	
	/**
	 * Constructs a BankAccount with 0 balance
	 */
	public BankAccount()
	{
		balance = 0;
	}
	
	// Methods
	/**
	 * Deposits an amount into the BankAccount
	 * @param amount amount deposited
	 */
	public void deposit(double amount)
	{
		// Throws an IllegalDepositException when you try to put a negative amount into the account
		if(amount < 0)
		{
			throw new IllegalDepositException("Cannot Deposit Negative Amount");
		}
		
		balance = balance + amount;
	}
	
	/**
	 * Withdraws an amount from the BankAccount
	 * @param amount amount withdrawn
	 */
	public void withdraw(double amount)
	{
		// Throws an InsufficientFundsException when you try to withdraw more than what you have in the account
		if(amount < 0 || amount > balance)
		{
			throw new InsufficientFundsException("You cannot Withdraw that amount");
		}
		
		balance = balance - amount;
	}
	
	/**
	 * Gets the balance
	 * @return balance
	 */
	public double getBalance()
	{
		return balance;
	}
}
