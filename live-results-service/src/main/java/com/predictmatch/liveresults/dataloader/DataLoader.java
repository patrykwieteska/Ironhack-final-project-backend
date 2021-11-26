package com.predictmatch.liveresults.dataloader;

import com.predictmatch.liveresults.service.LiveResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    LiveResultsService liveResultsService;

    @Override
    public void run(String... args) throws Exception {
        liveResultsService.initData();
    }
}
