package oop.lab1.serbin.service;

/**
 * Immutable set of parameters used to build and process a salad.
 */
public class SaladConfig {

    private final String saladName;
    private final String sortParameter;
    private final double calorieRangeMin;
    private final double calorieRangeMax;

    /**
     * Creates a configuration.
     *
     * @param saladName       the name of the salad, must not be blank
     * @param sortParameter   the parameter used for sorting, must not be blank
     * @param calorieRangeMin the lower bound of the calorie range
     * @param calorieRangeMax the upper bound of the calorie range
     * @throws IllegalArgumentException if a name is blank or the lower bound
     *                                  exceeds the upper bound
     */
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

    /**
     * Returns the name of the salad.
     *
     * @return the salad name
     */
    public String getSaladName() {
        return saladName;
    }

    /**
     * Returns the sorting parameter.
     *
     * @return the sort parameter
     */
    public String getSortParameter() {
        return sortParameter;
    }

    /**
     * Returns the lower bound of the calorie range.
     *
     * @return the minimum calories per 100 grams
     */
    public double getCalorieRangeMin() {
        return calorieRangeMin;
    }

    /**
     * Returns the upper bound of the calorie range.
     *
     * @return the maximum calories per 100 grams
     */
    public double getCalorieRangeMax() {
        return calorieRangeMax;
    }
}