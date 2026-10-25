package bg.sofia.uni.fmi.mjt.news.feed.http;

import bg.sofia.uni.fmi.mjt.news.feed.categories.NewsCategory;
import bg.sofia.uni.fmi.mjt.news.feed.country.codes.CountryCode;
import bg.sofia.uni.fmi.mjt.news.feed.exceptions.NoKeyWordException;
import bg.sofia.uni.fmi.mjt.news.feed.exceptions.NullApiKeyException;

public sealed interface AdditionallyRestrictedTopHeadlinesService permits TopHeadlinesService {

    /**
     * Creates a request with only an API key and at least one keyword.
     *
     * @param apiKey   the API key for authenticating with the News API
     * @param keyWords one or more search keywords
     * @throws NullApiKeyException    if {@code apiKey} is null
     * @throws InvalidApiKeyException if {@code apiKey} is blank
     * @throws NoKeyWordException     if no non-blank keyword is provided
     */
    TopHeadlinesRequest makeBaseRequest(String apiKey, String... keyWords);

    /**
     * Creates a request filtered by news category.
     *
     * @param apiKey   the API key
     * @param category the news category to filter by
     * @param keyWords one or more search keywords
     * @throws NullApiKeyException    if {@code apiKey} is null
     * @throws InvalidApiKeyException if {@code apiKey} is blank
     * @throws NoKeyWordException     if no non-blank keyword is provided
     */
    TopHeadlinesRequest makeRequestWithCategory(String apiKey, NewsCategory category, String... keyWords);

    /**
     * Creates a request filtered by country.
     *
     * @param apiKey   the API key
     * @param country  the country code to filter news by
     * @param keyWords one or more search keywords
     * @throws NullApiKeyException    if {@code apiKey} is null
     * @throws InvalidApiKeyException if {@code apiKey} is blank
     * @throws NoKeyWordException     if no non-blank keyword is provided
     */
    TopHeadlinesRequest makeRequestWithCountry(String apiKey, CountryCode country, String... keyWords);

    /**
     * Creates a request filtered by both country and category.
     *
     * @param apiKey   the API key
     * @param country  the country code to filter news by
     * @param category the news category to filter by
     * @param keyWords one or more search keywords
     * @throws NullApiKeyException    if {@code apiKey} is null
     * @throws InvalidApiKeyException if {@code apiKey} is blank
     * @throws NoKeyWordException     if no non-blank keyword is provided
     */
    TopHeadlinesRequest makeRequestCountryAndCategory(
            String apiKey, CountryCode country, NewsCategory category, String... keyWords);

    /**
     * Creates a fully parameterised request.
     *
     * @param apiKey   the API key
     * @param category the news category to filter by
     * @param country  the country code to filter news by
     * @param pageSize maximum number of articles per page (1–50)
     * @param page     page number (1–3)
     * @param keyWords one or more search keywords
     * @throws NullApiKeyException    if {@code apiKey} is null
     * @throws InvalidApiKeyException if {@code apiKey} is blank
     * @throws NoKeyWordException     if no non-blank keyword is provided
     */
    TopHeadlinesRequest makeFullRequest(
            String apiKey, NewsCategory category, CountryCode country,
            int pageSize, int page, String... keyWords);
}
