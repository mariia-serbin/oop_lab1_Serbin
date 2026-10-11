package oop.lab1.serbin.app;

import java.util.Scanner;

import oop.lab1.serbin.service.SaladService;

public class ConsoleMenu {

    private final SaladService service;
    private final ConsoleReporter reporter;
    private final Scanner scanner;

    public ConsoleMenu(SaladService service, ConsoleReporter reporter, Scanner scanner) {
        if (service == null || reporter == null || scanner == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }
        this.service = service;
        this.reporter = reporter;
        this.scanner = scanner;
    }

    public void run() {
        boolean running = true;
        while (running) {
            reporter.printMenu();
            running = handleCommand(scanner.nextLine().trim());
            reporter.printSeparator();
        }
    }

    boolean handleCommand(String input) {
        switch (input) {
            case "1":
                reporter.printTotalCalories(service);
                return true;
            case "2":
                reporter.printSortedVegetables(service);
                return true;
            case "3":
                reporter.printVegetablesInRange(service);
                return true;
            case "0":
                reporter.printExit();
                return false;
            default:
                reporter.printUnknownOption(input);
                return true;
        }
    }
}