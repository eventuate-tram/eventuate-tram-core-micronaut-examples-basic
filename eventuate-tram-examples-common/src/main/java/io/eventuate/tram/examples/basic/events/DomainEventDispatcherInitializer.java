package io.eventuate.tram.examples.basic.events;

import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.micronaut.context.annotation.Context;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Context
public class DomainEventDispatcherInitializer {

  @Inject
  private DomainEventDispatcher domainEventDispatcher;

  @PostConstruct
  public void init() {
    domainEventDispatcher.initialize();
  }
}
