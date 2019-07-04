package io.eventuate.tram.examples.basic.kafka;

import io.eventuate.tram.examples.basic.messages.AbstractTramMessageTest;
import io.micronaut.context.annotation.Property;
import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
//@Property(name = "micronaut.eventuate.tram.message.producer.jdbc.factory", value = "true")
//@Property(name = "micronaut.eventuate.tram.common.producer.factory", value = "true")
//@Property(name = "micronaut.eventuate.sql.dialect.factory", value = "true")
//@Property(name = "micronaut.eventuate.common.jdbc.operations.factory", value = "true")
public class JdbcKafkaTramMessageTest extends AbstractTramMessageTest {

}
