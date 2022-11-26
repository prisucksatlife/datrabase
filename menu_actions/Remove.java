package menu_actions;

import java.util.ArrayList;

import database.Action;
import database.Bank;
import database.Handler;
import misc.Common;
import misc.StringMethods;
import user_interface.Menu;
import user_interface.States;

public class Remove extends Action {
	public static class Name extends Action {
		public Name() {
			super("Remove a customer using first name");
		}

		@Override
		public void execute() {
			System.out.print(Menu.SCENE_DIVIDER + "\nEnter name of customer to remove: ");
			String name = StringMethods.filter(Common.input.nextLine());

			ArrayList<Integer> indices = Handler.findNAME(name);
			int index = Action.options(indices, "name", name);
			if (index == -1)
				return;

			try {
				if (index == indices.size())
					Action.finalize("Quit remove menu", true);

				else if (Handler.removeCustomer(indices.get(index)))
					Action.finalize("Customer found and removed", true);
			}

			catch (Exception e) {
				States.error("That is not a valid action");
			}
		}
	}

	public static class Sin extends Action {
		public Sin() {
			super("Remove a customer using SIN");
		}

		@Override
		public void execute() {
			System.out.print(Menu.SCENE_DIVIDER + "\nEnter SIN of customer to remove: ");
			int sin = Integer.parseInt(Common.input.nextLine());

			System.out.println(Menu.SCENE_DIVIDER + "\nSearching for customer...");

			if (Handler.removeCustomer(Handler.findSIN(sin)))
				Action.finalize("Customer found and removed", true);
		}
	}

	public Remove() {
		super("Remove a customer from the database");
	}

	@Override
	public void execute() {
		Bank.setState(States.REMOVE);
	}
}