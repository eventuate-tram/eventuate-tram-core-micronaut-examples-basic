package io.eventuate.tram.examples.basic.messages;

import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import io.eventuate.tram.messaging.producer.MessageBuilder;
import io.eventuate.tram.messaging.producer.MessageProducer;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public abstract class AbstractTramMessageTest {

  private long uniqueId = System.currentTimeMillis();

  private String subscriberId = "subscriberId" + uniqueId;
  private String destination = "destination" + uniqueId;
  private String payload = "Hello" + uniqueId;

  @Inject
  private MessageProducer messageProducer;

  @Inject
  private MessageConsumer messageConsumer;

  private BlockingQueue<Message> queue = new LinkedBlockingDeque<>();

  @Test
  public void shouldReceiveMessage() throws InterruptedException {
    messageConsumer.subscribe(subscriberId, Collections.singleton(destination), this::handleMessage);
    messageProducer.send(destination, MessageBuilder.withPayload(payload).build());

    Message m = queue.poll(30, TimeUnit.SECONDS);

    assertNotNull(m);
    assertEquals(payload, m.getPayload());
  }

  private void handleMessage(Message message) {
    queue.add(message);
  }
}
