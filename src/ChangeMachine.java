import java.text.DecimalFormat;
import java.util.Scanner;

/*
 *  ChangeMachine.java
 *  
 *  Scot Andre      sandre@rollins.edu
 *  Juan Recinos    jrecinos@rollins.edu
 *  Katie Shiver    kshiver@rollins.edu
 *  Patrick Skelly  pskelly@rollins.edu
 *  
 *  Coffee Pot Project
 *  Dr. Carrington
 *  CMS270.H1
 *  Fall 2014
 *
 */

public class ChangeMachine {
	private DecimalFormat df = new DecimalFormat("###0.00");
	private int nickels = 0;
	private int dimes = 0;
	private int quarters = 0;
	private int dollars = 0;
	private int cashBalance = 0;
	private int rCardBalance = 0;
	private int moneyInserted = 0;
	private int totalPurchase = 0;
	private boolean payWithRCard = false;

	public ChangeMachine() {
		nickels = 40;
		dimes = 50;
		quarters = 40;
		dollars = 20;
		cashBalance = nickels * 5 + dimes * 10 + quarters * 25 + dollars * 100;
	}

	/**
	 * Console method used to ask a user which payment method that they wish to
	 * use.
	 */
	public void selectPaymentType() {
		Scanner keyboard = new Scanner(System.in);
		int choice = 0;
		while (choice != 1 && choice != 2) {
			System.out.println("How would you like to pay for your selection?");
			System.out.println("1. Cash");
			System.out.println("2. rCard");
			choice = keyboard.nextInt();
		}
		if (choice == 1) {
			setPaymentToCash();
		} else {
			setPaymentToRCard();
		}
	}

	/**
	 * Sets the payment type to <code>true</code> signifying that the customer
	 * wants to pay with their rCard
	 */
	public void setPaymentToRCard() {
		payWithRCard = true;
	}

	/**
	 * Sets the payment type to <code>false</code> signifying that the customer
	 * wants to pay with cash
	 */
	public void setPaymentToCash() {
		payWithRCard = false;
	}

