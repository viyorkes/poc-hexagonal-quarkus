package com.poc.quarkus.network.application.ports.output;

import com.poc.quarkus.network.domain.entity.Router;

import java.util.List;

public interface RouterViewOutputPort {
    List<Router> fetchRelatedRouters();
}