package co.edu.unicauca.microevaluacion.QueueMessages.Consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EstadoFormato;
import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EvaluacionFormato;
import co.edu.unicauca.microevaluacion.AccessDataLayer.repositories.EvaluacionFormatoRepository;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output.EvaluacionFormatoResponseDTO;
import co.edu.unicauca.microevaluacion.QueueMessages.Config.RabbitMQConfig;

@Component
public class FormatoCreadoConsumer {
	private final EvaluacionFormatoRepository formatoRepository;

	public FormatoCreadoConsumer(EvaluacionFormatoRepository formatoRepository) {
		this.formatoRepository = formatoRepository;
	}

	@RabbitListener(queues = RabbitMQConfig.CREATED_FORMATS_QUEUE)
	public void consumirFormatoCreado(EvaluacionFormatoResponseDTO payload) {
		EstadoFormato estadoInicial = payload.getEstado() == null ? EstadoFormato.FORMULATED : payload.getEstado();
		EvaluacionFormato formato = EvaluacionFormato.builder()
				.id(payload.getId())
				.titulo(payload.getTitulo() == null ? "Formato sin titulo" : payload.getTitulo())
				.objetivoGeneral(payload.getObjetivoGeneral())
				.estado(estadoInicial)
				.fechaCreacion(payload.getFechaCreacion())
				.build();
		formatoRepository.guardarDesdeCola(formato);
	}
}

