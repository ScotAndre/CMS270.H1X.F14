/*
 *  Sugar.java
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
public class Sugar extends Condiment {
	private String name = "Sugar";
	private String description = "All-Natural Sugar";
	private int inventory = 3;
	private int wanted = 0;

	public String getName() {
		return name;
	}

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

	public int getInventory() {
		return inventory;
	}

	public void dispense() {
		inventory--;
		removeWanted();
		System.out.println("Sugar dispensed.");
	}
}
