## Minimum Number of Swaps

### Task

Create a public class `MinSwaps` with the method:

```java
public static int minSwaps(String s1, String s2)
```

that accepts two binary strings of equal length with an equal number of zeros and ones, and returns the minimum number of swaps needed to transform `s1` into `s2`.

A swap is an operation where any two bits in the string exchange positions.

### Examples

| Call                               | Result |
| :--------------------------------- | :----- |
| `minSwaps("1100", "1001")`         | `1`    |
| `minSwaps("110011", "010111")`     | `1`    |
| `minSwaps("10011001", "01100110")` | `4`    |

### Hint

The number of positions where the two strings differ is always even (since the number of zeros and ones is the same). Each swap can fix exactly two differences.

### Notes

- The task must be solved using only the knowledge from the first lecture of the course.
