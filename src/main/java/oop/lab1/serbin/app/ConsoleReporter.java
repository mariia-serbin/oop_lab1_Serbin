package oop.lab1.serbin.app;

import java.io.PrintStream;
import java.util.List;

import oop.lab1.serbin.model.Vegetable;
import oop.lab1.serbin.service.SaladConfig;
import oop.lab1.serbin.service.SaladService;

/**
 * Formats and prints salad information and the console menu to a destination
 * stream.
 */
public class ConsoleReporter {

    private final PrintStream out;

    /**
     * Creates a reporter.
     *
     * @param out the destination stream, must not be null
     * @throws IllegalArgumentException if the stream is null
     */
    public ConsoleReporter(PrintStream out) {
        if (out == null) {
            throw new IllegalArgumentException("Output must not be null");
        }
        this.out = out;
    }

    /**
     * Prints the whole report: total calories, sorted vegetables and the
     * vegetables within the configured calorie range.
     *
     * @param service the service providing the salad data
     */
    public void printReport(SaladService service) {
        SaladConfig config = service.getConfig();
        out.println("Salad: " + config.getSaladName());
        printTotalCalories(service);
        printSortedVegetables(service);
        printVegetablesInRange(service);
        printSeparator();
    }

    /**
     * Prints the available menu options.
     */
    public void printMenu() {
        out.println("1 - total calories");
        out.println("2 - sorted vegetables");
        out.println("3 - vegetables in calorie range");
        out.println("0 - exit");
        out.print("Your choice: ");
    }

    /**
     * Prints the total caloric value of the salad.
     *
     * @param service the service providing the salad data
     */
    public void printTotalCalories(SaladService service) {
        out.printf("Total calories: %.2f kcal%n", service.getTotalCalories());
    }

    /**
     * Prints the vegetables sorted according to the configuration.
     *
     * @param service the service providing the salad data
     */
    public void printSortedVegetables(SaladService service) {
        printVegetables("Sorted vegetables:", service.getSortedVegetables());
    }

    /**
     * Prints the vegetables within the configured calorie range.
     *
     * @param service the service providing the salad data
     */
    public void printVegetablesInRange(SaladService service) {
        SaladConfig config = service.getConfig();
        printVegetables(String.format("Vegetables in calorie range %.0f..%.0f kcal/100g:",
                config.getCalorieRangeMin(), config.getCalorieRangeMax()),
                service.findVegetablesInRange());
    }

    /**
     * Prints a message about an unknown menu option.
     *
     * @param input the entered option
     */
    public void printUnknownOption(String input) {
        out.println("Unknown option: " + input);
    }

    /**
     * Prints the exit message.
     */
    public void printExit() {
        out.println("Bye");
    }

    /**
     * Prints an empty line.
     */
    public void printSeparator() {
        out.println();
    }

    private void printVegetables(String title, List<Vegetable> vegetables) {
        out.println(title);
        for (Vegetable vegetable : vegetables) {
            out.println("  " + vegetable);
        }
    }
}