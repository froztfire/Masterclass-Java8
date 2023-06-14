package java8.groupExercise1.model;

import java8.groupExercise1.menu.CommandAction;
import java8.groupExercise1.util.SortEnum;
import lombok.Data;

import java.util.Stack;
@Data
public class UserSelectionState {
    private Stack<CommandAction> previousCommandAction = new Stack<>();
    private SortEnum sortEnum = SortEnum.defaultSort();
    private int addedEmployeeNumber;
}
