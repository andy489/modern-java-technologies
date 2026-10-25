## Longest Common Prefix

### Task

Create a public class `PrefixExtractor` with the method:

```java
public static String getLongestCommonPrefix(String[] words)
```

that returns the longest common prefix of the strings in `words`. If the words have no common prefix, the array is empty, or `null` is passed, the method returns an empty string. A word is any sequence of characters.

### Examples

| Call                                                               | Result  |
| :----------------------------------------------------------------- | :------ |
| `getLongestCommonPrefix(new String[]{"flower", "flow", "flight"})` | `"fl"`  |
| `getLongestCommonPrefix(new String[]{"dog", "racecar", "car"})`    | `""`    |
| `getLongestCommonPrefix(new String[]{"cat"})`                      | `"cat"` |

### Notes

- Using data structures other than arrays is **not allowed**. The task must be solved using only the knowledge from the first lecture of the course.
