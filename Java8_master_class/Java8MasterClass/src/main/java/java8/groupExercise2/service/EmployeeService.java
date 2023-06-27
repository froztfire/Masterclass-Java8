package java8.groupExercise2.service;

import java8.groupExercise2.model.Employee;
import java8.groupExercise2.util.SortEnum;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    int addEmployee(Employee employee);
    Optional<Employee> getEmployeeByEmployeeNumber(int employeeNumber);
    List<Employee> getEmployeeByFirstName(String firstName, SortEnum sortEnum);
    List<Employee> getEmployeeByLastName(String lastName, SortEnum sortEnum);
    List<Employee> getEmployeeByMiddleName(String middleName, SortEnum sortEnum);
    List<Employee> getEmployeeByHiringDate(String hiringDate, SortEnum sortEnum);
    List<Employee> getAll(Comparator<Employee> employeeComparator);
    Optional<Employee> deleteEmployeeByEmployeeNumber(int employeeNumber);
    List<Employee> getEmployeeByName(String name, SortEnum sortEnum);
    void addAllEmployee(List<Employee> employee);
}
