package profile_actions;

import database.Action;
import database.Bank;
import database.Handler;
import products.Account;
import products.Customer;
import user_interface.Menu;
import user_interface.States;

public class CancelAccount extends Action {
	private static String descFormat = "%s account removed";

	// -check if account already exists

	public CancelAccount() {
		super("Cancel a pre-existing account");
	}

	@Override
	public void execute() {
		Customer cust = Bank.getCurrentProfile();
		Account.Type req = Action.accountChoice("Choose account to cancel", true);
		if (req == null)
			return;

		Account acc;
		if ((acc = cust.getAccount(req)) == null) {
			States.error("Account doesn't exist");
			return;
		}

		if (!acc.cancel()) {
			States.error("Account cannot be cancelled because of outstanding debts");
			return;
		}

		cust.removeAccount(req);
		cust.addActivity(String.format(descFormat, req.name()), true);
		Action.finalize("Account removed successfully", false);

		if (cust.hasAccount())
			return;
		Handler.removeCustomer(cust);
		System.out.println(Menu.SCENE_DIVIDER + "\nCustomer removed since they have no accounts in the bank");
	}
}