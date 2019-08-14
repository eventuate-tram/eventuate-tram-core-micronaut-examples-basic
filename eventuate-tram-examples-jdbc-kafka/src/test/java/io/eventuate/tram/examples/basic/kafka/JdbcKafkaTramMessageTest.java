package io.eventuate.tram.examples.basic.kafka;

import io.eventuate.tram.examples.basic.messages.AbstractTramMessageTest;
import io.micronaut.test.annotation.MicronautTest;

@MicronautTest(transactional = false)
public class JdbcKafkaTramMessageTest extends AbstractTramMessageTest {

}
