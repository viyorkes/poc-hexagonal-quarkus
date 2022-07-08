package com.poc.quarkus.network.adapter.output.h2.data;

import jakarta.persistence.Embeddable;

@Embeddable
public enum RouterTypeData {
    EDGE,
    CORE;
}
