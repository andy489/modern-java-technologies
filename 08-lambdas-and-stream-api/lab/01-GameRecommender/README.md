# Game Recommender :video_game:

## Problem Statement

We will build a program that analyses and provides statistics over a real *dataset* containing data about the most popular video games for the period 1995–2021. The dataset holds over 17,000 records scraped from [Metacritic](https://www.metacritic.com/browse/games/score/metascore/all/all/filtered?page=0) and is sourced from [Kaggle](https://www.kaggle.com/deepcontractor/top-video-games-19952021-metacritic), Google's online platform for machine learning and data science.

To simplify parsing, we have pre-processed the file. Download it [here](./resources/video-games-data.zip).

Each line of the file describes one game with comma-separated fields (standard CSV format):

`name,platform,release_date,summary,meta_score,user_review`

**Notes:**

- The first line contains field names (header row)
- Field semantics and possible values are described on [Kaggle](https://www.kaggle.com/deepcontractor/top-video-games-19952021-metacritic)
- The field separator is a comma (`,`)
- Some fields may contain characters from non-Latin scripts. To keep them human-readable, specify `StandardCharsets.UTF_8` as the encoding when creating the input stream for the dataset

### 1. Loading and Parsing Data

Before the actual analysis, we need to load the data into memory.

A common first step in data analysis and machine learning is data cleaning and preparation — assuming that records may be malformed, missing expected fields, or containing unexpected values.

Implement a class responsible for parsing a single CSV line into a Java object.

### 2. Statistics

Once parsing is in place, load the data into an appropriate collection, obtain a stream from it, and use the Stream API to compute various statistics declaratively.

The class providing the statistics API is `GameRecommender`. A partial implementation is available [here](./resources/GameRecommender.java) — your task is to complete it.

### 3. Testing

Validate your solution with unit tests covering at least **80% code coverage**.

## Project Structure

Follow the package names exactly; otherwise the automated grader cannot test your solution.

```
src
└─ bg.sofia.uni.fmi.mjt.game.recommender
    ├── GameRecommender.java
    └── (...)
test
└─ bg.sofia.uni.fmi.mjt.game.recommender
    └── (...)
```

Submit a zip archive of the `src` and `test` folders.
