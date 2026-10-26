package oop.lab1.serbin.service;

import java.util.List;

import oop.lab1.serbin.model.Vegetable;

/**
 * Application service that builds a salad from a configuration and a list of
 * vegetables and exposes the operations required by the task.
 */
public class SaladService {

    private final SaladConfig config;
    private final Salad salad;

    /**
     * Creates the service and fills the salad with the given vegetables.
     *
     * @param config      the salad configuration, must not be null
     * @param vegetables  the vegetables to add, must not be null
     * @throws IllegalArgumentException if {@code config} or {@code vegetables} is null
     */
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

    /**
     * Calculates the total caloric value of the salad.
     *
     * @return the total calories
     */
    public double getTotalCalories() {
        return salad.getTotalCalories();
    }

    /**
     * Returns the vegetables sorted according to the configured parameter.
     *
     * @return a new sorted list of vegetables
     * @throws IllegalArgumentException if the sort parameter is not supported
     */
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

    /**
     * Finds the vegetables whose caloric value falls within the configured range.
     *
     * @return the vegetables within the configured calorie range
     */
    public List<Vegetable> findVegetablesInRange() {
        return salad.findByCalorieRange(config.getCalorieRangeMin(), config.getCalorieRangeMax());
    }

    /**
     * Returns the underlying salad.
     *
     * @return the salad
     */
    public Salad getSalad() {
        return salad;
    }

    /**
     * Returns the configuration.
     *
     * @return the configuration
     */
    public SaladConfig getConfig() {
        return config;
    }
}