package java8.groupExercise2.menu;

import java8.groupExercise2.menu.display.DisplayEmployeeAction;
import java8.groupExercise2.menu.option.Option;
import java8.groupExercise2.model.Employee;
import java8.groupExercise2.model.UserSelectionState;
import java8.groupExercise2.repository.EmployeeRepository;
import java8.groupExercise2.service.EmployeeService;
import java8.groupExercise2.serviceimpl.EmployeeServiceImpl;
import java8.groupExercise2.util.InputHelper;
import java8.groupExercise2.util.SortEnum;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static java8.groupExercise2.util.Constants.*;

@Option(label = OPTION_SEARCH_BY_EMPLOYEE_NUMBER, key = 1)
//@Option(label = OPTION_SEARCH_BY_FIRST_NAME, key = 2)
//@Option(label = OPTION_SEARCH_BY_MIDDLE_NAME, key = 3)
//@Option(label = OPTION_SEARCH_BY_LAST_NAME, key = 4)
@Option(label = OPTION_SEARCH_BY_FULL_NAME, key = 2)
@Option(label = OPTION_SEARCH_BY_HIRING_DATE, key = 3)
@Option(label = OPTION_BACK, key = -1)

public class SearchEmployeeAction implements CommandAction{

    private EmployeeService employeeService = new EmployeeServiceImpl();

    private final Function<List<Employee>, CommandAction> displayEmployeeActionFunction =
            DisplayEmployeeAction::new;

    private final Map<Integer, Supplier<List<Employee>>> searchMap = new HashMap<>();

    {
        searchMap.put(1, this::searchByEmployeeNumber);
//        searchMap.put(2, this::searchByFirstName);
//        searchMap.put(3, this::searchByMiddleName);
//        searchMap.put(4, this::searchByLastName);
        searchMap.put(2, this::searchEmployeeByName);
        searchMap.put(3, this::searchByHiringDate);
    }

    private final UserSelectionState userSelectionState;

    public SearchEmployeeAction(UserSelectionState userSelectionState) {
        this.userSelectionState = userSelectionState;
    }
    @Override
    public void doAction() {
        int selectedOption = InputHelper.askUserToSelect(
                this.getClass(), OPTION_HEADER_CHOOSE_AN_ACTION, INSTRUCTION_SELECT_AN_ACTION);
        if (selectedOption == -1) {
            userSelectionState.getPreviousCommandAction().pop().doAction();
            return;
        }
        userSelectionState.getPreviousCommandAction().add(this);
        Supplier<List<Employee>> searchSupplier = searchMap.get(selectedOption);
        displayEmployeeActionFunction.apply(searchSupplier.get()).doAction();
        userSelectionState.getPreviousCommandAction().pop().doAction();
    }

    private List<Employee> searchByEmployeeNumber() {
        int employeeNumber = InputHelper.askUserToProvideEmployeeNumber();
        Optional<Employee> employeeOptional = employeeService.getEmployeeByEmployeeNumber(employeeNumber);
        return employeeOptional.isPresent() ? Arrays.asList(employeeOptional.get()) : Collections.emptyList();
    }
    private List<Employee> searchByFirstName() {
        String firstName = InputHelper.askUserToProvideInput(INSTRUCTION_FIRST_NAME);
        return employeeService.getEmployeeByFirstName(firstName, SortEnum.defaultSort());
    }
    private List<Employee> searchByMiddleName() {
        String middleName = InputHelper.askUserToProvideInput(INSTRUCTION_MIDDLE_NAME);
        return employeeService.getEmployeeByMiddleName(middleName, SortEnum.defaultSort());
    }
    private List<Employee> searchByLastName() {
        String lastName = InputHelper.askUserToProvideInput(INSTRUCTION_LAST_NAME);
        return employeeService.getEmployeeByLastName(lastName, SortEnum.defaultSort());
    }
    private List<Employee> searchByHiringDate() {
        String hiringDate = InputHelper.askUserToProvideInput(INSTRUCTION_ENTER_HIRING_DATE);
        return employeeService.getEmployeeByHiringDate(hiringDate, SortEnum.BY_HIRING_DATE);
    }

    private List<Employee> searchEmployeeByName() {
        String name = InputHelper.askUserToProvideInput(INSTRUCTION_ENTER_FULL_NAME);
        return employeeService.getEmployeeByName(name,SortEnum.defaultSort());
    }
}
