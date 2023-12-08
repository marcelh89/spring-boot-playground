package com.example.webflux;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ConfigRepository {

    private static final Map<String, Config> CONFIG_DATA;

    static {
        CONFIG_DATA = new HashMap<>();
        CONFIG_DATA.put("1", new Config("1", "Config 1"));
        CONFIG_DATA.put("2", new Config("2", "Config 2"));
    }

    public Mono<Config> findById(String id) {
        return Mono.just(CONFIG_DATA.get(id));
    }

    public Flux<Config> findAll() {
        return Flux.fromIterable(CONFIG_DATA.values());
    }

    public Mono<Config> updateConfig(Config updatedConfig) {
        Config found = CONFIG_DATA.get(updatedConfig.id);
        if (found != null) {
            found.setName(updatedConfig.name);
        }
        return Mono.just(found);
    }


}
