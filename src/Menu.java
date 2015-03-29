/*
 *  Menu.java
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
import java.util.ArrayList;
import java.util.Iterator;

public class Menu extends BeverageComponent {
	private ArrayList<BeverageComponent> menuComponents = new ArrayList<BeverageComponent>();
	private String name;
	private String description;

	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public void add(BeverageComponent menuComponent) {
		menuComponents.add(menuComponent);
	}

	@Override
	public void remove(BeverageComponent menuComponent) {
		menuComponent.remove(menuComponent);
	}

	@Override
	public BeverageComponent getChild(int index) {
		return menuComponents.get(index);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void print() {
		System.out.print("\n" + getName());
		System.out.print(", " + getDescription());
		System.out.println("---------------------");

		Iterator<BeverageComponent> iterator = menuComponents.iterator();
		while (iterator.hasNext()) {
			BeverageComponent menuComponent = iterator.next();
			menuComponent.print();
		}
	}
}
