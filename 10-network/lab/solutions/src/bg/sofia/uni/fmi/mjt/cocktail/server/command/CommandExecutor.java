package bg.sofia.uni.fmi.mjt.cocktail.server.command;

import bg.sofia.uni.fmi.mjt.cocktail.server.Cocktail;
import bg.sofia.uni.fmi.mjt.cocktail.server.Ingredient;
import bg.sofia.uni.fmi.mjt.cocktail.server.storage.CocktailStorage;
import bg.sofia.uni.fmi.mjt.cocktail.server.storage.exceptions.CocktailAlreadyExistsException;
import bg.sofia.uni.fmi.mjt.cocktail.server.storage.exceptions.CocktailNotFoundException;
import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

public class CommandExecutor {

    private static final String INVALID_ARGS_CREATE =
            "Invalid argument count: \"%s\" expects at least %d arguments. Usage: \"%s\"";
    private static final String INVALID_ARGS_GET =
            "Invalid argument count: \"%s\" expects %d arguments. Usage: \"%s\"";

    private static final String ALREADY_EXISTS_MSG =
            "{\"status\":\"ERROR\",\"errorMessage\":\"cocktail %s already exists\"}";
    private static final String NOT_FOUND_MSG =
            "{\"status\":\"ERROR\",\"errorMessage\":\"cocktail %s not found\"}";

    private static final String OK_CREATED   = "{\"status\":\"CREATED\"}";
    private static final String OK_COCKTAILS = "{\"status\":\"OK\",\"cocktails\":%s}";

    private static final String CREATE        = "create";
    private static final String GET           = "get";
    private static final String ALL           = "all";
    private static final String BY_NAME       = "by-name";
    private static final String BY_INGREDIENT = "by-ingredient";
    private static final String UNKNOWN       = "Unknown command";

    private final CocktailStorage storage;
    private final Gson gson = new Gson();

    public CommandExecutor(CocktailStorage storage) {
        this.storage = storage;
    }

    public String execute(Command cmd) {
        return switch (cmd.command()) {
            case CREATE -> createCocktail(cmd.arguments());
            case GET    -> getRecipes(cmd.arguments());
            default     -> UNKNOWN;
        };
    }

    private String createCocktail(String[] args) {
        if (args.length < 2) {
            return String.format(INVALID_ARGS_CREATE, CREATE, 2, CREATE + " <name> <ingredient>=<amount> ...");
        }

        var ingredients = new HashSet<Ingredient>();
        for (int i = 1; i < args.length; i++) {
            var parts = args[i].split("=", 2);
            ingredients.add(Ingredient.of(parts[0], parts[1]));
        }

        try {
            storage.createCocktail(Cocktail.of(args[0], ingredients));
        } catch (CocktailAlreadyExistsException e) {
            return String.format(ALREADY_EXISTS_MSG, args[0]);
        }

        return OK_CREATED;
    }

    private String getRecipes(String[] args) {
        if (args.length == 0) {
            return UNKNOWN;
        }

        return switch (args[0]) {
            case ALL -> {
                if (args.length == 1) {
                    yield String.format(OK_COCKTAILS, gson.toJson(storage.getCocktails()));
                }
                yield String.format(INVALID_ARGS_GET, GET, 1, GET + " " + ALL);
            }
            case BY_NAME -> {
                if (args.length == 2) {
                    try {
                        yield String.format(OK_COCKTAILS, gson.toJson(storage.getCocktail(args[1])));
                    } catch (CocktailNotFoundException e) {
                        yield String.format(NOT_FOUND_MSG, args[1]);
                    }
                }
                yield String.format(INVALID_ARGS_GET, GET, 2, GET + " " + BY_NAME + " <cocktail_name>");
            }
            case BY_INGREDIENT -> {
                if (args.length == 2) {
                    var matches = storage.getCocktailsWithIngredient(args[1]);
                    yield String.format(OK_COCKTAILS, matches.isEmpty() ? "[]" : gson.toJson(matches));
                }
                yield String.format(INVALID_ARGS_GET, GET, 2, GET + " " + BY_INGREDIENT + " <ingredient_name>");
            }
            default -> UNKNOWN;
        };
    }
}
