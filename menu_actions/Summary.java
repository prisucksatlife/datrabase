package menu_actions;

import database.Action;
import database.Bank;
import products.Customer;
import user_interface.Menu;

public class Summary extends Action {
	public Summary() {
		super("Summarize all customer data");
	}

	@Override
	public void execute() {
		Menu.displayHeader("Users Summary");
		for (Customer c : Bank.getCustomers())
			System.out.printf("\t❏ %s\n", c.display());
	}
}
