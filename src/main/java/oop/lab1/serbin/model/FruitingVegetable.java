package oop.lab1.serbin.model;

public class FruitingVegetable extends Vegetable {

    private boolean ripe;

    public FruitingVegetable(String name, double weightGrams, double caloriesPer100g,
                             boolean ripe) {
        super(name, weightGrams, caloriesPer100g);
        this.ripe = ripe;
    }

    @Override
    public String getCategory() {
        return "Fruiting";
    }

    public boolean isRipe() {
        return ripe;
    }

    public void setRipe(boolean ripe) {
        this.ripe = ripe;
    }

    @Override
    public String toString() {
        return super.toString() + ", ripe: " + ripe;
    }
}