package io.eventuate.tram.examples.basic.events;

import io.eventuate.tram.events.common.DomainEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.examples.basic.events.domain.AccountDebited;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class AbstractTramEventTest {

  @Inject
  private DomainEventPublisher domainEventPublisher;

  @Inject
  private AbstractTramEventTestConfig config;

  @Inject
  private TramEventTestEventConsumer tramEventTestEventConsumer;

  @Test
  public void shouldReceiveEvent() throws Exception {
    long uniqueId = config.getUniqueId();

    DomainEvent domainEvent = new AccountDebited(uniqueId);

    domainEventPublisher.publish(config.getAggregateType(), config.getAggregateId(), Collections.singletonList(domainEvent));

    AccountDebited event = tramEventTestEventConsumer.getQueue().poll(30, TimeUnit.SECONDS);

    assertNotNull(event);
    assertEquals(uniqueId, event.getAmount());
  }

}
