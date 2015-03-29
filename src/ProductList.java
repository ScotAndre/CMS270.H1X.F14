import java.util.ArrayList;

/*
 *  ProductList.java
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
public class ProductList {
	// All Condiments
	private ArrayList<Condiment> allCondiments = new ArrayList<Condiment>();
	private Condiment cream = new Cream();
	private Condiment sugar = new Sugar();
	private Condiment lemon = new Lemon();
	private Condiment marshmallow = new Marshmallow();

	// All Products
	private ArrayList<Beverage> allBeverages = new ArrayList<Beverage>();
	private Beverage coffee = new Coffee();
	private Beverage decaf = new Decaf();
	private Beverage hotCoco = new HotChocolate();
	private Beverage tea = new Tea();
	private Beverage soup = new Soup();

	private BeverageComponent coffeeCondiments = new Menu("COFFEE CONDIMENTS",
			"Enhance Your Coffee!");

	public ProductList() {
		allCondiments();
		allBeverages();

		BeverageComponent coffeeMenu = new Menu("COFFEE MENU", "Coffee");
		BeverageComponent hotCocoMenu = new Menu("HOT COCO", "Hot Chocolate");
		BeverageComponent teaMenu = new Menu("TEA MENU", "Tea");
		BeverageComponent soupMenu = new Menu("SOUP MENU", "Soup");

		BeverageComponent teaCondiments = new Menu("Tea Condiments",
				"Tea Condiments");

		BeverageComponent hotCocoCondiments = new Menu("Hot Coco Condiments",
				"Hot Coco Condiments");

		BeverageComponent allMenus = new Menu("ALL MENUS",
				"All menus combined.");

		allMenus.add(coffeeMenu);
		allMenus.add(hotCocoMenu);
		allMenus.add(teaMenu);
		allMenus.add(soupMenu);

		coffeeMenu.add(coffee);
		coffeeMenu.add(decaf);
		coffeeMenu.add(coffeeCondiments);

		hotCocoMenu.add(hotCoco);
		hotCocoMenu.add(hotCocoCondiments);

		teaMenu.add(tea);
		teaMenu.add(teaCondiments);

		// acceptable condiments for coffee
		for (int i = 0; i < allCondiments.size(); i++) {
			if (coffee.isAcceptableCondiment(allCondiments.get(i).getName())) {
				coffeeCondiments.add(allCondiments.get(i));
			}
		}

		for (int i = 0; i < allCondiments.size(); i++) {
			if (hotCoco.isAcceptableCondiment(allCondiments.get(i).getName())) {
				hotCocoCondiments.add(allCondiments.get(i));
			}
		}

		// acceptable condiments for tea
		for (int i = 0; i < allCondiments.size(); i++) {
			if (tea.isAcceptableCondiment(allCondiments.get(i).getName())) {
				teaCondiments.add(allCondiments.get(i));
			}
		}

		soupMenu.add(soup);

		CoffeePot thePot = new CoffeePot(allMenus);

		thePot.printMenu();
	}

	/*
	 * builds an ArrayList of all of the condiments available in this machine
	 */
	private void allCondiments() {
		allCondiments.add(cream);
		allCondiments.add(lemon);
		allCondiments.add(marshmallow);
		allCondiments.add(sugar);
	}

	/*
	 * builds an ArrayList of all of the beverages available in this machine
	 */
	private void allBeverages() {
		allBeverages.add(coffee);
		allBeverages.add(decaf);
		allBeverages.add(hotCoco);
		allBeverages.add(tea);
		allBeverages.add(soup);
	}

	/**
	 * @return ArrayList of all condiments
	 */
	public ArrayList<Condiment> getAllCondiments() {
		return allCondiments;
	}

	/**
	 * @return ArrayList of all beverages
	 */
	public ArrayList<Beverage> getAllBeverages() {
		return allBeverages;
	}
}
