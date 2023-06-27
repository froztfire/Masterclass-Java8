package java8.groupExercise2.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Employee {
    private int employeeNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate hiringDate;
}
