/*
 *  Condiment.java
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

public class Condiment extends BeverageComponent {
	private String name;
	private String description;
	private int inventory;
	private int wanted;

	public Condiment() {

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public int getWanted() {
		return wanted;
	}

	public void addWanted() {
		wanted++;
	}

	public void removeWanted() {
		wanted--;
	}

	public void resetWanted() {
		wanted = 0;
	}

	@Override
	public int getInventory() {
		return inventory;
	}

	@Override
	public void print() {
		System.out.println("  " + getName());
		System.out.println("    -- " + getDescription());
	}

}
