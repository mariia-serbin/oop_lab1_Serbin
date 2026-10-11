package oop.lab1.serbin.service;

public class SaladConfig {

    private final String saladName;
    private final String sortParameter;
    private final double calorieRangeMin;
    private final double calorieRangeMax;

    public SaladConfig(String saladName, String sortParameter,
                       double calorieRangeMin, double calorieRangeMax) {
        if (saladName == null || saladName.isBlank()) {
            throw new IllegalArgumentException("Salad name must not be blank");
        }
        if (sortParameter == null || sortParameter.isBlank()) {
            throw new IllegalArgumentException("Sort parameter must not be blank");
        }
        if (calorieRangeMin > calorieRangeMax) {
            throw new IllegalArgumentException("Min must not exceed max");
        }
        this.saladName = saladName;
        this.sortParameter = sortParameter;
        this.calorieRangeMin = calorieRangeMin;
        this.calorieRangeMax = calorieRangeMax;
    }

    public String getSaladName() {
        return saladName;
    }

    public String getSortParameter() {
        return sortParameter;
    }

    public double getCalorieRangeMin() {
        return calorieRangeMin;
    }

    public double getCalorieRangeMax() {
        return calorieRangeMax;
    }
}