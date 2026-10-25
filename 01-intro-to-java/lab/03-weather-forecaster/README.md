## Weather Forecaster

### Task

We have a weather forecast for the coming days as an array of daily maximum temperatures (integers). For each day, we want to know how many days we need to wait until a warmer day. If no warmer future day exists, the result is 0.

Create a public class `WeatherForecaster` with the method:

```java
public static int[] getsWarmerIn(int[] temperatures)
```

that takes an array of forecast temperatures and returns an array where the `i`-th element is the number of days until the next day with a higher maximum temperature.

### Examples

| Call                                                | Result                     |
| :-------------------------------------------------- | :------------------------- |
| `getsWarmerIn(new int[]{3, 4, 5, 1, -1, 2, 6, 3})` | `[1, 1, 4, 2, 1, 1, 0, 0]` |
| `getsWarmerIn(new int[]{3, 4, 5, 6})`               | `[1, 1, 1, 0]`             |
| `getsWarmerIn(new int[]{3, 6, 9})`                  | `[1, 1, 0]`                |

### Bonus Challenge

Can you solve this in linear time O(N)?

### Notes

- Using data structures other than arrays is **not allowed**. The task must be solved using only the knowledge from the first lecture of the course.
