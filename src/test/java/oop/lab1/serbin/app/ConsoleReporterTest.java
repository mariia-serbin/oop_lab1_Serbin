package oop.lab1.serbin.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.Test;

import oop.lab1.serbin.model.FruitingVegetable;
import oop.lab1.serbin.model.LeafyVegetable;
import oop.lab1.serbin.model.RootVegetable;
import oop.lab1.serbin.model.Vegetable;
import oop.lab1.serbin.service.SaladConfig;
import oop.lab1.serbin.service.SaladService;

class ConsoleReporterTest {

    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private final ConsoleReporter reporter = new ConsoleReporter(new PrintStream(buffer));

    private String output() {
        return buffer.toString(StandardCharsets.UTF_8);
    }

    private SaladService service() {
        SaladConfig config = new SaladConfig("Vitamin Salad", "CALORIES", 15.0, 20.0);
        List<Vegetable> vegetables = List.of(
                new LeafyVegetable("Lettuce", 150.0, 15.0, true),
                new RootVegetable("Carrot", 120.0, 41.0, 15.0),
                new FruitingVegetable("Tomato", 130.0, 20.0, true));
        return new SaladService(config, vegetables);
    }

    @Test
    void rejectsNullOutput() {
        assertThrows(IllegalArgumentException.class, () -> new ConsoleReporter(null));
    }

    @Test
    void printsMenu() {
        reporter.printMenu();
        String text = output();
        assertTrue(text.contains("1 - total calories"));
        assertTrue(text.contains("0 - exit"));
        assertTrue(text.contains("Your choice:"));
    }

    @Test
    void printsTotalCalories() {
        reporter.printTotalCalories(service());
        assertTrue(output().contains("Total calories:"));
    }

    @Test
    void printsSortedVegetables() {
        reporter.printSortedVegetables(service());
        String text = output();
        assertTrue(text.contains("Sorted vegetables:"));
        assertTrue(text.contains("Lettuce"));
    }

    @Test
    void printsVegetablesInRange() {
        reporter.printVegetablesInRange(service());
        String text = output();
        assertTrue(text.contains("Vegetables in calorie range 15..20"));
        assertTrue(text.contains("Lettuce"));
    }

    @Test
    void printsUnknownOption() {
        reporter.printUnknownOption("x");
        assertEquals("Unknown option: x" + System.lineSeparator(), output());
    }

    @Test
    void printsExit() {
        reporter.printExit();
        assertTrue(output().contains("Bye"));
    }

    @Test
    void printsReport() {
        reporter.printReport(service());
        String text = output();
        assertTrue(text.contains("Salad: Vitamin Salad"));
        assertTrue(text.contains("Sorted vegetables:"));
    }
}