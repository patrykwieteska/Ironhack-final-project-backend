package com.predictmatch.liveresults.enmus;

public enum FixtureStatus {
    PST, // Match postponed
    FT, // Match finished
    TBD, // Time to be defined
    NS ,// Not started
    HT, // Another one for live matches
    H2, // second half in progress
    H1(), // first half in progress
}
