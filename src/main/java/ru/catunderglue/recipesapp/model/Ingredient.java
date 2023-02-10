package ru.catunderglue.recipesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ингредиент. Входит в состав рецепта.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String name;
    private int count;
    private String measureUnit;

    @Override
    public String toString() {
        return String.format("%s в количестве %d %s", name, count, measureUnit);
    }
}
