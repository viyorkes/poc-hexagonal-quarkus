package com.poc.quakus.network.adapter.input.stdin;

import com.poc.quakus.network.adapter.output.file.RouterViewFileAdapter;
import com.poc.quakus.network.application.ports.input.RouterViewInputPort;
import com.poc.quakus.network.domain.Router;
import com.poc.quakus.network.domain.RouterType;
import com.poc.quakus.network.application.usecase.RouterViewUseCase;

import java.util.List;

public class RouterViewCLIAdapter {

    RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter(){
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Router.filterRouterByType(RouterType.valueOf(type)));
    }

    private void setAdapters(){
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}