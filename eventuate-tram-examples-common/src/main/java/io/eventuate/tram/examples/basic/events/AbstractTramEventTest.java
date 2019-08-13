package io.eventuate.tram.examples.basic.events;

import io.eventuate.tram.events.common.DomainEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.examples.basic.events.domain.AccountDebited;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.sql.DataSource;
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

  @Inject
  private TransactionTemplate transactionTemplate;

  @Test
  public void shouldReceiveEvent() throws Exception {
    long uniqueId = config.getUniqueId();

    DomainEvent domainEvent = new AccountDebited(uniqueId);

    transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

    transactionTemplate.execute(status -> {
      domainEventPublisher.publish(config.getAggregateType(), config.getAggregateId(), Collections.singletonList(domainEvent));
      return null;
    });

    AccountDebited event = tramEventTestEventConsumer.getQueue().poll(30, TimeUnit.SECONDS);

    assertNotNull(event);
    assertEquals(uniqueId, event.getAmount());
  }

}
