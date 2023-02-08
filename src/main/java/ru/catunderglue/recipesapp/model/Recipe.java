package ru.catunderglue.recipesapp.model;


import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

public class Recipe {
    private String title;
    private int cookingTime;
    private int numberOfServings;
    private List<Ingredient> ingredients;
    private List<String> cookingInstructions;

    public Recipe(String title, int cookingTime, int numberOfServings, List<Ingredient> ingredients, List<String> cookingInstructions) {
        setTitle(title);
        setCookingTime(cookingTime);
        setIngredients(ingredients);
        setCookingInstructions(cookingInstructions);
        setNumberOfServings(numberOfServings);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (!StringUtils.isBlank(title)) {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        if (ingredients.size() > 0) {
            this.ingredients = ingredients;
        } else {
            throw new IllegalArgumentException("У рецепта должен быть хотя бы один ингредиент!");
        }
    }

    public List<String> getCookingInstructions() {
        return cookingInstructions;
    }

    public void setCookingInstructions(List<String> cookingInstructions) {
        if (cookingInstructions.size() > 0) {
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
        return cookingTime == recipe.cookingTime && numberOfServings == recipe.numberOfServings && Objects.equals(title, recipe.title) && Objects.equals(ingredients, recipe.ingredients) && Objects.equals(cookingInstructions, recipe.cookingInstructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, cookingTime, numberOfServings, ingredients, cookingInstructions);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(title).append("<br>");
        builder.append("Время готовки: ").append(cookingTime).append(" минут<br>");
        builder.append("Количество порций: ").append(numberOfServings).append(" шт.<br>");
        builder.append("Ингредиенты:<br>");
        for (Ingredient ingredient : ingredients) {
            builder.append("• ").append(ingredient).append("<br>");
        }
        builder.append("Инструкция по приготовлению:<br>");
        for (int i = 0; i < cookingInstructions.size(); i++) {
            builder.append("<b>").append(i + 1).append("</b>").append(" - ").append(cookingInstructions.get(i)).append("<br>");
        }
        return builder.toString();
    }
}
