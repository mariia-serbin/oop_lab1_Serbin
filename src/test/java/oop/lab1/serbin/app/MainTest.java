package oop.lab1.serbin.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import oop.lab1.serbin.service.SaladService;

class MainTest {

    @Test
    void loadServiceReadsResources() throws IOException {
        SaladService service = Main.loadService();
        assertFalse(service.getSalad().getVegetables().isEmpty());
        assertEquals("Vitamin Salad", service.getConfig().getSaladName());
    }
}