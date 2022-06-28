package com.poc.quakus.network.adapter.output.h2;


import com.poc.quakus.network.adapter.output.h2.data.RouterData;
import com.poc.quakus.network.adapter.output.h2.mappers.RouterH2Mapper;
import com.poc.quakus.network.application.usecase.RouterNetworkOutputPort;
import com.poc.quakus.network.domain.entity.Router;
import com.poc.quakus.network.domain.vo.RouterId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

public class RouterNetworkH2Adapter implements RouterNetworkOutputPort {

    private static RouterNetworkH2Adapter instance;

    @PersistenceContext
    private EntityManager em;

    private RouterNetworkH2Adapter(){
        setUpH2Database();
    }

    @Override
    public Router fetchRouterById(RouterId routerId) {
        var routerData = em.getReference(RouterData.class, routerId.getUUID());
        return RouterH2Mapper.toDomain(routerData);
    }

    @Override
    public boolean persistRouter(Router router) {
        var routerData = RouterH2Mapper.toH2(router);
        em.persist(routerData);
        return true;
    }

    private void setUpH2Database() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("inventory");
        EntityManager em = entityManagerFactory.createEntityManager();
        this.em = em;
    }

    public static RouterNetworkH2Adapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkH2Adapter();
        }
        return instance;
    }
}
