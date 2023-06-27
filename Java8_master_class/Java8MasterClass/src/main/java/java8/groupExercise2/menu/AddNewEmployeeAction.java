package java8.groupExercise2.menu;

import java8.groupExercise2.menu.display.DisplayNewlyAddedEmployeeAction;
import java8.groupExercise2.model.Employee;
import java8.groupExercise2.model.UserSelectionState;
import java8.groupExercise2.service.EmployeeService;
import java8.groupExercise2.serviceimpl.EmployeeServiceImpl;
import java8.groupExercise2.util.InputHelper;

import java.time.LocalDate;
import java.util.function.Function;

import static java8.groupExercise2.util.Constants.*;

public class AddNewEmployeeAction implements CommandAction{

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
    String middleName = InputHelper.askUserToProvideInput(INSTRUCTION_MIDDLE_NAME);
    String lastName = InputHelper.askUserToProvideInput(INSTRUCTION_LAST_NAME);
    LocalDate hiringDate = InputHelper.askUserToProvideHiringDate(INSTRUCTION_HIRING_DATE);

    Employee employee = Employee.builder()
            .employeeNumber(employeeNumber)
            .firstName(firstName)
            .middleName(middleName)
            .lastName(lastName)
            .hiringDate(hiringDate)
            .build();

    employeeService.addEmployee(employee);
    displayNewlyAddedEmployeeActionFunction.apply(employee).doAction();
    userSelectionState.getPreviousCommandAction().pop().doAction();
    }
}
