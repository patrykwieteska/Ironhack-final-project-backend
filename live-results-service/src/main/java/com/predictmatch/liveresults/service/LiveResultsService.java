package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.dao.*;
import com.predictmatch.liveresults.repository.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class LiveResultsService {


    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm" );

    @Autowired
    ApiService apiService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private LeagueStandingRepository leagueStandingRepository;

    @Autowired
    private FixtureRepository fixtureRepository;

    @Transactional
    public void initData(boolean isLive) throws JSONException, IOException {
        String standingsResponse;
        String fixturesResponse;
        if(isLive) {
            standingsResponse = apiService.getLiveStandings();
            fixturesResponse = apiService.getLiveFixtures();
        } else {
            standingsResponse = apiService.getTestStandings();
            fixturesResponse = apiService.getTestFixtures();
        }



        saveLeague( standingsResponse );
        saveTeam( standingsResponse );
        saveLeagueStandings( standingsResponse );
        saveFixtures( fixturesResponse );

    }

    // TODO add exception handling
    private void saveFixtures(String fixturesResponse) throws JSONException {
        JSONObject jsonObject = new JSONObject( fixturesResponse );

        JSONArray jsonArray = jsonObject.getJSONArray( "response" );

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject fixtureObject = jsonArray.getJSONObject( i ).getJSONObject( "fixture" );
            JSONObject teamsObject = jsonArray.getJSONObject( i ).getJSONObject( "teams" );
            JSONObject scoresObject = jsonArray.getJSONObject( i ).getJSONObject( "score" );
            JSONObject leagueObject = jsonArray.getJSONObject( i ).getJSONObject( "league" );

            int round= Integer.parseInt(  leagueObject.getString( "round" ).substring(leagueObject.getString(
                    "round" ).indexOf( "-" )+1).trim());

            Fixture fixture = new Fixture(
                    fixtureObject.getLong( "id" ),
                    LocalDateTime.parse( fixtureObject.getString( "date" ).substring( 0, 16 ), DATE_FORMAT ),
                    fixtureObject.getString( "timezone" ),
                    fixtureObject.getString( "referee" ),
                    fixtureObject.getJSONObject( "venue" ).getString( "name" ),
                    fixtureObject.getJSONObject( "venue" ).getString( "city" ),
                    fixtureObject.getJSONObject( "status" ).getString( "short" ),
                    teamsObject.getJSONObject( "home" ).optLong( "id" ),
                    teamsObject.getJSONObject( "away" ).optLong( "id" ),
                    teamsObject.getJSONObject( "home" ).optBoolean( "winner" ),
                    teamsObject.getJSONObject( "away" ).optBoolean( "winner" ),
                    scoresObject.getJSONObject( "halftime" ).optInt( "home" ),
                    scoresObject.getJSONObject( "halftime" ).optInt( "away" ),
                    scoresObject.getJSONObject( "fulltime" ).optInt( "home" ),
                    scoresObject.getJSONObject( "fulltime" ).optInt( "away" ),
                    round,
                    leagueObject.getLong( "id" )
            );

            fixtureRepository.save( fixture );
        }

    }

    private void saveLeague(String standingsResponse) throws JSONException {
        JSONObject standingsResult = new JSONObject( standingsResponse );

        JSONObject leagueObject = standingsResult
                .getJSONArray( "response" )
                .getJSONObject( 0 )
                .getJSONObject( "league" );


        League league = new League(
                leagueObject.getLong( "id" ),
                leagueObject.getString( "name" ),
                leagueObject.getInt( "season" ),
                leagueObject.getString( "logo" ),
                leagueObject.getString( "country" ),
                leagueObject.getString( "flag" )

        );

        leagueRepository.save( league );
    }

    private void saveLeagueStandings(String standingsResponse) throws JSONException {
        JSONObject result = new JSONObject( standingsResponse );

        JSONArray standingsArray = result
                .getJSONArray( "response" )
                .getJSONObject( 0 )
                .getJSONObject( "league" )
                .getJSONArray( "standings" )
                .getJSONArray( 0 );

        for (int i = 0; i < standingsArray.length(); i++) {

            JSONObject standing = standingsArray.getJSONObject( i );
            LeagueStanding leagueStanding = new LeagueStanding(
                    standing.getLong( "rank" ),
                    standing.getJSONObject( "team" ).getLong( "id" ),
                    standing.getInt( "points" ),
                    standing.getJSONObject( "all" ).getInt( "played" ),
                    standing.getJSONObject( "all" ).getInt( "win" ),
                    standing.getJSONObject( "all" ).getInt( "draw" ),
                    standing.getJSONObject( "all" ).getInt( "lose" ),
                    standing.getJSONObject( "all" ).getJSONObject( "goals" ).getInt( "for" ),
                    standing.getJSONObject( "all" ).getJSONObject( "goals" ).getInt( "against" ),
                    standing.getInt( "goalsDiff" ),
                    standing.getString( "status" ),
                    result.getJSONArray( "response" ).getJSONObject( 0 ).getJSONObject( "league" ).getLong( "id" )
            );

            leagueStandingRepository.save( leagueStanding );
        }
    }

    private void saveTeam(String standingsResponse) throws JSONException {
        JSONObject jsonObject = new JSONObject( standingsResponse );

        JSONArray jsonArray = jsonObject
                .getJSONArray( "response" )
                .getJSONObject( 0 )
                .getJSONObject( "league" )
                .getJSONArray( "standings" )
                .getJSONArray( 0 );


        for (int i = 0; i < jsonArray.length(); i++) {

            Team team = new Team(
                    jsonArray.getJSONObject( i ).getJSONObject( "team" ).getLong( "id" ),
                    jsonArray.getJSONObject( i ).getJSONObject( "team" ).getString( "name" ),
                    jsonArray.getJSONObject( i ).getJSONObject( "team" ).getString( "logo" ),
                    jsonArray.getJSONObject( i ).getString( "form" ),
                    jsonObject.getJSONArray( "response" ).getJSONObject( 0 ).getJSONObject( "league" ).optLong( "id" )
            );

            teamRepository.save( new Team(
                    Long.parseLong( jsonArray.getJSONObject( i ).getJSONObject( "team" ).getString( "id" ) ),
                    jsonArray.getJSONObject( i ).getJSONObject( "team" ).getString( "name" ),
                    jsonArray.getJSONObject( i ).getJSONObject( "team" ).getString( "logo" ),
                    jsonArray.getJSONObject( i ).getString( "form" ),
                    jsonObject.getJSONArray( "response" ).getJSONObject( 0 ).getJSONObject( "league" ).optLong( "id" )
            ) );
        }

    }
}
