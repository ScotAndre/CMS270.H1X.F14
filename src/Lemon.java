/*
 *  Lemon.java
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
public class Lemon extends Condiment {
	private String name = "Lemon";
	private String description = "Lemon Concentrate";
	private int inventory = 100;
	private int wanted = 0;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public int getWanted() {
		return wanted;
	}

	@Override
	public void addWanted() {
		wanted++;
	}

	@Override
	public void removeWanted() {
		wanted--;
	}

	@Override
	public void resetWanted() {
		wanted = 0;
	}

	@Override
	public int getInventory() {
		return inventory;
	}

	@Override
	public void dispense() {
		inventory--;
		removeWanted();
		System.out.println("Lemon dispensed.");
	}
}
