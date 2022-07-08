package com.poc.quarkus.network.application.usecase;


import com.poc.quarkus.network.domain.entity.Router;
import com.poc.quarkus.network.domain.vo.Network;
import com.poc.quarkus.network.domain.vo.RouterId;

public interface RouterNetworkUseCase {

        Router addNetworkToRouter(RouterId routerId, Network network);

        Router getRouter(RouterId routerId);
    }



