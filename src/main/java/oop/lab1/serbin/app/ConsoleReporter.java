package oop.lab1.serbin.app;

import java.io.PrintStream;
import java.util.List;

import oop.lab1.serbin.model.Vegetable;
import oop.lab1.serbin.service.SaladConfig;
import oop.lab1.serbin.service.SaladService;

public class ConsoleReporter {

    private final PrintStream out;

    public ConsoleReporter(PrintStream out) {
        if (out == null) {
            throw new IllegalArgumentException("Output must not be null");
        }
        this.out = out;
    }

    public void printReport(SaladService service) {
        SaladConfig config = service.getConfig();
        out.println("Salad: " + config.getSaladName());
        printTotalCalories(service);
        printSortedVegetables(service);
        printVegetablesInRange(service);
        printSeparator();
    }

    public void printMenu() {
        out.println("1 - total calories");
        out.println("2 - sorted vegetables");
        out.println("3 - vegetables in calorie range");
        out.println("0 - exit");
        out.print("Your choice: ");
    }

    public void printTotalCalories(SaladService service) {
        out.printf("Total calories: %.2f kcal%n", service.getTotalCalories());
    }

    public void printSortedVegetables(SaladService service) {
        printVegetables("Sorted vegetables:", service.getSortedVegetables());
    }

    public void printVegetablesInRange(SaladService service) {
        SaladConfig config = service.getConfig();
        printVegetables(String.format("Vegetables in calorie range %.0f..%.0f kcal/100g:",
                config.getCalorieRangeMin(), config.getCalorieRangeMax()),
                service.findVegetablesInRange());
    }

    public void printUnknownOption(String input) {
        out.println("Unknown option: " + input);
    }

    public void printExit() {
        out.println("Bye");
    }

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