# Stock Exchange

## Problem

When is the right time to buy and sell stocks in order to maximise profit?

Create a public class `StockExchange` with the following method:

```java
public static int maxProfit(int[] prices)
```

The method determines the maximum profit achievable from buying and selling stocks. It accepts an array of integers representing predicted stock prices for a company on consecutive future days: the `i`-th element is the price on day `i` after today.

At most **two transactions** may be completed within the given time window. Parallel transactions are not allowed — you must sell your shares before you can buy again.

## Examples

| Input | Result | Explanation |
|:---|:---|:---|
| `{7, 1, 5, 3, 6, 4}` | `7` | Buy on day 2 (price = 1), sell on day 3 (price = 5), profit = 4. Then buy on day 4 (price = 3), sell on day 5 (price = 6), profit = 3. Total = 7. |
| `{1, 2, 3, 4, 5}` | `4` | Buy on day 1 (price = 1), sell on day 5 (price = 5), profit = 4. Note: you cannot buy on day 1, buy again on day 2, and sell later — that would be two overlapping transactions. You must sell before buying again. |
| `{7, 6, 4, 3, 1}` | `0` | There is no profitable move. Maximum profit is 0 (do not trade). |
