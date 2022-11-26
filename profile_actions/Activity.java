package profile_actions;

import database.Action;
import database.Bank;
import database.Handler;
import user_interface.Menu;

public class Activity extends Action {
	public Activity() {
		super("View account activity");
	}

	@Override
	public void execute() {
		Handler.displayBalance();
		Menu.displayHeader("Transaction History: ");
		String[] activity = Bank.getCurrentProfile().getActivity();

		for (int i = 4; i >= 0; i--)
			if (activity[i] != null)
				System.out.println(activity[i]);
	}
}