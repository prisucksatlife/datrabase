package menu_actions;

import java.util.ArrayList;

import database.Action;
import database.Bank;
import database.Handler;
import misc.Common;
import misc.StringMethods;
import products.Account;
import products.Customer;
import user_interface.Menu;
import user_interface.States;

public class Find extends Action {
	public static class Name extends Action {
		public Name() {
			super("Find customer by name");
		}

		@Override
		public void execute() {
			System.out.print(Menu.SCENE_DIVIDER + "\nEnter name of customer to find: ");
			String name = StringMethods.filter(Common.input.nextLine());

			ArrayList<Integer> indices = Handler.findNAME(name);
			int index = Action.options(indices, "name", name);
			if (index == -1)
				return;

			try {
				if (index == indices.size())
					Action.finalize("Quit find menu", true);

				else if (Handler.setProfile(index)) {
					Action.finalize("Customer found", false);
					Handler.displayBalance();
				}
			}

			catch (Exception e) {
				States.error("That is not a valid action");
			}
		}
	}

	public static class Sin extends Action {
		public Sin() {
			super("Find customer using SIN");
		}

		@Override
		public void execute() {
			System.out.print(Menu.SCENE_DIVIDER + "\nEnter SIN of customer to find: ");
			int sin = Integer.parseInt(Common.input.nextLine());

			System.out.println(Menu.SCENE_DIVIDER + "\nSearching for customer...");
			if (Handler.setProfile(Handler.findSIN(sin))) {
				Action.finalize("Customer found", false);
				Handler.displayBalance();
			}
		}
	}

	public Find() {
		super("Find a customer profile");
	}

	@Override
	public void execute() {
		Bank.setState(States.SEARCH);
	}
}