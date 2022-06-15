package com.poc.quakus.network.domain.entity;

import com.poc.quakus.network.domain.vo.IP;
import com.poc.quakus.network.domain.vo.Network;
import com.poc.quakus.network.domain.vo.SwitchId;
import com.poc.quakus.network.domain.vo.SwitchType;

import java.util.ArrayList;
import java.util.List;

public class Switch {

    private SwitchType switchType;
    private SwitchId switchId;
    private List<Network> networks;
    private IP address;

    public Switch(SwitchType switchType, SwitchId switchId, List<Network> networks, IP address) {
        this.switchType = switchType;
        this.switchId = switchId;
        this.networks = networks;
        this.address = address;
    }

    public Switch addNetwork(Network network) {
        var networks = new ArrayList<>(List.of(network));
        networks.add(network);
        return new Switch(this.switchType, this.switchId, networks, this.address);
    }

    public List<Network> getNetworks() {
        return networks;
    }

}
