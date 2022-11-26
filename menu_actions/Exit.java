package menu_actions;

import database.Action;
import database.Handler;
import user_interface.Menu;

public class Exit extends Action {
	public Exit() {
		super("Save customer data, then close the program");
	}

	@Override
	public void execute() {
		Action.finalize("Data saved", false);
		System.out.println(Menu.SCENE_DIVIDER + "\nProgram closed successfully");

		Handler.saveData();
		System.exit(0);
	}
}
