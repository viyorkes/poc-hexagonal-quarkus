package com.poc.quarkus.network.domain.policy;

import com.poc.quarkus.network.domain.entity.Event;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public interface EventParser {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneId.of("UTC"));

    default Event parseEvent(String event) {
        return null;
    }
}