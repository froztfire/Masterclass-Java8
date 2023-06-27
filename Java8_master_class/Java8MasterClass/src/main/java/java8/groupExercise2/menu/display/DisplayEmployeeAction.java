package java8.groupExercise2.menu.display;

import java8.groupExercise2.menu.CommandAction;
import java8.groupExercise2.model.Employee;

import java.util.List;

import static java8.groupExercise2.util.Constants.*;

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

        if (employees.isEmpty()){
            displayNoRecordFound();
        } else {
            employees.forEach(this::displayEmployee);
        }
        System.out.println(DISPLAY_LINE);
    }

    private void displayEmployee(Employee employee){
        System.out.printf(DISPLAY_TABLE_BODY_FORMAT,
                employee.getEmployeeNumber(),
                String.format(
                        DISPLAY_EMPLOYEE_FULL_NAME_FORMAT,
                        employee.getFirstName(),
                        employee.getMiddleName(),
                        employee.getLastName()),
                        employee.getHiringDate());
    }

    private void displayNoRecordFound() {
        System.out.printf(DISPLAY_NO_RECORDS_FOUND_FORMAT,DISPLAY_NO_RECORDS_FOUND,EMPTY_STRING);
    }
}
