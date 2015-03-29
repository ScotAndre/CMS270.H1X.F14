/*
 *  BeverageComponent.java
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
public abstract class BeverageComponent {
	/**
	 * Add a beverage component object to the menu.
	 * 
	 * @param menuComponent
	 *            the component or leaf that you want to add
	 */
	public void add(BeverageComponent menuComponent) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Remove a beverage component object to the menu.
	 * 
	 * @param menuComponent
	 *            the component or leaf that you want to remove
	 */
	public void remove(BeverageComponent menuComponent) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param index
	 *            the index of the child to return
	 * @return BeverageComponent the child specified by the index given
	 */
	public BeverageComponent getChild(int index) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return String the name of the beverage
	 */
	public String getName() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return String the description of the beverage
	 */
	public String getDescription() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return int the price of the beverage
	 */
	public int getPrice() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return int the current inventory of the beverage
	 */
	public int getInventory() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @param menuComponent
	 * @return boolean returns <code>true</code> if the condiment is acceptable
	 *         to the beverage, otherwise it returns <code>false</code>
	 */
	public boolean isAcceptableCondiment(BeverageComponent menuComponent) {
		throw new UnsupportedOperationException();
	}

	/**
	 * dispenses the beverage and adjusts the inventory
	 */
	public void dispense() {
		throw new UnsupportedOperationException();
	}

	public void print() {
		throw new UnsupportedOperationException();
	}
}
