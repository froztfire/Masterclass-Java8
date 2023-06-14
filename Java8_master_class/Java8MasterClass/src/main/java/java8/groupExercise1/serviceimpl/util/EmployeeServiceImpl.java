package java8.groupExercise1.serviceimpl.util;

import java8.groupExercise1.model.Employee;
import java8.groupExercise1.repository.EmployeeRepository;
import java8.groupExercise1.service.EmployeeService;
import java8.groupExercise1.util.SortEnum;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public int addEmployee(Employee employee) {
        return EmployeeRepository.addEmployee(employee);
    }

    @Override
    public Optional<Employee> getEmployeeByEmployeeNumber(int employeeNumber) {
        return EmployeeRepository.getEmployees()
                .stream()
                .filter(e -> e.getEmployeeNumber() == employeeNumber)
                .findFirst();
    }

    @Override
    public List<Employee> getEmployeeByFirstName(String firstName, SortEnum sortEnum) {
        return getMatchedEmployees(e -> e.getFirstName().equalsIgnoreCase(firstName), sortEnum);
    }

    @Override
    public List<Employee> getEmployeeByLastName(String lastName, SortEnum sortEnum) {
        return getMatchedEmployees(e -> e.getLastName().equalsIgnoreCase(lastName), sortEnum);
    }

    @Override
    public List<Employee> getEmployeeByMiddleName(String middleName, SortEnum sortEnum) {
        return getMatchedEmployees(e -> e.getMiddleName().equalsIgnoreCase(middleName),sortEnum);
    }

    @Override
    public List<Employee> getEmployeeByHiringDate(String hiringDate, SortEnum sortEnum) {
        return getMatchedEmployees(e -> e.getHiringDate().equalsIgnoreCase(hiringDate), sortEnum);
    }

    @Override
    public List<Employee> getAll(SortEnum sortEnum) {
        return EmployeeRepository.getEmployees()
                .stream()
                .sorted(sortEnum.getComparator())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> deleteEmployeeByEmployeeNumber(int employeeNumber) {
        EmployeeRepository.setEmployees(
                getMatchedEmployees(e -> e.getEmployeeNumber() != employeeNumber, SortEnum.defaultSort())
        );
        return Optional.empty();
    }

    private List<Employee> getMatchedEmployees(
            Predicate<Employee> employeePredicate, SortEnum sortEnum) {

        return EmployeeRepository.getEmployees()
                .stream()
                .filter(employeePredicate)
                .sorted(sortEnum.getComparator())
                .collect(Collectors.toList());
    }
}
