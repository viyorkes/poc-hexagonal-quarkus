package com.poc.quakus.network.application.usecase;


import com.poc.quakus.network.domain.entity.Router;
import com.poc.quakus.network.domain.vo.Network;
import com.poc.quakus.network.domain.vo.RouterId;

public interface RouterNetworkUseCase {

        Router addNetworkToRouter(RouterId routerId, Network network);
    }



