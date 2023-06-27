package java8.groupExercise2.util;

import java8.groupExercise2.model.Employee;
import java8.groupExercise2.service.EmployeeService;
import java8.groupExercise2.serviceimpl.EmployeeServiceImpl;

import java.time.LocalDate;

public class TestDataUtil {
    public static void generateTestData(){
        EmployeeService employeeService = new EmployeeServiceImpl();

        Employee emp1 = Employee.builder()
                .employeeNumber(1234567)
                .firstName("Jerome")
                .middleName("A")
                .lastName("Garcia")
                .hiringDate(LocalDate.of(2022, 05,22))
                .build();

        Employee emp2 = Employee.builder()
                .employeeNumber(2341527)
                .firstName("Jhacell")
                .middleName("C")
                .lastName("Vasquez")
                .hiringDate(LocalDate.of(2021, 01,20))
                .build();

        Employee emp3 = Employee.builder()
                .employeeNumber(3142654)
                .firstName("Sancho James")
                .middleName("A")
                .lastName("Garcia")
                .hiringDate(LocalDate.of(2020, 04,27))
                .build();

        employeeService.addEmployee(emp1);
        employeeService.addEmployee(emp2);
        employeeService.addEmployee(emp3);
    }
}
