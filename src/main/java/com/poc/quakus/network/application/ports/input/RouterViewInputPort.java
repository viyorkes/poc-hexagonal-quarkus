package com.poc.quakus.network.application.ports.input;

import com.poc.quakus.network.application.ports.output.RouterViewOutputPort;
import com.poc.quakus.network.domain.entity.Router;

import com.poc.quakus.network.application.usecase.RouterViewUseCase;
import com.poc.quakus.network.domain.service.RouterSearch;

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
            return RouterSearch.retrieveRouter(routers, filter);
        }
    }