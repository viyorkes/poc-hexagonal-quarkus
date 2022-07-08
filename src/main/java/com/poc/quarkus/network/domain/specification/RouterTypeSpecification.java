package com.poc.quarkus.network.domain.specification;


import com.poc.quarkus.network.domain.entity.Router;
import com.poc.quarkus.network.domain.specification.shared.AbstractSpecification;
import com.poc.quarkus.network.domain.vo.RouterType;



    public class RouterTypeSpecification extends AbstractSpecification<Router> {
        @Override
        public boolean isSatisfiedBy(Router router) {
            return router.isType(RouterType.EDGE);
        }
}
