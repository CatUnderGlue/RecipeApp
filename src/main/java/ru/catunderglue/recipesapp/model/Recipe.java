package ru.catunderglue.recipesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Рецепт
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String title;
    private int cookingTime;
    private int numberOfServings;
    private List<Ingredient> ingredients;
    private List<String> cookingInstructions;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(title).append("\n");
        builder.append("Время готовки: ").append(cookingTime).append(" минут\n");
        builder.append("Количество порций: ").append(numberOfServings).append(" шт.\n");
        builder.append("Ингредиенты:\n");
        for (Ingredient ingredient : ingredients) {
            builder.append("• ").append(ingredient).append("\n");
        }
        builder.append("Инструкция по приготовлению:\n");
        for (int i = 0; i < cookingInstructions.size(); i++) {
            builder.append(i + 1).append(" - ").append(cookingInstructions.get(i)).append("\n");
        }
        return builder.toString();
    }
}
