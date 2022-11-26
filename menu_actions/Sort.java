package menu_actions;

import database.Action;
import database.Bank;
import database.Handler;
import user_interface.States;

public class Sort extends Action {
	public static class Name extends Action {
		public Name() {
			super("Sort the customers by name");
		}

		@Override
		public void execute() {
			Handler.sortNAME();
			Action.finalize("Customers sorted", true);
		}
	}

	public static class Sin extends Action {
		public Sin() {
			super("Sort the customers by SIN");
		}

		@Override
		public void execute() {
			Handler.sortSIN();
			Action.finalize("Customers sorted", true);
		}
	}

	public Sort() {
		super("Sort the customers");
	}

	@Override
	public void execute() {
		Bank.setState(States.SORT);
	}
}