package com.poc.quarkus.network.adapter.output.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.quarkus.network.adapter.RouterNetworkAdapter;
import com.poc.quarkus.network.adapter.input.RouterManageNetworkAdapter;
import com.poc.quarkus.network.application.usecase.RouterNetworkUseCase;
import com.poc.quarkus.network.domain.entity.Router;
import com.sun.net.httpserver.HttpServer;

import java.nio.charset.StandardCharsets;
import java.util.List;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;
import java.net.URLDecoder;


public class RouterNetworkRestAdapter extends RouterManageNetworkAdapter {

    public RouterNetworkRestAdapter(RouterNetworkUseCase routerNetworkUseCase){
        this.routerNetworkUseCase = routerNetworkUseCase;
    }

    @Override
    public Router processRequest(Object requestParams){
        Map<String, String> params = new HashMap<>();
        if(requestParams instanceof HttpServer) {
            var httpserver = (HttpServer) requestParams;
            httpserver.createContext("/network", (exchange -> {
                if ("GET".equals(exchange.getRequestMethod())) {
                    var query = exchange.getRequestURI().getRawQuery();
                    httpParams(query, params);
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
                    if(exchange.getRequestURI().getPath().equals("/network/add")) {
                        try {
                            router = this.addNetworkToRouter(params);
                        } catch (Exception e){
                            exchange.sendResponseHeaders(400, e.getMessage().getBytes().length);
                            OutputStream output = exchange.getResponseBody();
                            output.write(e.getMessage().getBytes());
                            output.flush();
                        }
                    }
                    if(exchange.getRequestURI().getPath().contains("/network/get")) {
                        router = this.getRouter(params);
                    }
                    ObjectMapper mapper = new ObjectMapper();
                    var routerJson = mapper.writeValueAsString(RouterJsonFileMapper.toJson(router));
                    exchange.sendResponseHeaders(200, routerJson.getBytes().length);
                    OutputStream output = exchange.getResponseBody();
                    output.write(routerJson.getBytes());
                    output.flush();
                } else {
                    exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
                }
                exchange.close();
            }));
            httpserver.setExecutor(null); // creates a default executor
            httpserver.start();
        }
        return router;
    }

    private void httpParams(String query, Map<String, String> params) {
        var noNameText = "Anonymous";
        var requestParams = Pattern.compile("&").splitAsStream(query)
                .map(s -> Arrays.copyOf(s.split("="), 2))
                .collect(groupingBy(s -> decode(s[0]), mapping(s -> decode(s[1]), toList())));

        var routerId = requestParams.getOrDefault("routerId", List.of(noNameText)).stream().findFirst().orElse(noNameText);
        params.put("routerId",routerId);
        var address = requestParams.getOrDefault("address", List.of(noNameText)).stream().findFirst().orElse(noNameText);
        params.put("address",address);
        var name = requestParams.getOrDefault("name", List.of(noNameText)).stream().findFirst().orElse(noNameText);
        params.put("name",name);
        var cidr = requestParams.getOrDefault("cidr", List.of(noNameText)).stream().findFirst().orElse(noNameText);
        params.put("cidr",cidr);
    }

    private static String decode(final String encoded) {
        return encoded == null ? null : URLDecoder.decode(encoded, StandardCharsets.UTF_8);
    }
}