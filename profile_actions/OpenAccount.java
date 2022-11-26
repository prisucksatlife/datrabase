package profile_actions;

import accounts.Chequing;
import accounts.Credit;
import accounts.Savings;
import database.Action;
import database.Bank;
import misc.Common;
import products.Account;
import products.Customer;
import user_interface.Menu;
import user_interface.States;

public class OpenAccount extends Action {
	private static String descFormat = "%s account created";

	// -check if account already exists

	public OpenAccount() {
		super("Open a new account");
	}

	@Override
	public void execute() {
		Customer cust = Bank.getCurrentProfile();
		Account.Type req = Action.accountChoice("Choose type of account to open", false);
		if (req == null)
			return;

		if (cust.getAccount(req) != null) {
			States.error("Account already exists");
			return;
		}

		double amount = 0;
		if (req != Account.Type.Credit) 
			amount = Action.amountInput("Enter initial balance: ");

		boolean valid = false;
		switch (req) {
			case Chequing:
				valid = new Chequing(cust, amount).validate();
				break;
			case Savings:
				valid = new Savings(cust, amount).validate();
				break;
			case Credit:
				valid = new Credit(cust, amount).validate();
				break;
		}

		if (!valid) {
			States.error("Account cannot be created for this customer");
			cust.removeAccount(req);
			return;
		}

		cust.addActivity(String.format(descFormat, req.name()), true);
		Action.finalize("Account created successfully", false);
	}
}