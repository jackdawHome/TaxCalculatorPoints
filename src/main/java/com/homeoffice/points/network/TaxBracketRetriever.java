package com.homeoffice.points.network;

import com.homeoffice.points.model.WebResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TaxBracketRetriever {
    private static final Logger log = LogManager.getLogger(TaxBracketRetriever.class);

    private static HttpClient client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL).build();

    /**
     * Sends request to a server
     * @param url
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static WebResponse get(String url) throws IOException, InterruptedException {

        if (url == null) {
            throw new IllegalArgumentException("Incorrect url value.");
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        log.info(String.valueOf(response.statusCode()));
        log.info(response.body());

        return new WebResponse(response.statusCode(), response.body());
    }
}
