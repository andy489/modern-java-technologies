# Unit Testing and Mocking

In professional software development, it is very common to not only write code from scratch, but also to maintain, fix, or write tests for existing code.

The goal of this exercise is to create and run JUnit tests against an existing implementation.

## MyFitnessPal :muscle:

We will look at a module from a simplified version of [MyFitnessPal](https://www.myfitnesspal.com): an application that tracks the calories you consume each day. You log what you eat at breakfast, lunch, dinner, or as a snack — specifying the food and quantity — and the application calculates your calorie intake automatically. Such applications rely on a database or web service that provides nutritional information (macronutrients: carbohydrates, fats, and proteins) for a given food, from which the caloric value is computed automatically.

### Existing implementation

In [./src](./src) you will find an implementation of the `FoodDiary` interface, which represents a daily food diary. It depends on *some* implementation of the `NutritionInfoAPI` interface to retrieve macronutrient information for a given food. In a real system, such an implementation would rely on a database or web service. Our focus, however, is to automatically test the food diary implementation in isolation, without caring about how the nutrition information API is implemented — we assume it works correctly.

#### Important

:warning: The implementation contains some bugs that you need to find and fix in the process of writing tests. To make the process effective, first write a test for a scenario, then fix the bug you discovered with it.

:warning: No implementation of the `NutritionInfoAPI` interface is provided — instead, use *Mockito* in your unit tests to mock this API. Alternatively, you may write a simple stub for it.

Your project must have the following structure:

```
src
└─ bg/sofia/uni/fmi/mjt/myfitnesspal/
    ├─ diary
    |    ├─ DailyFoodDiary.java
    |    ├─ FoodDiary.java
    |    ├─ FoodEntry.java
    |    ├─ FoodEntryProteinContentComparator.java
    |    └─ Meal.java
    ├─ exception
    |    └─ UnknownFoodException.java
    └─ nutrition
         ├─ MacroNutrient.java
         ├─ NutritionInfo.java
         └─ NutritionInfoAPI.java
test
└─ bg/sofia/uni/fmi/mjt/myfitnesspal/
    └─ (...)
```

Submit a single `.zip` archive containing both the `src` and `test` directories.

Good luck, and don't hesitate to ask questions! :star:
