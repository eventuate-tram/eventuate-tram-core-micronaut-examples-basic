package io.eventuate.tram.examples.basic.events;

import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class AbstractTramEventTestFactory {

  @Singleton
  public AbstractTramEventTestConfig abstractTramEventTestConfig() {
    return new AbstractTramEventTestConfig();
  }

  @Context
  public DomainEventDispatcher domainEventDispatcher(AbstractTramEventTestConfig config,
                                                     TramEventTestEventConsumer target,
                                                     DomainEventDispatcherFactory domainEventDispatcherFactory) {
    return domainEventDispatcherFactory.make("eventDispatcherId" + config.getUniqueId(), target.domainEventHandlers());
  }

  @Singleton
  public TramEventTestEventConsumer tramEventTestTarget(AbstractTramEventTestConfig config) {
    return new TramEventTestEventConsumer(config.getAggregateType());
  }
}
