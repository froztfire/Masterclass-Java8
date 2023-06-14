package java8.groupExercise1.menu.option;

import java.lang.annotation.Repeatable;

@Repeatable(value = Options.class)
public @interface Option {
    String label();
    int key();
}
