package com.poc.quarkus.network.adapter.input.stdin;

import com.poc.quarkus.network.adapter.output.file.RouterViewFileAdapter;
import com.poc.quarkus.network.application.ports.input.RouterViewInputPort;
import com.poc.quarkus.network.domain.entity.Router;
import com.poc.quarkus.network.application.usecase.RouterViewUseCase;
import com.poc.quarkus.network.domain.vo.RouterType;

import java.util.List;

public class RouterViewCLIAdapter {

    RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter(){
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        RouterViewUseCase.RelatedRoutersCommand relatedRoutersCommand = new RouterViewUseCase.RelatedRoutersCommand(type);
        return routerViewUseCase.getRelatedRouters(relatedRoutersCommand);
    }

    private void setAdapters(){
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}