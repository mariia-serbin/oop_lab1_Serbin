package oop.lab1.serbin.model;

/**
 * A leafy vegetable (for example lettuce or spinach).
 */
public class LeafyVegetable extends Vegetable {

    private boolean greenhouseGrown;

    /**
     * Creates a leafy vegetable.
     *
     * @param name            the name of the vegetable, must not be blank
     * @param weightGrams     the portion weight in grams, must be positive
     * @param caloriesPer100g the caloric value per 100 grams, must not be negative
     * @param greenhouseGrown whether the vegetable was grown in a greenhouse
     * @throws IllegalArgumentException if any argument is invalid
     */
    public LeafyVegetable(String name, double weightGrams, double caloriesPer100g,
                          boolean greenhouseGrown) {
        super(name, weightGrams, caloriesPer100g);
        this.greenhouseGrown = greenhouseGrown;
    }

    @Override
    public String getCategory() {
        return "Leafy";
    }

    /**
     * Tells whether the vegetable was grown in a greenhouse.
     *
     * @return {@code true} if grown in a greenhouse, {@code false} otherwise
     */
    public boolean isGreenhouseGrown() {
        return greenhouseGrown;
    }

    /**
     * Updates the greenhouse flag.
     *
     * @param greenhouseGrown whether the vegetable was grown in a greenhouse
     */
    public void setGreenhouseGrown(boolean greenhouseGrown) {
        this.greenhouseGrown = greenhouseGrown;
    }

    @Override
    public String toString() {
        return super.toString() + ", greenhouse: " + greenhouseGrown;
    }
}