import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class CoffeePotGui extends JFrame {
	DecimalFormat df = new DecimalFormat("##0.00");

	private Order order = new Order();
	private ProductList pl = new ProductList();
	private ChangeMachine cm = new ChangeMachine();

	private JButton[] btnBevs = new JButton[pl.getAllBeverages().size()];
	private JButton[] btnCondsAdd = new JButton[pl.getAllCondiments().size()];
	private JButton[] btnCondsRem = new JButton[pl.getAllCondiments().size()];

	private JButton btnNickel;
	private JButton btnDime;
	private JButton btnQuarter;
	private JButton btnDollar;

	private JButton btnOrder;

	private JTextArea msgAreaLeft;
	private JTextArea msgAreaCenter;
	private JTextArea msgAreaRight;

	public CoffeePotGui() {
		super("Coffee Machine");

		BeverageComponent beverage;

		// Build the main window
		JPanel mainFrame;
		setBounds(100, 100, 425, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame = new JPanel();
		mainFrame.setLayout(new GridLayout(5, 1));
		mainFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		// contentPane.setBackground(Color.black);
		setContentPane(mainFrame);

		// top (message) panel
		JPanel msgPanel = new JPanel();
		msgAreaLeft = new JTextArea();
		msgAreaCenter = new JTextArea();
		msgAreaRight = new JTextArea();
		msgPanel.setLayout(new GridLayout(1, 3));
		mainFrame.add(msgPanel);
		msgPanel.add(msgAreaLeft);
		msgPanel.add(msgAreaCenter);
		msgPanel.add(msgAreaRight);

		// print the menu:
		for (int i = 0; i < pl.getAllBeverages().size(); i++) {
			beverage = pl.getAllBeverages().get(i);
			String price = df.format(beverage.getPrice() / 100.0);
			msgAreaCenter.append(beverage.getName() + "\t$ " + price + "\n");
		}

		// beverage panel
		JPanel bevPanel = new JPanel();
		mainFrame.add(bevPanel);

		// condiment panel
		JPanel condPanel = new JPanel();
		JPanel topCondPanel = new JPanel();
		JPanel bottomCondPanel = new JPanel();
		condPanel.setLayout(new GridLayout(2, 1));
		mainFrame.add(condPanel);
		condPanel.add(topCondPanel);
		condPanel.add(bottomCondPanel);

		// payment panel
		JPanel payPanel = new JPanel();
		JPanel topPayPanel = new JPanel();
		JPanel bottomPayPanel = new JPanel();
		JPanel topLeftPayPanel = new JPanel();
		JPanel topRightPayPanel = new JPanel();
		mainFrame.add(payPanel);
		payPanel.add(topPayPanel);
		payPanel.add(bottomPayPanel);
		topPayPanel.add(topLeftPayPanel);
		topPayPanel.add(topRightPayPanel);

		// order panel
		JPanel orderPanel = new JPanel();
		JPanel orderPanelLeft = new JPanel();
		JPanel orderPanelRight = new JPanel();
		mainFrame.add(orderPanel);
		orderPanel.add(orderPanelLeft);
		orderPanel.add(orderPanelRight);

		// beverage buttons
		for (int i = 0; i < pl.getAllBeverages().size(); i++) {
			String buttonTitle = pl.getAllBeverages().get(i).getName();
			// System.out.println(buttonTitle);
			btnBevs[i] = new JButton(buttonTitle);
			bevPanel.add(btnBevs[i]);
			btnBevs[i].addActionListener(bevListener);
		}

		// condiment buttons
		for (int i = 0; i < pl.getAllCondiments().size(); i++) {
			String buttonTitle = pl.getAllCondiments().get(i).getName();
			btnCondsAdd[i] = new JButton(buttonTitle + " +");
			btnCondsRem[i] = new JButton(buttonTitle + " -");
			topCondPanel.add(btnCondsAdd[i]);
			bottomCondPanel.add(btnCondsRem[i]);
			btnCondsAdd[i].addActionListener(condListener);
			btnCondsRem[i].addActionListener(condListener);
			btnCondsAdd[i].setEnabled(false);
			btnCondsRem[i].setEnabled(false);
		}

		// payment buttons
		JButton btnRCard = new JButton("rCard");
		JButton btnCash = new JButton("Cash");
		topLeftPayPanel.add(btnRCard);
		topRightPayPanel.add(btnCash);
		btnRCard.addActionListener(payTypeListener);
		btnCash.addActionListener(payTypeListener);

		// coin buttons
		btnNickel = new JButton(".05");
		btnDime = new JButton(".10");
		btnQuarter = new JButton(".25");
		btnDollar = new JButton("1.00");
		bottomPayPanel.add(btnNickel);
		bottomPayPanel.add(btnDime);
		bottomPayPanel.add(btnQuarter);
		bottomPayPanel.add(btnDollar);
		btnNickel.setEnabled(false);
		btnDime.setEnabled(false);
		btnQuarter.setEnabled(false);
		btnDollar.setEnabled(false);
		btnNickel.addActionListener(moneyListener);
		btnDime.addActionListener(moneyListener);
		btnQuarter.addActionListener(moneyListener);
		btnDollar.addActionListener(moneyListener);

		// order buttons
		btnOrder = new JButton("Order");
		JButton btnCoinReturn = new JButton("Coin Return");
		orderPanelLeft.add(btnOrder);
		orderPanelRight.add(btnCoinReturn);
		btnOrder.addActionListener(orderListener);
		btnCoinReturn.addActionListener(orderListener);

	}// end constructor

	// ****************************************************
	// ActionListeners
	// ****************************************************

	// Beverage button ActionListener
	ActionListener bevListener = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			String button = event.getActionCommand();

			// make sure the condiments are not enabled to start with
			for (int i = 0; i < pl.getAllCondiments().size(); i++) {
				btnCondsAdd[i].setEnabled(false);
				btnCondsRem[i].setEnabled(false);
			}

			// iterate through all beverages
			for (int i = 0; i < pl.getAllBeverages().size(); i++) {
				Beverage bev = pl.getAllBeverages().get(i);

				// determine which beverage was selected & add it to the order
				if (button.contentEquals(bev.getName())) {
					order.add(bev);
					cm.addToPurchase(bev.getPrice());
					msgAreaRight.append("   Total: $ "
							+ df.format(cm.getTotalPurchase() / 100.0) + "\n");

					for (int j = 0; j < pl.getAllCondiments().size(); j++) {
						Condiment cond = pl.getAllCondiments().get(j);

						// decide which condiment buttons to turn on
						if (bev.isAcceptableCondiment(cond.getName())) {
							btnCondsAdd[j].setEnabled(true);
						}
					}// end for (j) condiments
				}// end if

				// disable the beverage buttons - no double orders
				btnBevs[i].setEnabled(false);

			}// end for (i) beverages
		}
	};// end beverage listener

	// Condiment button ActionListener
	ActionListener condListener = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			String button = event.getActionCommand();

			if (button.contains("+")) {
				// pressed a Condiment + button
				button = button.substring(0, button.length() - 2);

				for (int i = 0; i < pl.getAllCondiments().size(); i++) {
					Condiment cond = pl.getAllCondiments().get(i);

					// determine which condiment was chosen
					if (cond.getName().equals(button)) {

						// ensure there is sufficient inventory
						if (cond.getWanted() + 1 <= cond.getInventory()) {
							cond.addWanted();
							order.add(cond);
							btnCondsRem[i].setEnabled(true);
							msgAreaLeft.append(cond.getName() + " added\n");
						}
						if (cond.getWanted() == cond.getInventory()) {
							btnCondsAdd[i].setEnabled(false);
						}
					}
				}
			} else {
				// pressed a Condiment - button
				button = button.substring(0, button.length() - 2);

				for (int i = 0; i < pl.getAllCondiments().size(); i++) {
					Condiment cond = pl.getAllCondiments().get(i);

					// determine which condiment was chosen
					if (cond.getName().equals(button)) {

						// verify sufficient wanted condiments
						if (cond.getWanted() > 0) {
							cond.removeWanted();
							order.remove(cond);
							msgAreaLeft.append(cond.getName() + " removed\n");

						}
						if (cond.getWanted() == 0) {
							btnCondsRem[i].setEnabled(false);
						}
						if (cond.getWanted() < cond.getInventory()) {
							btnCondsAdd[i].setEnabled(true);
						}
					}
				}
			}
		}
	};// end condiment listener

	// Payment Type ActionListener
	ActionListener payTypeListener = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			String button = event.getActionCommand();

			if (button.equals("Cash")) {
				// cash payment
				cm.setPaymentToCash();

				// enable the coin buttons
				btnNickel.setEnabled(true);
				btnDime.setEnabled(true);
				btnQuarter.setEnabled(true);
				btnDollar.setEnabled(true);

			} else {
				// rCard Payment
				cm.setPaymentToRCard();

				// disable the coin buttons
				btnNickel.setEnabled(false);
				btnDime.setEnabled(false);
				btnQuarter.setEnabled(false);
				btnDollar.setEnabled(false);

			}
		}
	};// end payment type listener

	// coin button ActionListener
	ActionListener moneyListener = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			String button = event.getActionCommand();

			// determine which coin
			if (button.equals(".05")) {
				cm.insertNickel();
				msgAreaLeft.append("Nickel inserted\n");
			} else if (button.equals(".10")) {
				msgAreaLeft.append("Dime inserted\n");
				cm.insertDime();
			} else if (button.equals(".25")) {
				msgAreaLeft.append("Quarter inserted\n");
				cm.insertQuarter();
			} else if (button.equals("1.00")) {
				msgAreaLeft.append("Dollar inserted\n");
				cm.insertDollar();
			} else {
				msgAreaLeft.append("Unknown coin\ninserted\n");
			}

		}
	};// end money listener

	// Order & Coin Return ActionListener
	ActionListener orderListener = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			String button = event.getActionCommand();

			// if order button is chosen
			if (button.equals("Order")) {
				if (cm.getTotalPurchase() > 0) {
					if (cm.hasPaidEnough()) {

						// process order
						double change = (cm.getInsertedAmount() - cm
								.getTotalPurchase()) / 100.0;
						cm.checkOut();
						order.dispense();
						msgAreaLeft.setText("Change: $ " + df.format(change)
								+ "\n");

						// reset beverage buttons
						for (int i = 0; i < pl.getAllBeverages().size(); i++) {
							btnBevs[i].setEnabled(true);
						}

						// reset condiment buttons
						for (int i = 0; i < pl.getAllCondiments().size(); i++) {
							btnCondsAdd[i].setEnabled(false);
							btnCondsRem[i].setEnabled(false);
						}

						// reset coin buttons
						btnNickel.setEnabled(false);
						btnDime.setEnabled(false);
						btnQuarter.setEnabled(false);
						btnDollar.setEnabled(false);

						// reset right message area
						msgAreaRight.setText("Total: $ "
								+ df.format(cm.getTotalPurchase() / 100.0)
								+ "\n");
						msgAreaLeft.append("Your order has\nbeen dispensed");
					} else {
						double moneyShort = (cm.getTotalPurchase() - cm
								.getInsertedAmount()) / 100.0;
						msgAreaLeft.setText("Please insert an\nadditional $ "
								+ df.format(moneyShort) + "\n");
					}// end hasPaidEnough

				} else {
					msgAreaLeft.setText("You need to order\nsomething "
							+ "first!\n\nPress \"Coin Return\"\nto cancel "
							+ "order.\n");
				}// end check if total purchase > 0
			}// end Order if

			// if Coin Return is chosen
			if (button.equals("Coin Return")) {
				System.out.println("Order canceled!");

				// cancel order
				order.cancelOrder();
				cm.coinReturn();

				if (cm.getInsertedAmount() > 0) {
					// return coins (if any)
					cm.coinReturn();
				} else {
					System.out.println("No money to be returned.");
				}
				// reset beverage buttons
				for (int i = 0; i < pl.getAllBeverages().size(); i++) {
					btnBevs[i].setEnabled(true);
				}

				// reset condiment buttons and counters
				for (int i = 0; i < pl.getAllCondiments().size(); i++) {
					Condiment cond = pl.getAllCondiments().get(i);
					cond.resetWanted();
					btnCondsAdd[i].setEnabled(false);
					btnCondsRem[i].setEnabled(false);
				}

				// reset coin buttons
				btnNickel.setEnabled(false);
				btnDime.setEnabled(false);
				btnQuarter.setEnabled(false);
				btnDollar.setEnabled(false);

				// reset right message areas
				msgAreaLeft.setText("");
				msgAreaRight.setText("Total: $ "
						+ df.format(cm.getTotalPurchase() / 100.0) + "\n");

			}// end coin return if
		}// end actionPerformed()
	};// end order listener
}
