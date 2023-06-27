package java8.groupExercise2.menu;

import java8.groupExercise2.menu.option.Option;
import java8.groupExercise2.model.UserSelectionState;
import java8.groupExercise2.util.InputHelper;

import java.util.HashMap;
import java.util.Map;

import static java8.groupExercise2.util.Constants.*;
@Option(label = OPTION_LIST_ALL_EMPLOYEE_RECORDS, key = 1)
@Option(label = OPTION_ADD_NEW_EMPLOYEE_RECORD, key = 2)
@Option(label = OPTION_DELETE_EMPLOYEE_RECORD, key = 3)
@Option(label = OPTION_SEARCH_EMPLOYEE_RECORD, key = 4)
@Option(label = OPTION_READ_FROM_FILE, key = 5)
@Option(label = OPTION_EXPORT_TO_FILE, key = 6)
@Option(label = OPTION_EXIT, key = -1)

public class MainMenuAction implements CommandAction{
    private Map<Integer, CommandAction> commandActionMap;
    private final UserSelectionState userSelectionState;
    public MainMenuAction(UserSelectionState userSelectionState) {
        this.userSelectionState = userSelectionState;

        commandActionMap = new HashMap<>();
        commandActionMap.put(1, new ListAllEmployeeAction(userSelectionState));
        commandActionMap.put(2, new AddNewEmployeeAction(userSelectionState));
        commandActionMap.put(3, new DeleteEmployeeAction(userSelectionState));
        commandActionMap.put(4, new SearchEmployeeAction(userSelectionState));
        commandActionMap.put(5, new ReadFromFileAction(userSelectionState));
        commandActionMap.put(6, new ExportToFileAction(userSelectionState));
        commandActionMap.put(-1, new ExitAction());
    }

    @Override
    public void doAction() {
        userSelectionState.getPreviousCommandAction().add(this);

        int selectedOption =
                InputHelper.askUserToSelect(
                this.getClass(), OPTION_HEADER_MAIN_OPTIONS, INSTRUCTION_ENTER_ACTION_TYPE);
        CommandAction commandAction = commandActionMap.get(selectedOption);
        commandAction.doAction();
    }
}
