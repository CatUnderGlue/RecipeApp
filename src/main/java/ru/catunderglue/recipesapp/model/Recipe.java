package ru.catunderglue.recipesapp.model;


import java.util.Arrays;
import java.util.Objects;

public class Recipe {
    private int id;
    private String title;
    private int cookingTime;
    private int numberOfServings;
    private Ingredient[] ingredients;
    private String[] cookingInstructions;

    public Recipe(String title, int cookingTime, int numberOfServings, Ingredient[] ingredients, String[] cookingInstructions) {
        setTitle(title);
        setCookingTime(cookingTime);
        setIngredients(ingredients);
        setCookingInstructions(cookingInstructions);
        setNumberOfServings(numberOfServings);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.isBlank() && !title.isEmpty()) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("У рецепта должно быть название!");
        }
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        if (cookingTime > 0) {
            this.cookingTime = cookingTime;
        } else {
            throw new IllegalArgumentException("У рецепта не может быть отрицательное время приготовления!");
        }
    }

    public int getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(int numberOfServings) {
        if (numberOfServings > 0) {
            this.numberOfServings = numberOfServings;
        } else {
            throw new IllegalArgumentException("У рецепта не может быть отрицательное количество порций!");
        }
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        if (ingredients.length > 0) {
            this.ingredients = ingredients;
        } else {
            throw new IllegalArgumentException("У рецепта должен быть хотя бы один ингредиент!");
        }
    }

    public String[] getCookingInstructions() {
        return cookingInstructions;
    }

    public void setCookingInstructions(String[] cookingInstructions) {
        if (cookingInstructions.length > 0) {
            this.cookingInstructions = cookingInstructions;
        } else {
            throw new IllegalArgumentException("У рецепта должен быть хотя бы один шаг приготовления!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return cookingTime == recipe.cookingTime && Objects.equals(title, recipe.title) && Arrays.equals(ingredients, recipe.ingredients) && Arrays.equals(cookingInstructions, recipe.cookingInstructions);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, cookingTime);
        result = 31 * result + Arrays.hashCode(ingredients);
        result = 31 * result + Arrays.hashCode(cookingInstructions);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID:").append(id).append("<br>");
        builder.append(title).append("<br>");
        builder.append("Время готовки: ").append(cookingTime).append(" минут<br>");
        builder.append("Количество порций: ").append(numberOfServings).append(" шт.<br>");
        builder.append("Ингредиенты:<br>");
        for (Ingredient ingredient : ingredients) {
            builder.append("• ").append(ingredient).append("<br>");
        }
        builder.append("Инструкция по приготовлению:<br>");
        for (int i = 0; i < cookingInstructions.length; i++) {
            builder.append("<b>").append(i + 1).append("</b>").append(" - ").append(cookingInstructions[i]).append("<br>");
        }
        return builder.toString();
    }
}
