package oop.lab1.serbin.model;

public class LeafyVegetable extends Vegetable {

    private boolean greenhouseGrown;

    public LeafyVegetable(String name, double weightGrams, double caloriesPer100g,
                          boolean greenhouseGrown) {
        super(name, weightGrams, caloriesPer100g);
        this.greenhouseGrown = greenhouseGrown;
    }

    @Override
    public String getCategory() {
        return "Leafy";
    }

    public boolean isGreenhouseGrown() {
        return greenhouseGrown;
    }

    public void setGreenhouseGrown(boolean greenhouseGrown) {
        this.greenhouseGrown = greenhouseGrown;
    }

    @Override
    public String toString() {
        return super.toString() + ", greenhouse: " + greenhouseGrown;
    }
}