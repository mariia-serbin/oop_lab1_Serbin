package oop.lab1.serbin.model;

public class RootVegetable extends Vegetable {

    private double rootDepthCm;

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

    public double getRootDepthCm() {
        return rootDepthCm;
    }

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