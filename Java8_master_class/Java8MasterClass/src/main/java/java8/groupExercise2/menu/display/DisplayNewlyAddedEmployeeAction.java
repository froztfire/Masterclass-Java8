package java8.groupExercise2.menu.display;

import java8.groupExercise2.menu.CommandAction;
import java8.groupExercise2.model.Employee;

import java.time.LocalDateTime;

import static java8.groupExercise2.util.Constants.MESSAGE_EMPLOYEE_SUCCESSFULLY_ADDED;

public class DisplayNewlyAddedEmployeeAction implements CommandAction {

    private final Employee employee;

    public DisplayNewlyAddedEmployeeAction(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void doAction() {
        System.out.println(MESSAGE_EMPLOYEE_SUCCESSFULLY_ADDED + LocalDateTime.now());
        System.out.println("Number: " + employee.getEmployeeNumber());
        System.out.println(
                "Name: "
                        + employee.getFirstName()
                        + " "
                        + employee.getMiddleName()
                        + " "
                        + employee.getLastName());
        System.out.println("Date Hired: " + employee.getHiringDate() + "\n");
    }
}
