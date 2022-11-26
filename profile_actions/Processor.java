package profile_actions;

import database.Action;
import database.Bank;
import misc.Common;
import products.Account;
import products.Customer;
import products.Transaction;
import user_interface.Menu;
import user_interface.States;

public class Processor {
	public static class Cheque extends Action {
		private static String descFormat = "Cheque of $%.2f processed";

		public Cheque() {
			super("Process a cheque");
		}

		@Override
		public void execute() {
			Customer cust = Bank.getCurrentProfile();
			Account acc = cust.getAccount(Account.Type.Chequing);
			if (acc == null) {
				States.error("Customer does not have chequing account");
				return;
			}

			double amount = Action.amountInput("Enter cheque amount: ");
			if (acc.getBalance() < 1000)
				amount += 0.15;
			Customer.process(new Transaction(null, acc, String.format(descFormat, amount), amount));
			Action.finalize("Cheque processed successfully", false);
		}
	}

	public static class Payment extends Action {
		private static String descFormat = "Credit card payment of $%.2f using %s account";

		public Payment() {
			super("Process payment for credit card");
		}

		@Override
		public void execute() {
			Customer cust = Bank.getCurrentProfile();
			Account card = cust.getAccount(Account.Type.Credit);
			if (card == null) {
				States.error("Customer does not have a credit card");
				return;
			}

			Account.Type type = Action.accountChoice("Choose which account to use to pay: ", true,
					new boolean[] { true, true, false });
			Account acc = cust.getAccount(type);
			if (acc == null)
				return;

			double amount = Action.amountInput("How much do you want to pay: ");
			Customer.process(new Transaction(acc, card, String.format(descFormat, amount, type.name()), amount));
			Action.finalize("Payment processed successfully", false);
		}
	}

	public static class Purchase extends Action {
		private static String descFormat = "%s purchased for $%.2f";

		public Purchase() {
			super("Process a purchase");
		}

		@Override
		public void execute() {
			Customer cust = Bank.getCurrentProfile();
			Account acc = cust.getAccount(Account.Type.Credit);
			if (acc == null) {
				States.error("Customer does not have a credit card");
				return;
			}

			System.out.println(Menu.SCENE_DIVIDER);
			System.out.print("Enter name of item purchased: ");
			String item = Common.input.nextLine();
			double price = Action.amountInput("Enter item price: ");;

			Customer.process(new Transaction(acc, null, String.format(descFormat, item, price), price));
			Action.finalize("Purchase processed successfully", false);
		}
	}
}
