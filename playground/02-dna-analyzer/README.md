# DNA Analyzer

*Inspired by an algorithms course at the [Department of Computer Science](https://www.cs.princeton.edu), [Princeton University](https://www.princeton.edu).*

## Problem

Implement a detector that finds the **longest repeating subsequence** of characters in a given (potentially very long) string. For example, `"abra"` is the longest repeating subsequence in `"abracadabra"`. If two or more repeating subsequences share the same maximum length, the detector may return any one of them.

### Background

This seemingly academic problem emerged from real challenges in natural language processing and cryptography. Today it has revolutionary applications in molecular biology. DNA — viewed as a sequence of nucleobases (adenine, thymine, guanine, cytosine) — contains a remarkable number of repeating subsequences that play a critical role in various biological functions and in species evolution.

> **Note:** The brute-force solution is relatively straightforward but has O(N³) time complexity. If you complete it and want to go deeper into algorithms, try solving the problem with a faster approach. Finding efficient algorithms for redundancy detection in character sequences is an active area of research, as such algorithms could help scientists unravel some of the great mysteries of molecular biology.

Create a public class `DNAAnalyzer` with the following method:

```java
public static String longestRepeatingSequence(String dna)
```

The method accepts a string and returns the longest repeating subsequence of characters found within it.

## Examples

| Call | Result |
|:---|:---|
| `longestRepeatingSequence("abracadabra")` | `"abra"` |
| `longestRepeatingSequence("ATACTCGGTACTCT")` | `"TACTC"` |
