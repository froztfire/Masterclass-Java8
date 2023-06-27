package java8.groupExercise2.menu;

import java8.groupExercise2.menu.display.DisplayEmployeeAction;
import java8.groupExercise2.model.Employee;
import java8.groupExercise2.model.UserSelectionState;
import java8.groupExercise2.service.EmployeeService;
import java8.groupExercise2.serviceimpl.EmployeeServiceImpl;

import java.util.List;
import java.util.function.Function;

public class ListAllEmployeeAction implements CommandAction{

    private final Function<List<Employee>, CommandAction> displayEmployeesActionFunction =
            DisplayEmployeeAction::new;

    private EmployeeService employeeService = new EmployeeServiceImpl();

    private CommandAction sortMenu;
    private final UserSelectionState userSelectionState;
    public ListAllEmployeeAction(UserSelectionState userSelectionState) {
        this.userSelectionState = userSelectionState;
        sortMenu = new SortMenuAction(userSelectionState);
    }

    @Override
    public void doAction() {
        sortMenu.doAction();
        List<Employee> employees = employeeService.getAll(userSelectionState.getSortEnum().getComparator());
        displayEmployeesActionFunction.apply(employees).doAction();
        userSelectionState.getPreviousCommandAction().pop().doAction();
    }
}
