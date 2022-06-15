package com.poc.quakus.network.domain.specification;


import com.poc.quakus.network.domain.entity.Router;
import com.poc.quakus.network.domain.specification.shared.AbstractSpecification;
import com.poc.quakus.network.domain.vo.RouterType;

public class RouterTypeSpecification extends AbstractSpecification<Router> {

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.getRouterType().equals(RouterType.EDGE) || router.getRouterType().equals(RouterType.CORE);
    }
}
