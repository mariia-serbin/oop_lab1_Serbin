package oop.lab1.serbin.model;

/**
 * Base class for all salad vegetables. Encapsulates the common parameters of a
 * vegetable: its name, portion weight and caloric value per 100 grams.
 */
public abstract class Vegetable implements Comparable<Vegetable> {

    private final String name;
    private final double caloriesPer100g;
    private double weightGrams;

    /**
     * Creates a vegetable.
     *
     * @param name            the name of the vegetable, must not be blank
     * @param weightGrams     the portion weight in grams, must be positive
     * @param caloriesPer100g the caloric value per 100 grams, must not be negative
     * @throws IllegalArgumentException if any argument is invalid
     */
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

    /**
     * Calculates the caloric value of this portion.
     *
     * @return weight in grams divided by 100 multiplied by calories per 100 grams
     */
    public double getCalories() {
        return weightGrams / 100.0 * caloriesPer100g;
    }

    /**
     * Returns the category of the vegetable.
     *
     * @return the category name
     */
    public abstract String getCategory();

    /**
     * Returns the name of the vegetable.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the portion weight in grams.
     *
     * @return the weight in grams
     */
    public double getWeightGrams() {
        return weightGrams;
    }

    /**
     * Returns the caloric value per 100 grams.
     *
     * @return calories per 100 grams
     */
    public double getCaloriesPer100g() {
        return caloriesPer100g;
    }

    /**
     * Updates the portion weight.
     *
     * @param weightGrams the new weight in grams, must be positive
     * @throws IllegalArgumentException if the weight is not positive
     */
    public void setWeightGrams(double weightGrams) {
        if (weightGrams <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weightGrams = weightGrams;
    }

    /**
     * Compares vegetables by the caloric value of their portions.
     *
     * @param other the vegetable to compare with
     * @return a negative, zero or positive value if this portion has fewer,
     *         equal or more calories than the other one
     */
    @Override
    public int compareTo(Vegetable other) {
        return Double.compare(getCalories(), other.getCalories());
    }

    /**
     * Returns a text description of the vegetable.
     *
     * @return the description
     */
    @Override
    public String toString() {
        return getCategory() + " " + name + " (" + weightGrams + " g, "
                + caloriesPer100g + " kcal/100g) - " + getCalories() + " kcal";
    }
}