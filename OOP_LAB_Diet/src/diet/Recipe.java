package diet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Represents a recipe of the diet.
 * <p>
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 */
public class Recipe implements NutritionalElement {
    private String name;
    private Food food;
    private Map<NutritionalElement, Double> ingredients = new HashMap<>();

    public Recipe(String name, Food food) {
        this.name = name;
        this.food = food;
    }


    /**
     * Adds a given quantity of an ingredient to the recipe.
     * The ingredient is a raw material.
     *
     * @param material the name of the raw material to be used as ingredient
     * @param quantity the amount in grams of the raw material to be used
     * @return the same Recipe object, it allows method chaining.
     */
    public Recipe addIngredient(String material, double quantity) {
        NutritionalElement rawMaterial = this.food.getRawMaterial(material);
        if (!this.ingredients.containsKey(rawMaterial)) {
            this.ingredients.put(rawMaterial, quantity);
        } else {
            this.ingredients.put(rawMaterial, (Double) this.ingredients.get(rawMaterial) + quantity);
        }

        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getCalories() {
        double totalCalories = 0.0;
        double totalQuantity = 0.0;

        Map.Entry entry;
        for (Iterator var5 = this.ingredients.entrySet().iterator(); var5.hasNext(); totalQuantity += (Double) entry.getValue()) {
            entry = (Map.Entry) var5.next();
        }

        return totalCalories / totalQuantity * 100.0;
    }


    @Override
    public double getProteins() {
        double totalProteins = 0.0;
        double totalQuantity = 0.0;

        Map.Entry entry;
        for (Iterator var5 = this.ingredients.entrySet().iterator(); var5.hasNext(); totalQuantity += (Double) entry.getValue()) {
            entry = (Map.Entry) var5.next();
            totalProteins += ((NutritionalElement) entry.getKey()).getProteins() / 100.0 * (Double) entry.getValue();
        }
        return totalProteins / totalQuantity;
    }

    @Override
    public double getCarbs() {
        double totalCarbs = 0.0;
        double totalQuantity = 0.0;

        Map.Entry entry;
        for(Iterator var5 = this.ingredients.entrySet().iterator(); var5.hasNext(); totalQuantity += (Double)entry.getValue()) {
            entry = (Map.Entry)var5.next();
            totalCarbs += ((NutritionalElement)entry.getKey()).getCarbs() / 100.0 * (Double)entry.getValue();
        }

        return totalCarbs / totalQuantity;
    }

    @Override
    public double getFat() {
        double totalFat = 0.0;
        double totalQuantity = 0.0;

        Map.Entry entry;
        for(Iterator var5 = this.ingredients.entrySet().iterator(); var5.hasNext(); totalQuantity += (Double)entry.getValue()) {
            entry = (Map.Entry)var5.next();
            totalFat += ((NutritionalElement)entry.getKey()).getFat() / 100.0 * (Double)entry.getValue();
        }

        return totalFat / totalQuantity;
    }

    /**
     * Indicates whether the nutritional values returned by the other methods
     * refer to a conventional 100g quantity of nutritional element,
     * or to a unit of element.
     * <p>
     * For the {@link Recipe} class it must always return {@code true}:
     * a recipe expressed nutritional values per 100g
     *
     * @return boolean indicator
     */
    @Override
    public boolean per100g() {
        return true;
    }

}
