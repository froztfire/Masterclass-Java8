package java8.groupExercise1.menu;

import java8.groupExercise1.menu.display.DisplayNewlyAddedEmployeeAction;
import java8.groupExercise1.model.Employee;
import java8.groupExercise1.model.UserSelectionState;
import java8.groupExercise1.service.EmployeeService;
import java8.groupExercise1.serviceimpl.util.EmployeeServiceImpl;
import java8.groupExercise1.util.InputHelper;

import java.util.function.Function;

import static java8.groupExercise1.util.Constants.*;

public class AddNewEmployeeAction implements CommandAction {
    private final Function<Employee, CommandAction> displayNewlyAddedEmployeeActionFunction =
            DisplayNewlyAddedEmployeeAction::new;
    private EmployeeService employeeService = new EmployeeServiceImpl();

    private final UserSelectionState userSelectionState;

    public AddNewEmployeeAction(UserSelectionState userSelectionState) {
        this.userSelectionState = userSelectionState;
    }

    @Override
    public void doAction() {
        int employeeNumber = InputHelper.askUserToProvideEmployeeNumber();
        String firstName = InputHelper.askUserToProvideInput(INSTRUCTION_FIRST_NAME);
        String lastName = InputHelper.askUserToProvideInput(INSTRUCTION_LAST_NAME);
        String middleName = InputHelper.askUserToProvideInput(INSTRUCTION_MIDDLE_NAME);
        String hiringDate = InputHelper.askUserToProvideInput(INSTRUCTION_HIRING_DATE);

        Employee employee =
                Employee.builder()
                        .employeeNumber(employeeNumber)
                        .firstName(firstName)
                        .lastName(lastName)
                        .middleName(middleName)
                        .hiringDate(hiringDate)
                        .build();

        employeeService.addEmployee(employee);
        displayNewlyAddedEmployeeActionFunction.apply(employee).doAction();
        userSelectionState.getPreviousCommandAction().pop().doAction();
    }
}
