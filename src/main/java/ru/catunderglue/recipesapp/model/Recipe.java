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
