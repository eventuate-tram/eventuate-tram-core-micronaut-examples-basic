package io.eventuate.tram.examples.basic.kafka;

import io.eventuate.tram.examples.basic.commands.AbstractTramCommandTest;
import io.micronaut.test.annotation.MicronautTest;

@MicronautTest(transactional = false)
public class JdbcKafkaTramCommandTest extends AbstractTramCommandTest {
}
