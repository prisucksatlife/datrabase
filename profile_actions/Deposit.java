package profile_actions;

import database.Action;
import database.Bank;
import misc.Common;
import products.Account;
import products.Customer;
import products.Transaction;
import user_interface.Menu;

public class Deposit extends Action {
	private static String descFormat = "Deposit of $%.2f to %s account";

	public Deposit() {
		super("Deposit");
	}

	@Override
	public void execute() {
		Customer cust = Bank.getCurrentProfile();
		Account.Type type = Action.accountChoice("Choose which account to deposit to: ", true, new boolean[] {true, true, false});
		Account acc = cust.getAccount(type);
		if (acc == null)
			return;
		
		double amount = Action.amountInput("How much do you want to deposit: ");
		Customer.process(new Transaction(null, acc, String.format(descFormat, amount, type.name()), amount));
		Action.finalize("Deposited successfully", false);
	}
}