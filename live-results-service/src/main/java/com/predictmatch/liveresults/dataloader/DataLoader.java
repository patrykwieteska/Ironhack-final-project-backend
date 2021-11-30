package com.predictmatch.liveresults.dataloader;

import com.predictmatch.liveresults.config.ApiProperties;
import com.predictmatch.liveresults.service.LiveResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {


    @Value("${api.football.live}")
    private boolean isLive;

    @Autowired
    LiveResultsService liveResultsService;

    public DataLoader() {
    }

    @Override
    public void run(String... args) throws Exception {
        liveResultsService.initData( isLive );
    }
}
