package co.edu.unicauca.microevaluacion.QueueMessages.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	public static final String CREATED_FORMATS_QUEUE = "CreatedFormatsQueue";
	public static final String STATE_CHANGES_QUEUE = "FormatStateChangesQueue";
	public static final String EXCHANGE_FORMATS = "ExchangeFormats";
	public static final String ROUTING_KEY_CREATED_FORMATS = "routingKeyCreatedFormats";
	public static final String ROUTING_KEY_STATE_CHANGES = "routingKeyStateChanges";

	@Bean("createdFormatsQueue")
	public Queue createdFormatsQueue() {
		return new Queue(CREATED_FORMATS_QUEUE, true);
	}

	@Bean("stateChangesQueue")
	public Queue stateChangesQueue() {
		return new Queue(STATE_CHANGES_QUEUE, true);
	}

	@Bean("formatsExchange")
	public DirectExchange formatsExchange() {
		return new DirectExchange(EXCHANGE_FORMATS);
	}

	@Bean
	public Binding createdFormatsBinding(
			@Qualifier("createdFormatsQueue") Queue queue,
			@Qualifier("formatsExchange") DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_CREATED_FORMATS);
	}

	@Bean
	public Binding stateChangesBinding(
			@Qualifier("stateChangesQueue") Queue queue,
			@Qualifier("formatsExchange") DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_STATE_CHANGES);
	}

	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter() {
		Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
		converter.setClassMapper(new MappeadorJSON());
		return converter;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(
			ConnectionFactory connectionFactory,
			Jackson2JsonMessageConverter jsonMessageConverter) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(jsonMessageConverter);
		return template;
	}
}

