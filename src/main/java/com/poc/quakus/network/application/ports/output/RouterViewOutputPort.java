package com.poc.quakus.network.application.ports.output;

import com.poc.quakus.network.domain.Router;

import java.util.List;

public interface RouterViewOutputPort {
    List<Router> fetchRouters();
}