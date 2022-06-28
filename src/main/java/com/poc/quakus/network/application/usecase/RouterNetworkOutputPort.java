package com.poc.quakus.network.application.usecase;

import com.poc.quakus.network.domain.entity.Router;
import com.poc.quakus.network.domain.vo.RouterId;

public interface RouterNetworkOutputPort {
    Router fetchRouterById(RouterId routerId);

    boolean persistRouter(Router router);
}
