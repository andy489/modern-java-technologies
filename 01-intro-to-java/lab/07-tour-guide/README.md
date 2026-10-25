## Sightseeing Pair Score

### Task

As tourists visiting a new destination, we want to visit the best sights in our limited time. We have a list of sights and their ratings, but they are spread across the city — we can only visit two of them.

Create a public class `TourGuide` with the method:

```java
public static int getBestSightseeingPairScore(int[] places)
```

that receives an array `places` where `places[i]` is the rating of sight `i`, and returns the score of the best pair of sights. For a pair `(i, j)` where `i < j`, the score is:

```
places[i] + places[j] + i - j
```

(sum of ratings minus the distance between them).

### Examples

| Call                                                    | Result |
| :------------------------------------------------------ | :----- |
| `getBestSightseeingPairScore(new int[]{8, 1, 5, 2, 6})` | `11`   |
| `getBestSightseeingPairScore(new int[]{1, 2})`          | `2`    |

In the first example, `i = 0, j = 2`: `8 + 5 + 0 - 2 = 11`.

### Notes

- Using data structures other than arrays is **not allowed**. The task must be solved using only the knowledge from the first lecture of the course.
