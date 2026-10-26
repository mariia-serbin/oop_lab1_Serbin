package oop.lab1.serbin.model;

/**
 * A fruiting vegetable (for example tomato or cucumber).
 */
public class FruitingVegetable extends Vegetable {

    private boolean ripe;

    /**
     * Creates a fruiting vegetable.
     *
     * @param name            the name of the vegetable, must not be blank
     * @param weightGrams     the portion weight in grams, must be positive
     * @param caloriesPer100g the caloric value per 100 grams, must not be negative
     * @param ripe            whether the vegetable is ripe
     * @throws IllegalArgumentException if any argument is invalid
     */
    public FruitingVegetable(String name, double weightGrams, double caloriesPer100g,
                             boolean ripe) {
        super(name, weightGrams, caloriesPer100g);
        this.ripe = ripe;
    }

    @Override
    public String getCategory() {
        return "Fruiting";
    }

    /**
     * Tells whether the vegetable is ripe.
     *
     * @return {@code true} if ripe, {@code false} otherwise
     */
    public boolean isRipe() {
        return ripe;
    }

    /**
     * Updates the ripeness flag.
     *
     * @param ripe whether the vegetable is ripe
     */
    public void setRipe(boolean ripe) {
        this.ripe = ripe;
    }

    @Override
    public String toString() {
        return super.toString() + ", ripe: " + ripe;
    }
}