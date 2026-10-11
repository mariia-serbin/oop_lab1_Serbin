package oop.lab1.serbin.model;

public abstract class Vegetable implements Comparable<Vegetable> {

    private final String name;
    private final double caloriesPer100g;
    private double weightGrams;

    protected Vegetable(String name, double weightGrams, double caloriesPer100g) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        if (weightGrams <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        if (caloriesPer100g < 0) {
            throw new IllegalArgumentException("Calories must not be negative");
        }
        this.name = name;
        this.weightGrams = weightGrams;
        this.caloriesPer100g = caloriesPer100g;
    }

    public double getCalories() {
        return weightGrams / 100.0 * caloriesPer100g;
    }

    public abstract String getCategory();

    public String getName() {
        return name;
    }

    public double getWeightGrams() {
        return weightGrams;
    }

    public double getCaloriesPer100g() {
        return caloriesPer100g;
    }

    public void setWeightGrams(double weightGrams) {
        if (weightGrams <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weightGrams = weightGrams;
    }

    @Override
    public int compareTo(Vegetable other) {
        return Double.compare(getCalories(), other.getCalories());
    }

    @Override
    public String toString() {
        return getCategory() + " " + name + " (" + weightGrams + " g, "
                + caloriesPer100g + " kcal/100g) - " + getCalories() + " kcal";
    }
}