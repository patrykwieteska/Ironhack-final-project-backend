package com.predictmatch.liveresults.service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApiService {

    private static final String API_FOOTBALL_BASE_URL="https://api-football-v1.p.rapidapi.com/v3";
    private static final String API_KEY = "6c83610d02mshefb35612aa590fep14330fjsn826d5828154f";

    public static String getDataFromFootballApi(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_FOOTBALL_BASE_URL + url)
                .get()
                .addHeader("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", API_KEY)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String getLiveFixtures() throws IOException {
        return getDataFromFootballApi("/fixtures?league=106&season=2021");
    }

    public static String getLiveStandings() throws IOException {
        return getDataFromFootballApi("/standings?season=2021&league=106");
    }


    //Test fixtures
    public static String getTestFixtures() throws IOException {


            return Files.readString (Paths.get("live-results-service/src/main/resources/static/fixtures.json"));

    }
    // Test standings
    public static String getTestStandings() throws IOException {
            return Files.readString (Paths.get("live-results-service/src/main/resources/static/standings.json"));
    }
}
