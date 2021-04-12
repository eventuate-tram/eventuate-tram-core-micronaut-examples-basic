package io.eventuate.tram.examples.basic.commands;

import io.eventuate.common.jdbc.EventuateTransactionTemplate;
import io.eventuate.tram.commands.common.ReplyMessageHeaders;
import io.eventuate.tram.commands.producer.CommandProducer;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class AbstractTramCommandTest {

  @Inject
  private CommandProducer commandProducer;

  @Inject
  private AbstractTramCommandTestConfig config;

  @Inject
  private MessageConsumer messageConsumer;

  @Inject
  private EventuateTransactionTemplate eventuateTransactionTemplate;

  private BlockingQueue<Message> queue = new LinkedBlockingDeque<>();

  @Test
  public void shouldInvokeCommand() throws InterruptedException {

    subscribeToReplyChannel();

    String commandId = sendCommand();

    assertReplyReceived(commandId);
  }

  private void assertReplyReceived(String commandId) throws InterruptedException {
    Message m = queue.poll(30, TimeUnit.SECONDS);
    System.out.println("Got message = " + m);
    assertNotNull(m);
    assertEquals(commandId, m.getRequiredHeader(ReplyMessageHeaders.IN_REPLY_TO));
  }

  private String sendCommand() {
    return eventuateTransactionTemplate.executeInTransaction(() -> {
      return commandProducer.send(config.getCommandChannel(),
              new DoSomethingCommand(),
              config.getReplyChannel(),
              Collections.emptyMap());
    });
  }

  private void subscribeToReplyChannel() {
    String subscriberId = "subscriberId" + config.getUniqueId();
    messageConsumer.subscribe(subscriberId, Collections.singleton(config.getReplyChannel()), this::handleMessage);
  }

  private void handleMessage(Message message) {
    queue.add(message);
  }
}
