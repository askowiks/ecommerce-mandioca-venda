package com.letscode.santander.ecommercemandioca.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.timelimiter.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class Resilience4jConfig {

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @PostConstruct
    private void postConstruct() {
        circuitBreakerRegistry.getAllCircuitBreakers()
                .forEach(circuitBreaker -> circuitBreaker.getEventPublisher().onStateTransition(
                        (event) -> log.info(event.toString())
                ));

        circuitBreakerRegistry.getEventPublisher().onEntryAdded(
                (addedEvent) -> addedEvent.getAddedEntry().getEventPublisher().onStateTransition(
                        (event) -> log.info(event.toString())
                )
        );
    }

    @Bean
    public RegistryEventConsumer<TimeLimiter> timeLimiterEventConsumer() {
        return new RegistryEventConsumer<TimeLimiter>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<TimeLimiter> entryAddedEvent) {
                entryAddedEvent.getAddedEntry().getEventPublisher()
                        .onTimeout(event -> log.error("time limiter {} timeout {} on {}",
                                event.getTimeLimiterName(), event.getEventType(), event.getCreationTime())
                        );
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<TimeLimiter> entryRemoveEvent) { }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<TimeLimiter> entryReplacedEvent) { }
        };
    }

}
