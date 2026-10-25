package bg.sofia.uni.fmi.mjt.news.feed.response;

import java.util.List;

public record ResponseObj(
        String status,
        String code,
        String message,
        int totalResults,
        List<ArticleObj> articles) {

    public String getMsg() {
        return message;
    }
}
