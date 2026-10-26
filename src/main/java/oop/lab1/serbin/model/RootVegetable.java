package oop.lab1.serbin.model;

/**
 * A root vegetable (for example carrot or beetroot).
 */
public class RootVegetable extends Vegetable {

    private double rootDepthCm;

    /**
     * Creates a root vegetable.
     *
     * @param name            the name of the vegetable, must not be blank
     * @param weightGrams     the portion weight in grams, must be positive
     * @param caloriesPer100g the caloric value per 100 grams, must not be negative
     * @param rootDepthCm     the average root depth in centimeters, must not be negative
     * @throws IllegalArgumentException if any argument is invalid
     */
    public RootVegetable(String name, double weightGrams, double caloriesPer100g,
                         double rootDepthCm) {
        super(name, weightGrams, caloriesPer100g);
        if (rootDepthCm < 0) {
            throw new IllegalArgumentException("Root depth must not be negative");
        }
        this.rootDepthCm = rootDepthCm;
    }

    @Override
    public String getCategory() {
        return "Root";
    }

    /**
     * Returns the average root depth.
     *
     * @return the root depth in centimeters
     */
    public double getRootDepthCm() {
        return rootDepthCm;
    }

    /**
     * Updates the average root depth.
     *
     * @param rootDepthCm the new root depth in centimeters, must not be negative
     * @throws IllegalArgumentException if the depth is negative
     */
    public void setRootDepthCm(double rootDepthCm) {
        if (rootDepthCm < 0) {
            throw new IllegalArgumentException("Root depth must not be negative");
        }
        this.rootDepthCm = rootDepthCm;
    }

    @Override
    public String toString() {
        return super.toString() + ", root depth: " + rootDepthCm + " cm";
    }
}