package java8.groupExercise1.menu.option;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Options {
    Option[] value() default {};
}
