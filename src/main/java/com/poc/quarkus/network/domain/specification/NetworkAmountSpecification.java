package com.poc.quarkus.network.domain.specification;


import com.poc.quarkus.network.domain.entity.Router;
import com.poc.quarkus.network.domain.specification.shared.AbstractSpecification;

public class NetworkAmountSpecification extends AbstractSpecification<Router> {

    final static public int MAXIMUM_ALLOWED_NETWORKS = 6;

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.retrieveNetworks().size() <=MAXIMUM_ALLOWED_NETWORKS;
    }
}
