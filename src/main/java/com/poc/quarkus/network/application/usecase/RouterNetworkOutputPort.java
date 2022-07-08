package com.poc.quarkus.network.application.usecase;

import com.poc.quarkus.network.domain.entity.Router;
import com.poc.quarkus.network.domain.vo.RouterId;

public interface RouterNetworkOutputPort {
    Router fetchRouterById(RouterId routerId);

    boolean persistRouter(Router router);
}
