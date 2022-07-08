package com.poc.quarkus.network.domain.entity;


import com.poc.quarkus.network.domain.vo.IP;
import com.poc.quarkus.network.domain.vo.Network;
import com.poc.quarkus.network.domain.vo.RouterId;
import com.poc.quarkus.network.domain.vo.RouterType;

import java.util.List;
import java.util.function.Predicate;


public class Router {

    private RouterType type;
    private RouterId id;
    private Switch networkSwitch;

    public Router(){

    }

    public Router(RouterType type, RouterId id) {
        this.type = type;
        this.id = id;
    }

    public Router(RouterType type, RouterId id, Switch networkSwitch) {
        this.type = type;
        this.id = id;
        this.networkSwitch = networkSwitch;
    }


    public boolean isType(RouterType type){
        return this.type == type;
    }

    public void addNetworkToSwitch(Network network){
        this.networkSwitch = networkSwitch.addNetwork(network, this);
    }

    public Network createNetwork(IP address, String name, int cidr){
        return new Network(address, name, cidr);
    }

    public List<Network> retrieveNetworks(){
        return networkSwitch.getNetworks();
    }

    public RouterType getType() {
        return type;
    }

    public RouterId getId() {
        return id;
    }

    public Switch getNetworkSwitch() {
        return networkSwitch;
    }

    @Override
    public String toString() {
        return "Router{" +
                "type=" + type +
                ", id=" + id +
                ", networkSwitch=" + networkSwitch +
                '}';
    }
}
