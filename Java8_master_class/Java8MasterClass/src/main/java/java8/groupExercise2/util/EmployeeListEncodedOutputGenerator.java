package java8.groupExercise2.util;

import java8.groupExercise2.model.Employee;

import java.util.Base64;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java8.groupExercise2.util.Constants.DISPLAY_EMPLOYEE_FULL_NAME_FORMAT;
import static java8.groupExercise2.util.Constants.DISPLAY_TABLE_BODY_FORMAT;

public class EmployeeListEncodedOutputGenerator implements Function<List<Employee>, String> {
    @Override
    public String apply(List<Employee> employeeList) {
        return employeeList
                .stream()
                .map(this::generateEmployeeDetails)
                .map(String::getBytes)
                .map(Base64.getEncoder()::encodeToString)
                .collect(Collectors.joining("\n"));
    }

    private String generateEmployeeDetails(Employee employee){
        return String.format(
                DISPLAY_TABLE_BODY_FORMAT,
                employee.getEmployeeNumber(),
                String.format(
                        DISPLAY_EMPLOYEE_FULL_NAME_FORMAT,
                        employee.getFirstName(),
                        employee.getMiddleName(),
                        employee.getLastName()),
                employee.getHiringDate().format(DateTimeFormatterEnum.MMMsDDDscYYYY.getDateTimeFormatter()));
    }
}
