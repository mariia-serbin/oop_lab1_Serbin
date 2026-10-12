package oop.lab1.serbin.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oop.lab1.serbin.service.SaladService;

class ConsoleMenuTest {

    private SaladService service;
    private ConsoleReporter reporter;
    private ConsoleMenu menu;

    @BeforeEach
    void setUp() {
        service = mock(SaladService.class);
        reporter = mock(ConsoleReporter.class);
        menu = new ConsoleMenu(service, reporter, new Scanner(""));
    }

    @Test
    void rejectsNullArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> new ConsoleMenu(null, reporter, new Scanner("")));
        assertThrows(IllegalArgumentException.class,
                () -> new ConsoleMenu(service, null, new Scanner("")));
        assertThrows(IllegalArgumentException.class,
                () -> new ConsoleMenu(service, reporter, null));
    }

    @Test
    void command1PrintsTotalCalories() {
        assertTrue(menu.handleCommand("1"));
        verify(reporter).printTotalCalories(service);
    }

    @Test
    void command2PrintsSortedVegetables() {
        assertTrue(menu.handleCommand("2"));
        verify(reporter).printSortedVegetables(service);
    }

    @Test
    void command3PrintsRange() {
        assertTrue(menu.handleCommand("3"));
        verify(reporter).printVegetablesInRange(service);
    }

    @Test
    void command0Exits() {
        assertFalse(menu.handleCommand("0"));
        verify(reporter).printExit();
    }

    @Test
    void unknownCommand() {
        assertTrue(menu.handleCommand("x"));
        verify(reporter).printUnknownOption("x");
    }

    @Test
    void runStopsOnExit() {
        ConsoleMenu running = new ConsoleMenu(service, reporter, new Scanner("0\n"));
        running.run();
        verify(reporter).printMenu();
        verify(reporter).printExit();
        verifyNoInteractions(service);
    }
}