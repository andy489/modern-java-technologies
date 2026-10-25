## Mountain Array

### Task

We call an integer array `array` a _mountain array_ if and only if:
- it has at least three elements
- there exists an element such that:
  - all elements up to and including it form a strictly increasing sequence
  - all elements from it to the end form a strictly decreasing sequence

Create a public class `ArrayAnalyzer` with the method:

```java
public static boolean isMountainArray(int[] array)
```

that determines whether the given `array` is a _mountain array_.

<img src="https://github.com/fmi/java-course/blob/master/01-intro-to-java/lecture/images/mountain-array.png" style="width: 50%; height: 50%" alt="Mountain array">

### Examples

| Call                                     | Result  |
| :--------------------------------------- | :------ |
| `isMountainArray(new int[]{2, 1})`       | `false` |
| `isMountainArray(new int[]{3, 5, 5})`    | `false` |
| `isMountainArray(new int[]{0, 3, 2, 1})` | `true`  |

### Notes

- Using data structures other than arrays is **not allowed**. The task must be solved using only the knowledge from the first lecture of the course.
