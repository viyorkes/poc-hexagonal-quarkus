package com.poc.quakus.network.application.ports.output;

import com.poc.quakus.network.domain.entity.Router;

import java.util.List;

public interface RouterViewOutputPort {
    List<Router> fetchRelatedRouters();
}