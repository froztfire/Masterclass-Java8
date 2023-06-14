package java8.exercise5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class NioMethods {

    public static void main(String[] args) {

        String fileName = "C:\\Users\\2194063\\Documents\\ACADEMY\\Java8_master_class\\Java8MasterClass\\src\\main\\resources\\exercise5.txt";
        List<Integer> listBounties = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(fileName)).skip(1)) {
            // Print table header

            // Process each line and display formatted output
            lines.map(line -> line.split(","))
                    .filter(data -> data.length == 3)
                    .forEach(data -> {
                        String name = data[0].trim();
                        String address = data[1].trim();
                        int bounty = Integer.parseInt(data[2].trim());
                        System.out.printf("Name: %s%n",name);
                        System.out.printf("Location: %s%n",address);
                        System.out.printf("Bounty: %d%n\n",bounty);
                        listBounties.add(bounty);
                    });

            System.out.println("Total Bounty: " + listBounties.stream().reduce(0, Math::addExact));

            System.out.println();

        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
