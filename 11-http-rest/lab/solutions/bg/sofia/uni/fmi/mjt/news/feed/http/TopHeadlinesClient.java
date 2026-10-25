package bg.sofia.uni.fmi.mjt.news.feed.http;

import bg.sofia.uni.fmi.mjt.news.feed.exceptions.BadGatewayException;
import bg.sofia.uni.fmi.mjt.news.feed.exceptions.BadRequestException;
import bg.sofia.uni.fmi.mjt.news.feed.exceptions.InternalServerErrorException;
import bg.sofia.uni.fmi.mjt.news.feed.exceptions.TooManyRequestsException;
import bg.sofia.uni.fmi.mjt.news.feed.exceptions.UnauthorizedException;
import bg.sofia.uni.fmi.mjt.news.feed.response.ResponseObj;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.HttpURLConnection.HTTP_BAD_GATEWAY;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

public class TopHeadlinesClient {
    private static final int HTTP_BAD_REQUEST = 400;
    private static final int HTTP_TOO_MANY_REQUESTS = 429;
    private static final int HTTP_INTERNAL_SERVER_ERROR = 500;

    private final HttpClient client;
    private final Gson gson = new Gson();

    public TopHeadlinesClient(HttpClient client) {
        this.client = client;
    }

    public ResponseObj sendRequest(TopHeadlinesRequest request) throws IOException, InterruptedException {
        var httpRequest = HttpRequest.newBuilder(URI.create(request.toString())).build();
        var response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        var responseObj = gson.fromJson(response.body(), ResponseObj.class);

        switch (response.statusCode()) {
            case HTTP_BAD_REQUEST -> throw new BadRequestException(responseObj.getMsg());
            case HTTP_UNAUTHORIZED -> throw new UnauthorizedException(responseObj.getMsg());
            case HTTP_BAD_GATEWAY -> throw new BadGatewayException(responseObj.getMsg());
            case HTTP_TOO_MANY_REQUESTS -> throw new TooManyRequestsException(responseObj.getMsg());
            case HTTP_INTERNAL_SERVER_ERROR -> throw new InternalServerErrorException(responseObj.getMsg());
        }

        return responseObj;
    }
}
