package com.poc.quakus.network.domain.service;

import com.poc.quakus.network.domain.entity.Event;
import com.poc.quakus.network.domain.vo.ParsePolicyType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventSearch {

    public List<Event> retrieveEvents(List<String> unparsedEvents, ParsePolicyType policyType) {
        return unparsedEvents.stream().map(event -> Event.parsedEvent(event, policyType)).collect(Collectors.toCollection(ArrayList::new));
    }

}
