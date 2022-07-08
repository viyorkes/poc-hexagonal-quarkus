package com.poc.quarkus.network.application.ports.input;

import com.poc.quarkus.network.application.ports.output.RouterViewOutputPort;
import com.poc.quarkus.network.domain.entity.Router;

import com.poc.quarkus.network.application.usecase.RouterViewUseCase;
import com.poc.quarkus.network.domain.service.RouterSearch;
import com.poc.quarkus.network.domain.vo.RouterType;

import java.util.List;



public class RouterViewInputPort implements RouterViewUseCase {

    private RouterViewOutputPort routerListOutputPort;

    private Router router;

    public RouterViewInputPort(RouterViewOutputPort routerGraphOutputPort) {
        this.routerListOutputPort = routerGraphOutputPort;
    }

    @Override
    public List<Router> getRelatedRouters(RelatedRoutersCommand relatedRoutersCommand) {
        var type = relatedRoutersCommand.getType();
        var routers = routerListOutputPort.fetchRelatedRouters();
        return fetchRelatedEdgeRouters(type, routers);
    }

    private List<Router> fetchRelatedEdgeRouters(RouterType type, List<Router> routers){
        return RouterSearch.getRouters(type, routers);
    }
}