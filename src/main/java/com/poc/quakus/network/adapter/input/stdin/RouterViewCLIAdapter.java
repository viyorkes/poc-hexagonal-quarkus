package com.poc.quakus.network.adapter.input.stdin;

import com.poc.quakus.network.adapter.output.file.RouterViewFileAdapter;
import com.poc.quakus.network.application.ports.input.RouterViewInputPort;
import com.poc.quakus.network.domain.entity.Router;
import com.poc.quakus.network.application.usecase.RouterViewUseCase;
import com.poc.quakus.network.domain.vo.RouterType;

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