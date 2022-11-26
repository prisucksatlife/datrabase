package menu_actions;

import database.Action;
import database.Bank;
import user_interface.States;

public class Return extends Action {
	public Return() {
		super("Return to main menu");
	}

	@Override
	public void execute() {
		Bank.setState(States.MAIN);
	}
}
