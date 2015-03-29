import java.util.ArrayList;

public class Order {

	private ArrayList<BeverageComponent> order = new ArrayList<BeverageComponent>();

	public Order() {
	}

	/**
	 * Adds a beverage component to the order
	 * 
	 * @param beverage
	 *            the beverage that will be added to the order
	 */
	public void add(BeverageComponent beverage) {
		order.add(beverage);
	}

	/**
	 * Removes a beverage component to the order
	 * 
	 * @param beverage
	 *            the beverage that will be removed from the order
	 */
	public void remove(BeverageComponent beverage) {
		order.remove(beverage);
	}

	/**
	 * @return ArrayList the list of beverage components currently ordered
	 */
	public ArrayList<BeverageComponent> getOrder() {
		return order;
	}

	/**
	 * Cancels an order
	 */
	public void cancelOrder() {
		order.clear();
	}

	/**
	 * finalizes and dispenses an order
	 */
	public void dispense() {
		BeverageComponent beverage;
		for (int i = order.size() - 1; i >= 0; i--) {
			beverage = order.get(i);
			beverage.dispense();
			order.remove(i);
		}
	}

	/**
	 * Outputs the current order to the console
	 */
	public void printOrder() {
		BeverageComponent beverage;
		for (int i = 0; i < order.size(); i++) {
			beverage = order.get(i);
			System.out.println(beverage.getName());
		}
	}

}
