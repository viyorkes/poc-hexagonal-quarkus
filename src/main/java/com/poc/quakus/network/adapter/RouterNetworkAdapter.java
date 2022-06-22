package com.poc.quakus.network.adapter;

import com.poc.quakus.network.application.usecase.RouterNetworkUseCase;
import com.poc.quakus.network.domain.entity.Router;
import com.poc.quakus.network.domain.vo.IP;
import com.poc.quakus.network.domain.vo.Network;
import com.poc.quakus.network.domain.vo.RouterId;

import java.util.Map;

public abstract class RouterNetworkAdapter {


    protected Router router;
    protected RouterNetworkUseCase routerNetworkUseCase;

    protected Router addNetworkToRouter(Map<String, String> params){
        var routerId = RouterId.withId(params.get("routerId"));
        var network = new Network(IP.fromAddress(params.get("address")),
                params.get("name"),
                Integer.valueOf(params.get("cidr")));
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    public abstract Router processRequest(Object requestParams);



}
