package com.poc.quarkus.network.application.ports.input;

import com.poc.quarkus.network.application.ports.output.NotifyEventOutputPort;
import com.poc.quarkus.network.application.usecase.RouterNetworkOutputPort;
import com.poc.quarkus.network.application.usecase.RouterNetworkUseCase;
import com.poc.quarkus.network.domain.entity.Router;
import com.poc.quarkus.network.domain.policy.NetworkOperation;
import com.poc.quarkus.network.domain.vo.Network;
import com.poc.quarkus.network.domain.vo.RouterId;

public class RouterNetworkInputPort implements RouterNetworkUseCase {

    private RouterNetworkOutputPort routerNetworkOutputPort;

    private NotifyEventOutputPort notifyEventOutputPort;

    public RouterNetworkInputPort(RouterNetworkOutputPort routerNetworkOutputPort,
                                  NotifyEventOutputPort notifyEventOutputPort){
        this.routerNetworkOutputPort = routerNetworkOutputPort;
        this.notifyEventOutputPort = notifyEventOutputPort;
    }

    public RouterNetworkInputPort(RouterNetworkOutputPort routerNetworkOutputPort){
        this.routerNetworkOutputPort = routerNetworkOutputPort;
    }

    @Override
    public Router addNetworkToRouter(RouterId routerId, Network network) {
        var router = fetchRouter(routerId);
        notifyEventOutputPort.sendEvent("Adding "+network.getName()+" network to router "+router.getId().getUUID());
        return createNetwork(router, network);
    }

    @Override
    public Router getRouter(RouterId routerId) {
        notifyEventOutputPort.sendEvent("Retrieving router ID "+routerId.getUUID());
        return fetchRouter(routerId);
    }

    private Router fetchRouter(RouterId routerId) {
        return routerNetworkOutputPort.fetchRouterById(routerId);
    }

    private Router createNetwork(Router router, Network network) {
        try {
            var routerWithNewNetwork = NetworkOperation.createNewNetwork(router, network);
            return persistNetwork(routerWithNewNetwork) ?
                    routerWithNewNetwork: router;
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private boolean persistNetwork(Router router) {
        return routerNetworkOutputPort.persistRouter(router);
    }
}