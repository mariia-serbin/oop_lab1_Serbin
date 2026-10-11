package oop.lab1.serbin.service;

import java.util.List;

import oop.lab1.serbin.model.Vegetable;

public class SaladService {

    private final SaladConfig config;
    private final Salad salad;

    public SaladService(SaladConfig config, List<Vegetable> vegetables) {
        if (config == null) {
            throw new IllegalArgumentException("Config must not be null");
        }
        if (vegetables == null) {
            throw new IllegalArgumentException("Vegetables must not be null");
        }
        this.config = config;
        this.salad = new Salad(config.getSaladName());
        for (Vegetable vegetable : vegetables) {
            this.salad.addVegetable(vegetable);
        }
    }

    public double getTotalCalories() {
        return salad.getTotalCalories();
    }

    public List<Vegetable> getSortedVegetables() {
        String sortParameter = config.getSortParameter().toUpperCase();
        switch (sortParameter) {
            case "CALORIES":
                return salad.sortByCalories();
            case "WEIGHT":
                return salad.sortByWeight();
            default:
                throw new IllegalArgumentException("Unknown sort parameter: " + sortParameter);
        }
    }

    public List<Vegetable> findVegetablesInRange() {
        return salad.findByCalorieRange(config.getCalorieRangeMin(), config.getCalorieRangeMax());
    }

    public Salad getSalad() {
        return salad;
    }

    public SaladConfig getConfig() {
        return config;
    }
}