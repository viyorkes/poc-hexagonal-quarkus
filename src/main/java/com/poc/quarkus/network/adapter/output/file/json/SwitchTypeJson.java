package com.poc.quarkus.network.adapter.output.file.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public enum SwitchTypeJson {
    LAYER2,
    LAYER3;
}
