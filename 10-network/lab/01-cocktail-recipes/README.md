# Cocktail Recipes

Whether on a weekday or a holiday, at the beach or on a ski slope, alcoholic or non-alcoholic — cocktails are a favourite drink for many. There is a vast variety of cocktails, and mixing them is a core skill of any self-respecting bartender. Sometimes you just want to make your favourite Mojito at home — but what's actually in it?

Let's help all cocktail enthusiasts by building an application for searching and sharing cocktail recipes. The application will be a **client-server** system: the server stores and manages the recipes, while clients communicate with the server to search and upload recipes.

## Cocktail Recipes Client

- Any user can upload cocktail recipes.
- Users can search for recipes (by name or by ingredient).
- Users can disconnect from the server at any time.

## Cocktail Recipes Server

- The server must be able to serve multiple clients concurrently.
- The server receives commands from clients and returns an appropriate response.

## Client Commands

```text
create <cocktail_name> [<ingredient_name>=<ingredient_amount> ...]
    Creates a new cocktail recipe with the given name and ingredients.
    The name must be a single word (no whitespace). Each cocktail contains
    at least one ingredient in the format whisky=100ml, where "whisky" is
    the ingredient name and "100ml" is the amount (both are arbitrary strings
    with no whitespace and no '='). Ingredients are separated by whitespace.
    No duplicate ingredient names within a single recipe.

get all
    Returns a list of all cocktail recipes.

get by-name <cocktail_name>
    Returns the recipe for the cocktail with the given name.

get by-ingredient <ingredient_name>
    Returns a list of all recipes that contain the given ingredient.

disconnect
    The user disconnects from the server.
```

## Example Session

```text
# start a cocktail recipes client

# create a new cocktail recipe
=> create manhattan whisky=100ml
=> {"status":"CREATED"}

# attempt to create a recipe that already exists
=> create manhattan whisky=100ml
=> {"status":"ERROR","errorMessage":"cocktail manhattan already exists"}

# list all recipes when there are none
=> get all
=> {"status":"OK","cocktails":[]}

# list all recipes after creation
=> get all
=> {"status":"OK","cocktails":[{"name":"manhattan","ingredients":[{"name":"whisky","amount":"100ml"}]}]}

# unknown command
=> some random command
=> Unknown command

# disconnect
=> disconnect
=> Disconnected from the server
```

## Design

One principle of clean design is separating business logic from I/O logic. For the server, the business logic is managing the recipe store and operations on it, while the I/O logic is the network communication with clients. This separation makes the code more readable, easier to extend, and significantly simplifies unit testing.

Implement the server's business logic in `DefaultCocktailStorage`, which has a public no-arg constructor and implements:

```java
package bg.sofia.uni.fmi.mjt.cocktail.server.storage;

public interface CocktailStorage {

    /**
     * Creates a new cocktail recipe.
     *
     * @throws CocktailAlreadyExistsException if the same cocktail already exists
     */
    void createCocktail(Cocktail cocktail) throws CocktailAlreadyExistsException;

    /**
     * Retrieves all cocktail recipes.
     *
     * @return all cocktail recipes in undefined order, or an empty collection if none exist.
     */
    Collection<Cocktail> getCocktails();

    /**
     * Retrieves all cocktail recipes containing the given ingredient.
     *
     * @param ingredientName ingredient name (case-insensitive)
     * @return matching recipes in undefined order, or an empty collection if none found.
     */
    Collection<Cocktail> getCocktailsWithIngredient(String ingredientName);

    /**
     * Retrieves the cocktail recipe with the given name.
     *
     * @param name cocktail name (case-insensitive)
     * @throws CocktailNotFoundException if no cocktail with that name exists
     */
    Cocktail getCocktail(String name) throws CocktailNotFoundException;
}
```

Model a cocktail as `record Cocktail(String name, Set<Ingredient> ingredients)` and an ingredient as `record Ingredient(String name, String amount)`. Two cocktails are equal when their names match; two ingredients are equal when their names match (regardless of amount).

## Testing

- Test manually with one client, then with multiple clients connected in parallel.
- Automated tests are optional but strongly recommended — they will also help with course projects.

**Hint:** Recall the Echo Client-Server examples from the lab session. Can you evolve them into a Cocktail Recipes Client/Server?

## Project Structure

```
src/
└─ bg.sofia.uni.fmi.mjt.cocktail
    ├── client/
    │    └── CocktailClient.java
    └── server/
         ├── storage/
         │    ├── exceptions/
         │    │    ├── CocktailAlreadyExistsException.java
         │    │    └── CocktailNotFoundException.java
         │    ├── CocktailStorage.java
         │    └── DefaultCocktailStorage.java
         ├── command/
         │    ├── Command.java
         │    ├── CommandCreator.java
         │    └── CommandExecutor.java
         ├── Cocktail.java
         ├── Ingredient.java
         └── CocktailServer.java
```
