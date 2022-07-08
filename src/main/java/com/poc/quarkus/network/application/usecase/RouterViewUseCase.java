package com.poc.quarkus.network.application.usecase;

import com.poc.quarkus.network.domain.entity.Router;
import com.poc.quarkus.network.domain.vo.RouterType;

import java.util.List;
import java.util.function.Predicate;

public interface RouterViewUseCase {

    List<Router> getRelatedRouters(RelatedRoutersCommand relatedRoutersCommand);

    class RelatedRoutersCommand {

        private RouterType type;

        public RelatedRoutersCommand(String type){
            this.type = RouterType.valueOf(type);
        }

        public RouterType getType() {
            return type;
        }
    }
}