package profile_actions;

import database.Action;
import database.Bank;
import misc.Common;
import products.Account;
import products.Customer;
import products.Transaction;
import user_interface.Menu;

public class Withdraw extends Action {
	private static String descFormat = "Withdrawal of $%.2f from %s account";

	public Withdraw() {
		super("Withdraw");
	}

	@Override
	public void execute() {
		Customer cust = Bank.getCurrentProfile();
		Account.Type type = Action.accountChoice("Choose which account to withdraw from: ", true, new boolean[] {true, true, false});
		Account acc = cust.getAccount(type);
		if (acc == null)
			return;

		double amount = Action.amountInput("How much do you want to withdraw: ");
		if (Customer.process(
				new Transaction(acc, (Account) null, String.format(descFormat, amount, type.name()), amount)))
			Action.finalize("Withdrawn successfully", false);
	}
}