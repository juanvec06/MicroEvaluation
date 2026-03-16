package co.edu.unicauca.microevaluacion.QueueMessages.Publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output.EstadoFormatoResponseDTO;
import co.edu.unicauca.microevaluacion.QueueMessages.Config.RabbitMQConfig;

@Component
public class EstadoFormatoPublisher {
	private final RabbitTemplate rabbitTemplate;

	public EstadoFormatoPublisher(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void publicarCambioEstado(EstadoFormatoResponseDTO estadoFormatoResponseDTO) {
		rabbitTemplate.convertAndSend(
				RabbitMQConfig.EXCHANGE_FORMATS,
				RabbitMQConfig.ROUTING_KEY_STATE_CHANGES,
				estadoFormatoResponseDTO);
	}
}

