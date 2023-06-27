package java8.groupExercise2.menu;

import java8.groupExercise2.model.Employee;
import java8.groupExercise2.model.UserSelectionState;
import java8.groupExercise2.service.EmployeeService;
import java8.groupExercise2.serviceimpl.EmployeeServiceImpl;
import java8.groupExercise2.util.DateTimeFormatterEnum;
import java8.groupExercise2.util.InputHelper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java8.groupExercise2.util.Constants.*;

public class ReadFromFileAction implements CommandAction{

    private final UserSelectionState userSelectionState;

    private EmployeeService employeeService = new EmployeeServiceImpl();

    public ReadFromFileAction(UserSelectionState userSelectionState) {
        this.userSelectionState = userSelectionState;
    }

    @Override
    public void doAction() {
        Optional<List<Employee>> optionalEmployees;

        do {
            optionalEmployees = parseEmployeeFromFile();
        }while (!optionalEmployees.isPresent());

        employeeService.addAllEmployee(optionalEmployees.get());
        userSelectionState.getPreviousCommandAction().pop().doAction();
    }

    private Optional<List<Employee>> parseEmployeeFromFile() {
        Optional<List<Employee>> employeeOptional = Optional.empty();

        String fileName = InputHelper.askUserToProvideInput(INSTRUCTION_ENTER_FILENAME);

        try (Stream<String> lines = Files.lines(Paths.get(fileName))){
            List<Employee> employees = lines.skip(1).map(this::buildEmployee).collect(Collectors.toList());
            employeeOptional = Optional.of(employees);
            System.out.printf(MESSAGE_RECORDS_SUCCESSFULLY_IMPORTED_FROM_FILE, fileName);
        }catch (Exception e){
            System.out.printf(MESSAGE_ERROR_ENCOUNTERED_READING_FILE,fileName);
        }
        return employeeOptional;
    }

    private Employee buildEmployee(String line){
        String[] employeeDetails = line.split(",");
        String dateHired = line.substring(line.lastIndexOf(",") - 6).trim();

        return Employee.builder()
                .employeeNumber(Integer.parseInt(employeeDetails[0]))
                .firstName(employeeDetails[1].trim())
                .middleName(employeeDetails[2].trim())
                .lastName(employeeDetails[3].trim())
                .hiringDate(getHiringDate(dateHired))
                .build();
    }

    private LocalDate getHiringDate(String hiringDate){
        return LocalDate.parse(hiringDate, DateTimeFormatterEnum.MMMsDDDscYYYY.getDateTimeFormatter());
    }
}
