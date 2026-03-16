package co.edu.unicauca.microevaluacion.FacadeServicesLayer.services;

import org.springframework.stereotype.Service;

import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EstadoFormato;
import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EvaluacionFormato;
import co.edu.unicauca.microevaluacion.AccessDataLayer.repositories.EvaluacionFormatoRepository;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output.EstadoFormatoResponseDTO;
import co.edu.unicauca.microevaluacion.QueueMessages.Publishers.EstadoFormatoPublisher;
import co.edu.unicauca.microevaluacion.StateLayer.context.FormatoStateContext;
import co.edu.unicauca.microevaluacion.StateLayer.state.AccionEstado;

@Service
public class ManejoEstadoService {
	private final EvaluacionFormatoRepository formatoRepository;
	private final FormatoStateContext stateContext;
	private final EstadoFormatoPublisher estadoFormatoPublisher;

	public ManejoEstadoService(
			EvaluacionFormatoRepository formatoRepository,
			FormatoStateContext stateContext,
			EstadoFormatoPublisher estadoFormatoPublisher) {
		this.formatoRepository = formatoRepository;
		this.stateContext = stateContext;
		this.estadoFormatoPublisher = estadoFormatoPublisher;
	}

	public EstadoFormato cambiarEstado(Long formatoId, AccionEstado accion) {
		EvaluacionFormato formato = formatoRepository.findById(formatoId)
				.orElseThrow(() -> new IllegalArgumentException("No existe el formato con id " + formatoId));

		EstadoFormato nuevoEstado = stateContext.resolverSiguienteEstado(formato.getEstado(), accion);
		formatoRepository.actualizarEstado(formatoId, nuevoEstado);
		estadoFormatoPublisher.publicarCambioEstado(
				EstadoFormatoResponseDTO.builder()
						.idFormato(formatoId)
						.nuevoEstado(nuevoEstado.name())
						.build());
		return nuevoEstado;
	}
}

