package com.redck.restaurantmsbff.domain;

import java.util.List;


public class MenuItem
{
    private String name;
    private String description;
    private double price;
    private String category;
    private List<String> ingredients;
    private Boolean vegan;
    private Boolean glutenFree;
    private Integer calories;
    private Boolean availability;
    private Integer preparationTime;
    private List<String> availableDays;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public MenuItem name(final String name)
    {
        this.name = name;
        return this;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public MenuItem description(final String description)
    {
        this.description = description;
        return this;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public MenuItem price(final double price)
    {
        this.price = price;
        return this;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public MenuItem category(final String category)
    {
        this.category = category;
        return this;
    }

    public List<String> getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients)
    {
        this.ingredients = ingredients;
    }

    public MenuItem ingredients(final List<String> ingredients)
    {
        this.ingredients = ingredients;
        return this;
    }

    public Boolean isVegan()
    {
        return vegan;
    }

    public void setVegan(Boolean vegan)
    {
        this.vegan = vegan;
    }

    private MenuItem vegan(final Boolean vegan)
    {
        this.vegan = vegan;
        return this;
    }

    public Boolean isGlutenFree()
    {
        return glutenFree;
    }

    public void setGlutenFree(Boolean glutenFree)
    {
        this.glutenFree = glutenFree;
    }

    public MenuItem glutenFree(final Boolean glutenFree)
    {
        this.glutenFree = glutenFree;
        return this;
    }

    public Integer getCalories()
    {
        return calories;
    }

    public void setCalories(Integer calories)
    {
        this.calories = calories;
    }

    public MenuItem calories(final Integer calories)
    {
        this.calories = calories;
        return this;
    }

    public Boolean isAvailable()
    {
        return availability;
    }

    public void setAvailability(Boolean availability)
    {
        this.availability = availability;
    }

    public MenuItem availability(final Boolean availability)
    {
        this.availability = availability;
        return this;
    }

    public Integer getPreparationTime()
    {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime)
    {
        this.preparationTime = preparationTime;
    }

    public MenuItem preparationTime(final Integer preparationTime)
    {
        this.preparationTime = preparationTime;
        return this;
    }

    public List<String> getAvailableDays()
    {
        return availableDays;
    }

    public void setAvailableDays(List<String> availableDays)
    {
        this.availableDays = availableDays;
    }

    public MenuItem availableDays(final List<String> availableDays)
    {
        this.availableDays = availableDays;
        return this;
    }

    @Override
    public String toString()
    {
        return "MenuItem { " +
                "name = " + name +
                ", description = " + description +
                ", price = " + price +
                ", category = " + category +
                ", ingredients = " + ingredients +
                ", vegan = " + vegan +
                ", glutenFree = " + glutenFree +
                ", calories = " + calories +
                ", availability = "  + availability +
                ", preparationTime = " + preparationTime +
                ", availableDays = " + availableDays +
                " }";
    }

}
