package java8.groupExercise2.menu.option;

import java.lang.annotation.Repeatable;

@Repeatable(value = Options.class)
public @interface Option {
    String label();
    int key();

}
