# Netflix Recommender :popcorn:

## Problem Statement

We will build a program that provides statistics, search, and a simple *recommendation engine* based on a real *dataset* containing data about TV shows and movies on the [Netflix](https://www.netflix.com) streaming platform (currently with over 222 million subscribers worldwide). The dataset covers more than 5,400 shows and movies and is sourced from [Kaggle](https://www.kaggle.com/datasets/victorsoeiro/netflix-tv-shows-and-movies?select=titles.csv).

To simplify parsing, we have pre-processed the file. Download it [here](./resources/dataset.csv).

Each line describes one show or movie with comma-separated fields (CSV format):

`id,title,type,description,release_year,runtime,genres,seasons,imdb_id,imdb_score,imdb_votes`

**Notes:**

- The first line contains field names (header row)
- Field semantics and possible values are described on [Kaggle](https://www.kaggle.com/datasets/victorsoeiro/netflix-tv-shows-and-movies?select=titles.csv)
- The field separator is a comma (`,`)

### 1. Loading and Parsing Data

Before the actual analysis, load the data into memory.

A common first step in data analysis is data cleaning — assuming records may be malformed, missing fields, or containing unexpected values.

Implement the `record Content` with components:

```java
(String id, String title, ContentType type, String description, int releaseYear,
    int runtime, List<String> genres, int seasons, String imdbId, double imdbScore,
    double imdbVotes)
```

This record represents a single show or movie. Two content items are equal if and only if they have the same `id`.

> **Hint:** When a `record` holds a collection component (or any reference type that does not override `equals()`), the compiler-generated `equals()` compares component by component — which for collections is both slow and often incorrect. Override `equals()` explicitly to compare only `id`.

The content type (show or movie) is represented by the `enum`:

```java
package bg.sofia.uni.fmi.mjt.netflix;

public enum ContentType {
    MOVIE, SHOW
}
```

### 2. Netflix Recommender

With `Content` in place, load the data into a suitable collection, obtain a stream, and use the Stream API to compute statistics and implement search functionality.

The class providing the API is `NetflixRecommender`. A partial implementation is available [here](./resources/NetflixRecommender.java) — your task is to complete it.

#### Keyword Search

The API supports searching shows and movies by keywords from their description. Word separators are whitespace and punctuation characters (regex: `"[\\p{IsPunctuation}\\s]+"`). Each word consists of at least one character. The search is case-insensitive but matches only complete words. For example, if a description is `"An eventful dramatic sequel"`, that title should **not** match searches for `"drama"`, `"ram"`, or `"vent"`, but **should** match `"an"` or `"DRAMAtic"`.

### 3. Testing

Validate your solution with unit tests.

## Project Structure

Follow the package names exactly; otherwise the automated grader cannot test your solution.

```
src
└─ bg.sofia.uni.fmi.mjt.netflix
    ├── Content.java
    ├── ContentType.java
    ├── NetflixRecommender.java
    └── (...)
test
└─ bg.sofia.uni.fmi.mjt.netflix
    └── (...)
```

Submit a zip archive of the `src` and `test` folders.
