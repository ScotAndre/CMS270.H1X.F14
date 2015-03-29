import java.awt.EventQueue;

/*
 *  CoffeePotMain.java
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
public class CoffeePotMain {

	public static void main(String[] args) {
		ProductList pl = new ProductList();
		// Order order = new Order();

		// run GUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoffeePotGui frame = new CoffeePotGui();
					frame.setTitle("Coffee Machine");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
