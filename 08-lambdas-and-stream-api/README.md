# Lambdas & Stream API

## Topics

### Lambda Expressions & Functional Interfaces
- Functional interfaces — motivation (named class → anonymous class → lambda)
- Lambda expression syntax and examples
- Pure functions vs. functions with side effects
- Higher-order functions
- Standard functional interfaces — `Supplier`, `Consumer`, `Predicate`, `Function`
- Variable capture and effectively-final variables
- The `this` keyword inside lambdas
- Method references — static, instance, constructor

### Stream API
- `java.util.stream` overview
- Creating streams — from collections, arrays, strings, files, directories, random numbers, iteration, `Supplier`
- Primitive streams — `IntStream`, `LongStream`, `DoubleStream`; statistical methods
- Concatenating streams
- Two kinds of operations — intermediate vs. terminal
- Stream pipeline execution — lazy evaluation
- Intermediate operations — `filter`, `limit`, `distinct`, `skip`, `dropWhile`, `takeWhile`, `map`, `flatMap`, `sorted`
- Terminal operations — `forEach`, `reduce`, `collect`, `count`, short-circuiting (`findFirst`, `anyMatch`, `allMatch`, `noneMatch`)
- Collectors — `toList()`, `toSet()`, `joining`, `groupingBy`, `partitioningBy`, `counting`
- `java.util.Optional`
- Stream characteristics — ordered, distinct, sorted, sized
- Stateless vs. stateful operations
- Parallel streams — recommendations and pitfalls

## Lecture

[slides.md](lecture/slides.md)

## Lab

[lab/](lab/)
