package java8.groupExercise2.menu;

import java8.groupExercise2.menu.option.Option;
import java8.groupExercise2.model.Employee;
import java8.groupExercise2.model.UserSelectionState;
import java8.groupExercise2.service.EmployeeService;
import java8.groupExercise2.serviceimpl.EmployeeServiceImpl;
import java8.groupExercise2.util.EmployeeListEncodedOutputGenerator;
import java8.groupExercise2.util.EmployeeListOutputGenerator;
import java8.groupExercise2.util.InputHelper;
import java8.groupExercise2.util.SortEnum;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java8.groupExercise2.util.Constants.*;

@Option(label = OPTION_EXPORT_AS_ENCODED, key = 1)
@Option(label = OPTION_EXPORT_AS_NOT_ENCODED, key = 2)
public class ExportToFileAction implements CommandAction{

    private final Map<Integer, BiConsumer<Path, List<Employee>>> EXPORTER_MAP = new HashMap<>();
    {
      EXPORTER_MAP.put(1, this::exportAsEncoded);
      EXPORTER_MAP.put(2, this::exportAsNonEncoded);
    }
    private final BiFunction<List<Employee>, Boolean, String> employeeListOutputGenerator =
            new EmployeeListOutputGenerator();

    private final Function<List<Employee>, String> employeeListEncodedOutputGenerator =
            new EmployeeListEncodedOutputGenerator();

    private final EmployeeService employeeService = new EmployeeServiceImpl();

    private final UserSelectionState userSelectionState;
    public ExportToFileAction(UserSelectionState userSelectionState) {
        this.userSelectionState = userSelectionState;
    }

    @Override
    public void doAction() {
        List<Employee> employeeList = employeeService.getAll(SortEnum.defaultSort().getComparator());

        String fileName = InputHelper.askUserToProvideInput(INSTRUCTION_ENTER_FILENAME);
        Path path = Paths.get(fileName);

        int selectedOption = InputHelper.askUserToSelect(this.getClass(), OPTION_HEADER_EXPORT_OPTIONS, INSTRUCTION_ENTER_ACTION_TYPE);

        EXPORTER_MAP.get(selectedOption).accept(path, employeeList);
        userSelectionState.getPreviousCommandAction().pop().doAction();
    }

    private void exportAsNonEncoded(Path path, List<Employee> employeeList) {
        try{
            if (Files.exists(path)) {
                Files.write(
                        path,
                        employeeListOutputGenerator.apply(employeeList, false).getBytes(),
                        StandardOpenOption.APPEND);
            }else {
                Files.write(path, employeeListOutputGenerator.apply(employeeList, true).getBytes());
            }
            System.out.printf("Records exported successfully to file %s. \n\n", path);
        }catch (Exception e){
            System.out.println("Error encountered exporting to a file.");
        }
    }

    private void exportAsEncoded(Path path, List<Employee> employeeList){
        try {
            String encodedEmployeeDetails = employeeListEncodedOutputGenerator.apply(employeeList);

            if (Files.exists(path)){
                Files.write(
                        path,
                        String.format("\n%s", encodedEmployeeDetails).getBytes(),
                        StandardOpenOption.APPEND);
            } else {
                Files.write(path, encodedEmployeeDetails.getBytes());
            }
            System.out.printf("Records exported successfully to file %s. \n\n", path);
        }catch (Exception e){
            System.out.println("Error encountered exporting to a file.");
        }

    }
}
