package com.poc.quakus.network.application.ports.output;

public interface NotifyEventOutputPort {

    void sendEvent(String Event);
    String getEvent();
}
