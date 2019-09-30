package io.eventuate.tram.examples.basic.kafka;

import io.eventuate.tram.examples.basic.events.AbstractTramEventTest;
import io.micronaut.test.annotation.MicronautTest;


@MicronautTest(transactional = false)
public class JdbcKafkaTramEventTest extends AbstractTramEventTest {
}
