package java8.groupExercise1.menu;

import static java8.groupExercise1.util.Constants.MESSAGE_EXIT;

public class ExitAction implements CommandAction {
    @Override
    public void doAction() {
        System.out.println(MESSAGE_EXIT);
        System.exit(0);
    }
}
