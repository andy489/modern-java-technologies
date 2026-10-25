package bg.sofia.uni.fmi.mjt.news.feed.response;

public record ArticleObj(
        Source source,
        String author,
        String title,
        String description,
        String url,
        String urlToImage,
        String publishedAt,
        String content) {}
