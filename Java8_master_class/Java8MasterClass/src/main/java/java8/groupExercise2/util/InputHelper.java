package java8.groupExercise2.util;

import java8.groupExercise2.exception.FutureDateIsNotAllowedException;
import java8.groupExercise2.menu.option.Option;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class InputHelper {

    public static int askUserToSelect(Class<?> clazz, String optionHeader, String instruction) {
        Option[] options = clazz.getAnnotationsByType(Option.class);

        Optional<Integer> optionalSelectedOption;
        do {
            optionalSelectedOption = getSelectedOption(options, optionHeader, instruction);
        } while (!optionalSelectedOption.isPresent());
        return optionalSelectedOption.get();
    }

    private static Optional<Integer> getSelectedOption(
            Option[] options, String optionHeader, String instruction) {
        System.out.println(optionHeader);

        Arrays.stream(options).forEach(o -> System.out.println(String.format("[%d] %s", o.key(), o.label())));
        System.out.print("\n" + instruction);

        Scanner scanner = new Scanner(System.in);

        Optional<Integer> optionalSelectedOption = Optional.empty();

        try{
            int selectedOption = scanner.nextInt();
            if (!isSelectedOptionValid(options, selectedOption)){
                throw new IllegalArgumentException("Selected Option is not valid");
            }
            optionalSelectedOption = Optional.of(selectedOption);
        }catch (Exception e) {
            System.out.println("Invalid entry. Try again. \n");
        }
        return optionalSelectedOption;
    }

    private static boolean isSelectedOptionValid(Option[] options, int selectedOption){
        return Arrays.stream(options).anyMatch(o-> o.key() == selectedOption);
    }

    public static int askUserToProvideEmployeeNumber(){
        Optional<Integer> optionalEmployeeNumber;
        do {
            optionalEmployeeNumber = getEmployeeNumber();
        }while (!optionalEmployeeNumber.isPresent());
        return optionalEmployeeNumber.get();
    }

    private static Optional<Integer> getEmployeeNumber() {
        Optional<Integer> optionalEmployeeNumber = Optional.empty();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Employee Number: ");
            int employeeNumber = scanner.nextInt();
            optionalEmployeeNumber = Optional.of(employeeNumber);
        }catch (Exception e){
            System.out.println("Invalid Entry. Try again. \n");
        }
        return optionalEmployeeNumber;
    }

    public static String askUserToProvideInput(String instruction){
        System.out.print(instruction);

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static LocalDate askUserToProvideHiringDate(String instruction){
        Optional<LocalDate> optionHiringDate = Optional.empty();
        do {
            optionHiringDate = getHiringDate(instruction);
        }while (!optionHiringDate.isPresent());

        return optionHiringDate.get();
    }

    private static Optional<LocalDate> getHiringDate(String instruction) {
        Optional<LocalDate> optionalHiringDate = Optional.empty();

        Scanner scanner = new Scanner(System.in);
        try{
            System.out.print(instruction);
            String hiringDate = scanner.nextLine();

            LocalDate hiringDateFormatted =
                    LocalDate.parse(hiringDate, DateTimeFormatterEnum.YYYYhMMhDD.getDateTimeFormatter());

            if (hiringDateFormatted.isAfter(LocalDate.now())){
                throw new FutureDateIsNotAllowedException();
            }

            optionalHiringDate = Optional.of(hiringDateFormatted);
        }catch (FutureDateIsNotAllowedException e){
            System.out.println("Invalid Date. Future date is not Allowed");
        }catch (Exception e){
            System.out.println("Invalid Date Format. Please enter in yyyy-MM-dd format");
        }

        return optionalHiringDate;
    }

}
