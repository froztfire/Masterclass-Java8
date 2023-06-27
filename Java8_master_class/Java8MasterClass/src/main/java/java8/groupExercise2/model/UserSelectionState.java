package java8.groupExercise2.model;

import java8.groupExercise2.menu.CommandAction;
import java8.groupExercise2.util.SortEnum;
import lombok.Data;

import java.util.Stack;
@Data
public class UserSelectionState {
    private Stack<CommandAction> previousCommandAction = new Stack<>();
    private SortEnum sortEnum = SortEnum.defaultSort();
    private int addedEmployeeNumber;
}
