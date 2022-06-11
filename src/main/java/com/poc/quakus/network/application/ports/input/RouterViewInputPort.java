package com.poc.quakus.network.application.ports.input;

import com.poc.quakus.network.application.ports.output.RouterViewOutputPort;
import com.poc.quakus.network.domain.Router;

import com.poc.quakus.network.application.usecase.RouterViewUseCase;

import java.util.List;
import java.util.function.Predicate;

import java.util.List;
import java.util.function.Predicate;

public class RouterViewInputPort implements RouterViewUseCase {

    private RouterViewOutputPort routerListOutputPort;

    public RouterViewInputPort(RouterViewOutputPort routerViewOutputPort) {
        this.routerListOutputPort = routerViewOutputPort;
    }

    @Override
    public List<Router> getRouters(Predicate<Router> filter) {
        var routers = routerListOutputPort.fetchRouters();
        return Router.retrieveRouter(routers, filter);
    }


}
