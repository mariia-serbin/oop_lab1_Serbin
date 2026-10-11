package oop.lab1.serbin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import oop.lab1.serbin.model.Vegetable;

public class Salad {

    private final String name;
    private final List<Vegetable> vegetables = new ArrayList<>();

    public Salad(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        this.name = name;
    }

    public void addVegetable(Vegetable vegetable) {
        if (vegetable == null) {
            throw new IllegalArgumentException("Vegetable must not be null");
        }
        vegetables.add(vegetable);
    }

    public boolean removeVegetable(Vegetable vegetable) {
        return vegetables.remove(vegetable);
    }

    public double getTotalCalories() {
        return vegetables.stream()
                .mapToDouble(Vegetable::getCalories)
                .sum();
    }

    public String getName() {
        return name;
    }

    public List<Vegetable> getVegetables() {
        return Collections.unmodifiableList(vegetables);
    }

    public List<Vegetable> sortByCalories() {
        List<Vegetable> sorted = new ArrayList<>(vegetables);
        sorted.sort(Comparator.comparingDouble(Vegetable::getCalories));
        return sorted;
    }

    public List<Vegetable> sortByWeight() {
        List<Vegetable> sorted = new ArrayList<>(vegetables);
        sorted.sort(Comparator.comparingDouble(Vegetable::getWeightGrams));
        return sorted;
    }

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