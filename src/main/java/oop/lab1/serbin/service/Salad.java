package oop.lab1.serbin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import oop.lab1.serbin.model.Vegetable;

/**
 * Represents a salad as a named collection of vegetables.
 */
public class Salad {

    private final String name;
    private final List<Vegetable> vegetables = new ArrayList<>();

    /**
     * Creates an empty salad.
     *
     * @param name the name of the salad, must not be blank
     * @throws IllegalArgumentException if the name is blank
     */
    public Salad(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        this.name = name;
    }

    /**
     * Adds a vegetable to the salad.
     *
     * @param vegetable the vegetable to add, must not be null
     * @throws IllegalArgumentException if the vegetable is null
     */
    public void addVegetable(Vegetable vegetable) {
        if (vegetable == null) {
            throw new IllegalArgumentException("Vegetable must not be null");
        }
        vegetables.add(vegetable);
    }

    /**
     * Removes a vegetable from the salad.
     *
     * @param vegetable the vegetable to remove
     * @return {@code true} if the vegetable was present and removed
     */
    public boolean removeVegetable(Vegetable vegetable) {
        return vegetables.remove(vegetable);
    }

    /**
     * Calculates the total caloric value of the salad.
     *
     * @return the sum of the caloric values of all vegetables
     */
    public double getTotalCalories() {
        return vegetables.stream()
                .mapToDouble(Vegetable::getCalories)
                .sum();
    }

    /**
     * Returns the name of the salad.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an unmodifiable view of the salad vegetables.
     *
     * @return the list of vegetables
     */
    public List<Vegetable> getVegetables() {
        return Collections.unmodifiableList(vegetables);
    }

    /**
     * Returns the vegetables sorted by the caloric value of their portions.
     * The order of vegetables in the salad is not changed.
     *
     * @return a new sorted list of vegetables
     */
    public List<Vegetable> sortByCalories() {
        List<Vegetable> sorted = new ArrayList<>(vegetables);
        sorted.sort(Comparator.comparingDouble(Vegetable::getCalories));
        return sorted;
    }

    /**
     * Returns the vegetables sorted by portion weight. The order of vegetables
     * in the salad is not changed.
     *
     * @return a new sorted list of vegetables
     */
    public List<Vegetable> sortByWeight() {
        List<Vegetable> sorted = new ArrayList<>(vegetables);
        sorted.sort(Comparator.comparingDouble(Vegetable::getWeightGrams));
        return sorted;
    }

    /**
     * Finds vegetables whose caloric value per 100 grams is within the given range.
     *
     * @param min the lower bound of the range, inclusive
     * @param max the upper bound of the range, inclusive
     * @return the vegetables within the range
     * @throws IllegalArgumentException if {@code min} is greater than {@code max}
     */
    public List<Vegetable> findByCalorieRange(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("Min must not exceed max");
        }
        return vegetables.stream()
                .filter(vegetable -> vegetable.getCaloriesPer100g() >= min
                        && vegetable.getCaloriesPer100g() <= max)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name + " (" + vegetables.size() + " vegetables, "
                + getTotalCalories() + " kcal)";
    }
}