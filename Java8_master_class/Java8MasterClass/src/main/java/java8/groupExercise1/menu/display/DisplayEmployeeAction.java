package java8.groupExercise1.menu.display;

import java8.groupExercise1.menu.CommandAction;
import java8.groupExercise1.model.Employee;

import java.util.List;

import static java8.groupExercise1.util.Constants.*;

public class DisplayEmployeeAction implements CommandAction {

    private final List<Employee> employees;

    public DisplayEmployeeAction(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public void doAction() {
        System.out.println(DISPLAY_LINE);
        System.out.printf(
                DISPLAY_TABLE_HEADER_FORMAT,
                DISPLAY_TABLE_HEADER_EMPLOYEE_NUMBER,
                DISPLAY_TABLE_HEADER_NAME,
                DISPLAY_TABLE_HEADER_HIRING_DATE);
        System.out.println(DISPLAY_LINE);

        if (employees.isEmpty()) {
            displayNoRecordsFound();
        } else {
            employees.forEach(this::displayEmployee);
        }
        System.out.println(DISPLAY_LINE);
    }

    private void displayEmployee(Employee employee) {
        System.out.printf(
                DISPLAY_TABLE_BODY_FORMAT,
                employee.getEmployeeNumber(),
                String.format(
                        DISPLAY_EMPLOYEE_FULL_NAME_FORMAT,
                        employee.getFirstName(),
                        employee.getMiddleName(),
                        employee.getLastName()),
                employee.getHiringDate());
    }

    private void displayNoRecordsFound() {
        System.out.printf(DISPLAY_NO_RECORDS_FOUND_FORMAT, DISPLAY_NO_RECORDS_FOUND, EMPTY_STRING);
    }
}