	/**
	 * Get the method of payment
	 * 
	 * @return <code>true</code> if rCard payment type is selected,
	 *         <code>false</code> if otherwise
	 */
	public boolean getPaymentType() {
		if (payWithRCard) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Finalizes the sale, charges the user and if necessary gives change.
	 */
	public void checkOut() {
		if (payWithRCard) {
			rCardBalance += totalPurchase;
			totalPurchase = 0;
			payWithRCard = false;
		} else {
			moneyInserted -= totalPurchase;
			makeChange(moneyInserted);
			cashBalance += totalPurchase;
			payWithRCard = false;
			totalPurchase = 0;
			moneyInserted = 0;
		}
	}

	/**
	 * Coin Return
	 */
	public void coinReturn() {
		makeChange(moneyInserted);
		totalPurchase = 0;
		moneyInserted = 0;
	}

	/**
	 * Provides the total amount of money a user has inserted.
	 * 
	 * @return int total money inserted into the machine in cents
	 */
	public int getInsertedAmount() {
		return moneyInserted;
	}

	/**
	 * Verifies whether a user has inserted enough money to cover the purchase
	 * amount.
	 * 
	 * @param totalBill
	 *            the total amount to be charged to the user
	 * @return <code>true</code> if the amount of money that the user inserted
	 *         is greater than or equal to the total amount due,
	 *         <code>false</code> otherwise.
	 */
	public boolean hasPaidEnough() {
		if (payWithRCard) {
			return true;
		} else if (moneyInserted >= totalPurchase) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gives the total amount of money that is in the bank.
	 * 
	 * @return int represents the total amount of money in the bank in cents.
	 */
	public int getTotalBankBalance() {
		return (cashBalance + rCardBalance);
	}

	/**
	 * Gives the total amount of money that has been collected from customers
	 * using their rCard.
	 * 
	 * @return int represents the total amount of money collected via a rCard in
	 *         cents.
	 */
	public int getRCardBalance() {
		return rCardBalance;
	}

	/**
	 * Gives the total amount of cash currently in the bank.
	 * 
	 * @return int represents the total amount currently stored in the bank in
	 *         cents.
	 */
	public int getCashBalance() {
		return cashBalance;
	}

	/**
	 * Provides the users total purchase amount.
	 * 
	 * @return int the total amount of the current purchase in cents.
	 */
	public int getTotalPurchase() {
		return totalPurchase;
	}

	/**
	 * Adds the amount to the total purchase price.
	 * 
	 * @param itemPrice
	 *            an integer representing the purchase price of an item.
	 */
	public void addToPurchase(int itemPrice) {
		totalPurchase += itemPrice;
	}

	public void printReport() {
		System.out.println("The Change Machine has:");
		System.out.println("Nickels:\t" + nickels);
		System.out.println("Dimes:\t" + dimes);
		System.out.println("Quarters:\t" + quarters);
		System.out.println("Dollars:\t" + dollars);
		System.out.println("Total Cash:\t" + cashBalance);
		System.out.println("Total rCard:\t" + rCardBalance);
		System.out.println("Total Bank:\t" + (cashBalance + rCardBalance));
	}

	/**
	 * Returns the amount provided to the user.
	 * 
	 * @param change
	 *            an integer representing the amount of money in cents to return
	 *            to the user.
	 */
	public void makeChange(int change) {
		int balance = change;
		int dollarsReturned = 0;
		int quartersReturned = 0;
		int dimesReturned = 0;
		int nickelsReturned = 0;

		System.out.println("Your change is: $ " + df.format(change / 100.0));
		// calculate number of dollars to return
		// out of dollars - return quarters instead
		dollarsReturned = change / 100;
		if (dollars <= dollarsReturned) {
			balance -= dollars * 100;
			returnDollars(dollars);
		} else {
			balance -= dollarsReturned * 100;
			returnDollars(dollarsReturned);
		}

		// calculate number of quarters to return
		// out of quarters - return dimes and nickels instead
		quartersReturned = balance / 25;
		if (quarters <= quartersReturned) {
			balance -= quarters * 25;
			returnQuarters(quarters);
		} else {
			balance -= quartersReturned * 25;
			returnQuarters(quartersReturned);
		}

		// calculate number of dimes to return
		// out of dimes - return nickels instead
		dimesReturned = balance / 10;
		if (dimes <= dimesReturned) {
			balance -= dimes * 10;
			returnDime(dimes);
		} else {
			balance -= dimesReturned * 10;
			returnDime(dimesReturned);
		}

		// calculate number of nickels to return
		nickelsReturned = balance / 5;
		if (nickelsReturned > nickels) {
			System.out.println("Sorry, I can't return that much!");
			returnNickel(nickels);
		} else {
			returnNickel(nickelsReturned);
		}
	}

	/* ******************************************
	 * Coinage Methods:
	 */
	public void insertNickel() {
		moneyInserted += 5;
		nickels++;
		System.out.println("Nickel inserted.");
	}

	public void insertDime() {
		moneyInserted += 10;
		dimes++;
	}

	public void insertQuarter() {
		moneyInserted += 25;
		quarters++;
	}

	public void insertDollar() {
		moneyInserted += 100;
		dollars++;
	}

	public void returnNickel(int numToReturn) {
		moneyInserted -= numToReturn * 5;
		nickels--;
	}

	public void returnDime(int numToReturn) {
		moneyInserted -= numToReturn * 10;
		dimes--;
	}

	public void returnQuarters(int numToReturn) {
		moneyInserted -= numToReturn * 25;
		quarters--;
	}

	public void returnDollars(int numToReturn) {
		moneyInserted -= numToReturn * 100;
		dollars--;
	}
}
