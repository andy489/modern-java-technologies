# News Feed

This task focuses on HTTP, REST, and Design Patterns — the last topics of the course.

Unlike previous tasks, there are no automatic reference tests, pre-defined interfaces to implement, or a fixed project structure — you have full freedom regarding design, implementation, and testing.

## Problem Description

Create a program that allows searching for news by various criteria.

The [REST API](https://newsapi.org/) used to search for news is public, free, and requires an API Key that you can obtain after [registration](https://newsapi.org/register).

The required endpoint is `/v2/top-headlines`, documented [here](https://newsapi.org/docs/endpoints/top-headlines).

The API has rate limits — you can make **up to 100 requests per day**, so plan and optimise your usage while developing and testing.

## Design

This time you define the abstractions you use. The only requirement is to apply your knowledge of design patterns (use at least one) — the task lends itself well in this regard.

## Functionality

The criteria by which news can be searched are:

- keywords (required parameter)
- category
- country

As you can see in the [News API documentation](https://newsapi.org/docs), it supports required and optional parameters — your implementation must work the same way. For example, it must allow searching by keywords and category, but not by country alone.

### Pagination

When an API can return a large number of results, it typically does not do so all at once (for performance reasons). Instead it uses *pagination* — returning results in parts (pages): first page, then second, etc.

Implement support for reading **up to 3 pages**, with **up to 50 articles** on each page.

> **Important:** Keep the 100 requests-per-day limit in mind when implementing and testing the pagination feature.

### Error Handling

When sending a request to the server, processing is not always successful. When something goes wrong, the server returns an appropriate status code and message — handle these. Model this functionality using custom checked exceptions.

The different errors that can be returned are documented [here](https://newsapi.org/docs/errors).

## Testing

You are responsible for comprehensive testing of your solution. Aim for high code coverage and cover all scenarios your program supports, including edge cases that might seem trivial. To achieve high coverage, you may need to use Mockito.

## Submission

Archive the `src` and `test` directories into a **zip** file and upload it to the corresponding assignment in the grader.
