package java8.groupExercise1.menu;

import java8.groupExercise1.model.Employee;
import java8.groupExercise1.model.UserSelectionState;
import java8.groupExercise1.service.EmployeeService;
import java8.groupExercise1.serviceimpl.util.EmployeeServiceImpl;
import java8.groupExercise1.util.InputHelper;

import java.util.Optional;

import static java8.groupExercise1.util.Constants.MESSAGE_DELETED_EMPLOYEE;
import static java8.groupExercise1.util.Constants.MESSAGE_EMPLOYEE_NOT_FOUND;

public class DeleteEmployeeAction implements CommandAction {
    private EmployeeService employeeService = new EmployeeServiceImpl();
    private final UserSelectionState userSelectionState;

    public DeleteEmployeeAction(UserSelectionState userSelectionState) {
        this.userSelectionState = userSelectionState;
    }

    @Override
    public void doAction() {
        int employeeNumber = InputHelper.askUserToProvideEmployeeNumber();

        Optional<Employee> optionalEmployee =
                employeeService.getEmployeeByEmployeeNumber(employeeNumber);

        if (optionalEmployee.isPresent()) {
            employeeService.deleteEmployeeByEmployeeNumber(employeeNumber);

            Employee deletedEmployee = optionalEmployee.get();
            System.out.println(
                    String.format(
                            MESSAGE_DELETED_EMPLOYEE,
                            deletedEmployee.getEmployeeNumber(),
                            deletedEmployee.getFirstName(),
                            deletedEmployee.getMiddleName(),
                            deletedEmployee.getLastName(),
                            deletedEmployee.getHiringDate()));

        } else {
            System.out.println(String.format(MESSAGE_EMPLOYEE_NOT_FOUND, employeeNumber));
        }

        userSelectionState.getPreviousCommandAction().pop().doAction();
    }
}
