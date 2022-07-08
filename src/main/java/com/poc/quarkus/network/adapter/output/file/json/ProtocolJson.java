package com.poc.quarkus.network.adapter.output.file.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public enum ProtocolJson {
    IPV4,
    IPV6;
}
