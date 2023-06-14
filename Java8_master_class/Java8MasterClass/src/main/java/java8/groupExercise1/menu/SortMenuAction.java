package java8.groupExercise1.menu;


import java8.groupExercise1.menu.option.Option;
import java8.groupExercise1.model.UserSelectionState;
import java8.groupExercise1.util.InputHelper;
import java8.groupExercise1.util.SortEnum;

import java.util.HashMap;
import java.util.Map;

import static java8.groupExercise1.util.Constants.*;

@Option(label = OPTION_SORT_BY_EMPLOYEE_NUMBER, key = 1)
@Option(label = OPTION_SORT_BY_FIRST_NAME, key = 2)
@Option(label = OPTION_SORT_BY_LAST_NAME, key = 3)
@Option(label = OPTION_SORT_BY_HIRING_DATE, key = 4)
@Option(label = OPTION_BACK, key = -1)


public class SortMenuAction  implements CommandAction {

    private static final Map<Integer, SortEnum> SORT_ENUMS_MAP;

    static {
        SORT_ENUMS_MAP = new HashMap<>();
        SORT_ENUMS_MAP.put(1, SortEnum.BY_EMPLOYEE_NUMBER);
        SORT_ENUMS_MAP.put(2, SortEnum.BY_FIRST_NAME);
        SORT_ENUMS_MAP.put(3, SortEnum.BY_LAST_NAME);
        SORT_ENUMS_MAP.put(4, SortEnum.BY_HIRING_DATE);
    }

    private final UserSelectionState userSelectionState;

    public SortMenuAction(UserSelectionState userSelectionState) {
        this.userSelectionState = userSelectionState;
    }
    @Override
    public void doAction() {
        int selectedOption =
                InputHelper.askUserToSelect(
                        this.getClass(), OPTION_HEADER_CHOOSE_AN_ACTION, INSTRUCTION_SELECT_AN_ACTION);

        if (selectedOption == -1) {
            userSelectionState.getPreviousCommandAction().pop().doAction();
            return;
        }

        SortEnum sortEnum = SORT_ENUMS_MAP.get(selectedOption);
        userSelectionState.setSortEnum(sortEnum);
    }
}
