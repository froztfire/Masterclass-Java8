package java8.groupExercise1;

import java8.groupExercise1.menu.CommandAction;
import java8.groupExercise1.menu.MainMenuAction;
import java8.groupExercise1.model.UserSelectionState;
import java8.groupExercise1.util.TestDataUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeDirectory {
    public static void main(String[] args) {
        TestDataUtil.generateTestData();
        CommandAction mainMenuAction = new MainMenuAction(new UserSelectionState());
        mainMenuAction.doAction();
    }
}
