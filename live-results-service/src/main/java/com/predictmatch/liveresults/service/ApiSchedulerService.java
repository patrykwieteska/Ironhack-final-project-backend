package com.predictmatch.liveresults.service;

import com.predictmatch.liveresults.repository.FixtureRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableScheduling
@Configuration
@Slf4j
public class ApiSchedulerService implements SchedulingConfigurer {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm" );

    public static LocalDateTime lastApiCallDate;

    private static final Logger logger = LoggerFactory.getLogger( ApiSchedulerService.class );

    @Autowired
    FixtureRepository fixtureRepository;

    @Autowired
    LiveResultsService liveResultsService;


    @Transactional
    private void checkLiveUpdatePossibility()  {

        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime nextMatchDate = fixtureRepository.getNextMatchDate();
        int liveMatches = fixtureRepository.getNumberOfLiveMatches(); // TODO --> what if last match will start -->
        // ERROR?


        if((liveMatches>0 && ChronoUnit.MINUTES.between( lastApiCallDate,now) >20)
                || (ChronoUnit.MINUTES.between ( nextMatchDate ,now) >2 && now.isAfter( nextMatchDate ))) {
            try {
                liveResultsService.initData();
                logger.info("--> Live results updated!");
            } catch (JSONException | IOException e) {
                logger.error( Arrays.toString( e.getStackTrace() ) );
            }
        } else {
            logger.info("--> Live results are up-to-date. Last update date: "+lastApiCallDate.format( DATE_FORMAT ));
        }
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addTriggerTask(
                new Runnable() {
                    @Override
                    public void run() {
                        checkLiveUpdatePossibility();
                    }
                },
                new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext context) {
                        Optional<Date> lastCompletionTime =
                                Optional.ofNullable(context.lastCompletionTime());
                        Instant nextExecutionTime =
                                lastCompletionTime.orElseGet(Date::new).toInstant()
                                        .plusMillis(600000); // call every 10 minutes
                        return Date.from(nextExecutionTime);
                    }
                }
        );
    }
}
