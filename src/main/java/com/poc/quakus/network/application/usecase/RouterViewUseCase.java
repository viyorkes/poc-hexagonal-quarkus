package com.poc.quakus.network.application.usecase;

import com.poc.quakus.network.domain.Router;

import java.util.List;
import java.util.function.Predicate;

public interface RouterViewUseCase {
    List<Router> getRouters(Predicate<Router> filter);
}
