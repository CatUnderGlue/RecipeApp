package ru.catunderglue.recipesapp.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Ingredient {
    private String name;
    private int count;
    private String measureUnit;

    public Ingredient(String name, int count, String measureUnit) {
        setName(name);
        setCount(count);
        setMeasureUnit(measureUnit);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (!StringUtils.isBlank(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("У ингредиента должно быть корректное название!");
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count >= 0) {
            this.count = count;
        } else {
            throw new IllegalArgumentException("У ингредиента не может быть отрицательное количество!");
        }
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        if (!StringUtils.isBlank(measureUnit)) {
            this.measureUnit = measureUnit;
        } else {
            throw new IllegalArgumentException("У ингредиента должна быть корректная единица измерения!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return count == that.count && Objects.equals(name, that.name) && Objects.equals(measureUnit, that.measureUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, count, measureUnit);
    }

    @Override
    public String toString() {
        return String.format("%s в количестве %d %s", name, count, measureUnit);
    }
}
