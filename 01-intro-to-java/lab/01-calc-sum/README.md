## Sum of a Series

### Task

Create a public class `CalcSum` with two methods — one using iteration and one using recursion:

```java
public static double sumIter(int n, int m)
public static double sumRec(int n, int m)
```

Each method, for given integers `n` and `m`, computes the real number — the sum:

```
1/m + 2/m² + 3/m³ + ... + n/mⁿ
```

### Examples

| Call                   | Result                |
| :--------------------- | :-------------------- |
| `sumIter(10, 2)`       | `1.9980468750000002`  |
| `sumRec(10, 2)`        | `1.9980468750000002`  |

### Notes

- The task must be solved using only the knowledge from the first lecture of the course.
- The recursive version must call itself.
