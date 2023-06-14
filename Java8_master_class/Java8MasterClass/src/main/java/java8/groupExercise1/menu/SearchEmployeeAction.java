package java8.groupExercise1.menu;

import java8.groupExercise1.menu.display.DisplayEmployeeAction;
import java8.groupExercise1.menu.option.Option;
import java8.groupExercise1.model.Employee;
import java8.groupExercise1.model.UserSelectionState;
import java8.groupExercise1.service.EmployeeService;
import java8.groupExercise1.serviceimpl.util.EmployeeServiceImpl;
import java8.groupExercise1.util.InputHelper;
import java8.groupExercise1.util.SortEnum;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static java8.groupExercise1.util.Constants.*;

@Option(label = OPTION_SEARCH_BY_EMPLOYEE_NUMBER, key = 1)
@Option(label = OPTION_SEARCH_BY_FIRST_NAME, key = 2)
@Option(label = OPTION_SEARCH_BY_MIDDLE_NAME, key = 3)
@Option(label = OPTION_SEARCH_BY_LAST_NAME, key = 4)
@Option(label = OPTION_SEARCH_BY_HIRING_DATE, key = 5)
@Option(label = OPTION_BACK, key = -1)
public class SearchEmployeeAction implements CommandAction {
    private final Function<List<Employee>, CommandAction> displayEmployeesActionFunction =
            DisplayEmployeeAction::new;

    private final Map<Integer, Supplier<List<Employee>>> searchesMap;

    {
        searchesMap = new HashMap<>();
        searchesMap.put(1, this::searchByEmployeeNumber);
        searchesMap.put(2, this::searchByFirstName);
        searchesMap.put(3, this::searchByMiddleName);
        searchesMap.put(4, this::searchByLastName);
        searchesMap.put(5, this::searchByHiringDate);
    }

    private EmployeeService employeeService = new EmployeeServiceImpl();
    private final UserSelectionState userSelectionState;

    public SearchEmployeeAction(UserSelectionState userSelectionState) {
        this.userSelectionState = userSelectionState;
    }


    @Override
    public void doAction() {
        int selectedOption =
                InputHelper.askUserToSelect(
                        this.getClass(), OPTION_HEADER_CHOOSE_AN_ACTION, INSTRUCTION_SELECT_AN_ACTION);

        if (selectedOption == -1) {
            userSelectionState.getPreviousCommandAction().pop().doAction();
            return;
        }

        userSelectionState.getPreviousCommandAction().add(this);
        Supplier<List<Employee>> searchSupplier = searchesMap.get(selectedOption);

        displayEmployeesActionFunction.apply(searchSupplier.get()).doAction();

        userSelectionState.getPreviousCommandAction().pop().doAction();
    }

    private List<Employee> searchByEmployeeNumber() {
        int employeeNumber = InputHelper.askUserToProvideEmployeeNumber();

        Optional<Employee> employeeOptional =
                employeeService.getEmployeeByEmployeeNumber(employeeNumber);

        return employeeOptional.isPresent()
                ? Arrays.asList(employeeOptional.get())
                : Collections.emptyList();
    }

    private List<Employee> searchByFirstName() {
        String firstName = InputHelper.askUserToProvideInput(INSTRUCTION_ENTER_FIRST_NAME);

        return employeeService.getEmployeeByFirstName(firstName, SortEnum.defaultSort());
    }

    private List<Employee> searchByLastName() {
        String lastName = InputHelper.askUserToProvideInput(INSTRUCTION_ENTER_LAST_NAME);

        return employeeService.getEmployeeByLastName(lastName, SortEnum.defaultSort());
    }

    private List<Employee> searchByMiddleName() {
        String middleName = InputHelper.askUserToProvideInput(INSTRUCTION_ENTER_MIDDLE_NAME);

        return employeeService.getEmployeeByMiddleName(middleName, SortEnum.defaultSort());
    }

    private List<Employee> searchByHiringDate() {
        String hiringDate = InputHelper.askUserToProvideInput(INSTRUCTION_ENTER_HIRING_DATE);

        return employeeService.getEmployeeByHiringDate(hiringDate, SortEnum.defaultSort());
    }
}
