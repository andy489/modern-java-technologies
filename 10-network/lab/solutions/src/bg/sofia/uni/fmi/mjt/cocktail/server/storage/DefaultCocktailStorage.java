package bg.sofia.uni.fmi.mjt.cocktail.server.storage;

import bg.sofia.uni.fmi.mjt.cocktail.server.Cocktail;
import bg.sofia.uni.fmi.mjt.cocktail.server.Ingredient;
import bg.sofia.uni.fmi.mjt.cocktail.server.storage.exceptions.CocktailAlreadyExistsException;
import bg.sofia.uni.fmi.mjt.cocktail.server.storage.exceptions.CocktailNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultCocktailStorage implements CocktailStorage {

    private final Map<String, Cocktail> nameCocktail = new HashMap<>();
    private final List<Cocktail> cocktails = new ArrayList<>();
    private final Map<String, List<Cocktail>> ingredientCocktails = new HashMap<>();

    @Override
    public void createCocktail(Cocktail cocktail) throws CocktailAlreadyExistsException {
        if (nameCocktail.containsKey(cocktail.name())) {
            throw new CocktailAlreadyExistsException("Cocktail already exists.");
        }

        nameCocktail.put(cocktail.name(), cocktail);
        cocktails.add(cocktail);

        for (Ingredient ing : cocktail.ingredients()) {
            ingredientCocktails.computeIfAbsent(ing.name(), k -> new ArrayList<>()).add(cocktail);
        }
    }

    @Override
    public Collection<Cocktail> getCocktails() {
        return cocktails;
    }

    @Override
    public Collection<Cocktail> getCocktailsWithIngredient(String ingredientName) {
        return ingredientCocktails.getOrDefault(ingredientName, Collections.emptyList());
    }

    @Override
    public Cocktail getCocktail(String name) throws CocktailNotFoundException {
        var cocktail = nameCocktail.get(name);
        if (cocktail == null) {
            throw new CocktailNotFoundException("No such cocktail: " + name);
        }
        return cocktail;
    }
}
