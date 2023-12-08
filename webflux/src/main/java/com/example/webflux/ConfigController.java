package com.example.webflux;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/config")
public class ConfigController {

    final ConfigRepository configRepository;

    public ConfigController(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @GetMapping("/{id}")
    public Mono<Config> config(@PathVariable String id) {
        return configRepository.findById(id);
    }

    @GetMapping
    public Flux<Config> configAll() {
        return configRepository.findAll();
    }

    @PutMapping()
    public Mono<Config> update(@RequestBody Config config) {
        return configRepository.updateConfig(config);
    }
}
