package co.edu.unicauca.microevaluacion.FacadeServicesLayer.services;

import org.springframework.stereotype.Service;

import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EstadoFormato;
import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EvaluacionFormato;
import co.edu.unicauca.microevaluacion.AccessDataLayer.repositories.EvaluacionFormatoRepository;
import co.edu.unicauca.microevaluacion.StateLayer.context.FormatoStateContext;
import co.edu.unicauca.microevaluacion.StateLayer.state.AccionEstado;

@Service
public class ManejoEstadoService {
	private final EvaluacionFormatoRepository formatoRepository;
	private final FormatoStateContext stateContext;

	public ManejoEstadoService(EvaluacionFormatoRepository formatoRepository, FormatoStateContext stateContext) {
		this.formatoRepository = formatoRepository;
		this.stateContext = stateContext;
	}

	public EstadoFormato cambiarEstado(Long formatoId, AccionEstado accion) {
		EvaluacionFormato formato = formatoRepository.findById(formatoId)
				.orElseThrow(() -> new IllegalArgumentException("No existe el formato con id " + formatoId));

		EstadoFormato nuevoEstado = stateContext.resolverSiguienteEstado(formato.getEstado(), accion);
		formatoRepository.actualizarEstado(formatoId, nuevoEstado);
		return nuevoEstado;
	}
}

