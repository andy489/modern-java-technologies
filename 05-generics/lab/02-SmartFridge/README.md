# Smart Fridge

Smart home devices are increasingly common. Build a software model of a smart fridge that:
- knows what is stored inside
- prioritises items expiring soon
- checks what ingredients are missing for a given recipe

## SmartFridge

In the package `bg.sofia.uni.fmi.mjt.smartfridge`, create class `SmartFridge` with constructor:

```java
public SmartFridge(int totalCapacity)
```

It implements `SmartFridgeAPI` (see full Javadoc in the source).

## What the Fridge Stores

Any object implementing `Storable` can be stored:

```java
public interface Storable {
    StorableType getType();
    String getName();
    LocalDate getExpiration();
    boolean isExpired();
}
```

`StorableType` is an enum: `FOOD`, `BEVERAGE`, `OTHER`.

## Recipes

A `Recipe` holds a set of `Ingredient<? extends Storable>`. Use the provided `DefaultIngredient` record:

```java
public record DefaultIngredient<E extends Storable>(E item, int quantity) implements Ingredient<E> {}
```

## Package Structure

```
src
└── bg.sofia.uni.fmi.mjt.smartfridge
    ├── exception
    │      ├── FridgeCapacityExceededException.java
    │      └── InsufficientQuantityException.java
    ├── ingredient
    │      ├── DefaultIngredient.java
    │      └── Ingredient.java
    ├── recipe
    │      └── Recipe.java
    ├── storable
    │      ├── type
    │      │     └── StorableType.java
    │      └── Storable.java
    ├── SmartFridge.java
    └── SmartFridgeAPI.java
```

> **Note:** Do not modify any of the provided interfaces.
> This task must be solved without lambda expressions or the Stream API.
