package com.predictmatch.predictionservice.predictionservice.service;

import com.predictmatch.predictionservice.predictionservice.dto.fixture.FixtureDto;
import com.predictmatch.predictionservice.predictionservice.proxy.FixtureProxy;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class FixtureService {

    @Autowired
    FixtureProxy fixtureProxy;

    @Retry( name="getFixture", fallbackMethod = "getUnknownFixture")
    public FixtureDto getFixtureById(Long fixtureId) {

        Optional<FixtureDto> fixture = Optional.ofNullable(fixtureProxy.findFixtureById( fixtureId).getBody());

        if(fixture.isEmpty())
            throw new EntityNotFoundException("Error during making prediction - not found fixture with id: "+fixtureId);
        return fixture.get();

    }

    public FixtureDto getUnknownFixture(Long fixtureId, Exception e) throws ServiceUnavailableException {
        throw new ServiceUnavailableException("Live results service is unavailable. Please contact with admin");
    }
}
