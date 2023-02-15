package diet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Represents a complete menu.
 * <p>
 * It can be made up of both packaged products and servings of given recipes.
 */
public class Menu implements NutritionalElement {
    private String name;
    private Food food;
    private Map<NutritionalElement, Double> recipeMap = new HashMap<>();
    private Map<NutritionalElement, Integer> productMap = new HashMap();

    public Menu(String name, Food food) {
        this.name = name;
        this.food = food;
    }

    /**
     * Adds a given serving size of a recipe.
     * The recipe is a name of a recipe defined in the {@code food}
     * argument of the constructor.
     *
     * @param recipe   the name of the recipe to be used as ingredient
     * @param quantity the amount in grams of the recipe to be used
     * @return the same Menu to allow method chaining
     */
    public Menu addRecipe(String recipe, double quantity) {
        NutritionalElement recipeObject = this.food.getRecipe(recipe);
        if (!this.recipeMap.containsKey(recipeObject)) {
            this.recipeMap.put(recipeObject, quantity);
        } else {
            this.recipeMap.put(recipeObject, (Double) this.recipeMap.get(recipeObject) + quantity);
        }

        return this;
    }

    /**
     * Adds a unit of a packaged product.
     * The product is a name of a product defined in the {@code food}
     * argument of the constructor.
     *
     * @param product the name of the product to be used as ingredient
     * @return the same Menu to allow method chaining
     */
    public Menu addProduct(String product) {
        NutritionalElement productObject = this.food.getProduct(product);
        if (!this.productMap.containsKey(productObject)) {
            this.productMap.put(productObject, 1);
        } else {
            this.productMap.put(productObject, (Integer) this.productMap.get(productObject) + 1);
        }

        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Total KCal in the menu
     */
    @Override
    public double getCalories() {
        double totalCalories = 0.0;
        for (Map.Entry<NutritionalElement, Double> entry : recipeMap.entrySet()) {
            totalCalories += entry.getKey().getCalories() / 100 * entry.getValue();
        }
        for (Map.Entry<NutritionalElement, Integer> entry : productMap.entrySet()) {
            totalCalories += entry.getKey().getCalories() / 100 * entry.getValue();
        }


        return totalCalories;
    }

    /**
     * Total proteins in the menu
     */
    @Override
    public double getProteins() {
        return 0.0;
    }

    /**
     * Total carbs in the menu
     */
    @Override
    public double getCarbs() {
        return 0.0;
    }

    /**
     * Total fats in the menu
     */
    @Override
    public double getFat() {
        return 0.0;
    }

    /**
     * Indicates whether the nutritional values returned by the other methods
     * refer to a conventional 100g quantity of nutritional element,
     * or to a unit of element.
     * <p>
     * For the {@link Menu} class it must always return {@code false}:
     * nutritional values are provided for the whole menu.
     *
     * @return boolean indicator
     */
    @Override
    public boolean per100g() {
        // nutritional values are provided for the whole menu.
        return false;
    }
}
