package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.config.ApiProperties;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class ApiService {

    private static Logger logger = LoggerFactory.getLogger( ApiService.class);

    @Value("${api.football.base.url}")
    private String baseurl;
    @Value("${api.football.api.key}")
    private String apiKey;
    @Value("${api.football.league.id}")
    private String leagueId;


    public String getDataFromFootballApi(String url) throws IOException {

        logger.info( "Outgoing request: " +baseurl + url);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url( baseurl + url)
                .get()
                .addHeader("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", apiKey)
                .build();

        Response response = client.newCall(request).execute();

        logger.info("Incoming response: {}",response);

        return response.body().string();
    }

    //Live fixtures
    public String getLiveFixtures() throws IOException {
        return getDataFromFootballApi("/fixtures?league="+leagueId+"&season=2021");
    }

    //Live standings
    public String getLiveStandings() throws IOException {
        return getDataFromFootballApi("/standings?season=2021&league="+leagueId);
    }


    //Test fixtures
    public String getTestFixtures() throws IOException {


            return Files.readString (Paths.get("live-results-service/src/main/resources/static/fixtures.json"));

    }
    // Test standings
    public String getTestStandings() throws IOException {
            return Files.readString (Paths.get("live-results-service/src/main/resources/static/standings.json"));
    }
}
