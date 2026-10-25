package bg.sofia.uni.fmi.mjt.smartfridge;

import bg.sofia.uni.fmi.mjt.smartfridge.exception.FridgeCapacityExceededException;
import bg.sofia.uni.fmi.mjt.smartfridge.exception.InsufficientQuantityException;
import bg.sofia.uni.fmi.mjt.smartfridge.ingredient.DefaultIngredient;
import bg.sofia.uni.fmi.mjt.smartfridge.ingredient.Ingredient;
import bg.sofia.uni.fmi.mjt.smartfridge.recipe.Recipe;
import bg.sofia.uni.fmi.mjt.smartfridge.storable.Baverage;
import bg.sofia.uni.fmi.mjt.smartfridge.storable.Food;
import bg.sofia.uni.fmi.mjt.smartfridge.storable.Storable;
import bg.sofia.uni.fmi.mjt.smartfridge.storable.StorableByExpirationComparator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class SmartFridge implements SmartFridgeAPI {

    private final int totalCapacity;
    private int currentCapacity;
    private final Map<String, Queue<Storable>> storage;

    public SmartFridge(int totalCapacity) {
        this.totalCapacity = totalCapacity;
        this.currentCapacity = 0;
        this.storage = new HashMap<>();
    }

    @Override
    public <E extends Storable> void store(E item, int quantity) throws FridgeCapacityExceededException {
        if (item == null || quantity <= 0) {
            throw new IllegalArgumentException("item must not be null and quantity must be positive");
        }
        if (currentCapacity + quantity > totalCapacity) {
            throw new FridgeCapacityExceededException("Fridge capacity exceeded");
        }

        storage.putIfAbsent(item.getName(), new PriorityQueue<>(new StorableByExpirationComparator()));
        var queue = storage.get(item.getName());
        for (int i = 0; i < quantity; i++) {
            queue.add(item);
        }
        currentCapacity += quantity;
    }

    @Override
    public List<? extends Storable> retrieve(String itemName) {
        validateItemName(itemName);

        if (!storage.containsKey(itemName)) {
            return List.of();
        }

        var retrieved = new ArrayList<>(storage.get(itemName));
        currentCapacity -= retrieved.size();
        storage.remove(itemName);
        return retrieved;
    }

    @Override
    public List<? extends Storable> retrieve(String itemName, int quantity) throws InsufficientQuantityException {
        validateItemName(itemName);
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }

        var queue = storage.get(itemName);
        if (queue == null || queue.size() < quantity) {
            throw new InsufficientQuantityException("Insufficient quantity for: " + itemName);
        }

        var retrieved = new ArrayList<Storable>(quantity);
        for (int i = 0; i < quantity; i++) {
            retrieved.add(queue.poll());
        }
        currentCapacity -= quantity;
        return retrieved;
    }

    @Override
    public int getQuantityOfItem(String itemName) {
        validateItemName(itemName);
        var items = storage.get(itemName);
        return items == null ? 0 : items.size();
    }

    @Override
    public Iterator<Ingredient<? extends Storable>> getMissingIngredientsFromRecipe(Recipe recipe) {
        if (recipe == null) {
            throw new IllegalArgumentException("recipe must not be null");
        }

        var missing = new ArrayList<Ingredient<? extends Storable>>();
        for (var ingredient : recipe.getIngredients()) {
            var foods = storage.get(ingredient.item().getName());
            int available = 0;
            if (foods != null) {
                for (var food : foods) {
                    if (!food.isExpired()) {
                        available++;
                    }
                }
            }
            if (available < ingredient.quantity()) {
                missing.add(new DefaultIngredient<>(ingredient.item(), ingredient.quantity() - available));
            }
        }
        return missing.iterator();
    }

    @Override
    public List<? extends Storable> removeExpired() {
        var expired = new ArrayList<Storable>();
        for (var queue : storage.values()) {
            while (queue != null && !queue.isEmpty() && queue.peek().isExpired()) {
                expired.add(queue.poll());
                currentCapacity--;
            }
        }
        return expired;
    }

    public static void main(String... args) throws FridgeCapacityExceededException {
        var flour = new Food("Flour", LocalDate.now().plusMonths(8));
        var bakingPowder = new Food("Baking powder", LocalDate.now().plusMonths(3));
        var whiteSugar = new Food("White sugar", LocalDate.now().plusMonths(4));
        var salt = new Food("Salt", LocalDate.now().plusYears(1));
        var milk = new Baverage("Milk", LocalDate.now().plusWeeks(1));
        var butter = new Food("Butter", LocalDate.now().plusWeeks(2));
        var egg = new Food("Egg", LocalDate.now().plusDays(6));

        var fridge = new SmartFridge(13);
        System.out.println("Flour quantity before: " + fridge.getQuantityOfItem("Flour"));
        fridge.store(flour, 2);
        System.out.println("Flour quantity after:  " + fridge.getQuantityOfItem("Flour"));
        fridge.store(bakingPowder, 3);
        fridge.store(whiteSugar, 1);
        fridge.store(salt, 1);
        fridge.store(milk, 1);
        fridge.store(butter, 3);
        fridge.store(egg, 2);
    }

    private void validateItemName(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            throw new IllegalArgumentException("itemName must not be null, empty, or blank");
        }
    }
}
