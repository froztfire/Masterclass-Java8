package java8.groupExercise2.menu;

import java8.groupExercise2.model.Employee;
import java8.groupExercise2.model.UserSelectionState;
import java8.groupExercise2.service.EmployeeService;
import java8.groupExercise2.serviceimpl.EmployeeServiceImpl;
import java8.groupExercise2.util.InputHelper;

import java.util.Optional;

import static java8.groupExercise2.util.Constants.MESSAGE_DELETED_EMPLOYEE;
import static java8.groupExercise2.util.Constants.MESSAGE_EMPLOYEE_NOT_FOUND;

public class DeleteEmployeeAction implements CommandAction{

    private final UserSelectionState userSelectionState;
    private EmployeeService employeeService = new EmployeeServiceImpl();

    public DeleteEmployeeAction(UserSelectionState userSelectionState) {
        this.userSelectionState = userSelectionState;
    }

    @Override
    public void doAction() {
    int employeeNumber = InputHelper.askUserToProvideEmployeeNumber();
        Optional<Employee> employeeOptional = employeeService.getEmployeeByEmployeeNumber(employeeNumber);
        if (employeeOptional.isPresent()){
            employeeService.deleteEmployeeByEmployeeNumber(employeeNumber);
            Employee deletedEmployee = employeeOptional.get();
            System.out.printf(String.format(
                    MESSAGE_DELETED_EMPLOYEE,
                    deletedEmployee.getEmployeeNumber(),
                    deletedEmployee.getFirstName(),
                    deletedEmployee.getMiddleName(),
                    deletedEmployee.getLastName(),
                    deletedEmployee.getHiringDate()
            ));
        }else {
            System.out.println(String.format(MESSAGE_EMPLOYEE_NOT_FOUND,employeeNumber));
        }
        userSelectionState.getPreviousCommandAction().pop().doAction();
    }
}
