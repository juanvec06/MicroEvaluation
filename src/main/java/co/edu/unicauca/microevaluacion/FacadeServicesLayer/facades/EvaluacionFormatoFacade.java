package co.edu.unicauca.microevaluacion.FacadeServicesLayer.facades;

import org.springframework.stereotype.Service;

import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.input.AgregarObservacionDTO;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.input.CambiarEstadoDTO;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output.EstadoFormatoResponseDTO;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output.EvaluacionFormatoResponseDTO;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.services.EvaluacionFormatoService;

@Service
public class EvaluacionFormatoFacade {
	private final EvaluacionFormatoService evaluacionFormatoService;

	public EvaluacionFormatoFacade(EvaluacionFormatoService evaluacionFormatoService) {
		this.evaluacionFormatoService = evaluacionFormatoService;
	}

	public EstadoFormatoResponseDTO startEvaluacionFormato(String id, CambiarEstadoDTO entity) {
		return evaluacionFormatoService.iniciarEvaluacion(id, entity);
	}

	public EvaluacionFormatoResponseDTO terminateEvaluacionFormato(String id, AgregarObservacionDTO entity) {
		return evaluacionFormatoService.terminarEvaluacion(id, entity);
	}

	public EstadoFormatoResponseDTO approveFormato(String id, CambiarEstadoDTO entity) {
		return evaluacionFormatoService.aprobarFormato(id, entity);
	}

	public EstadoFormatoResponseDTO denyFormato(String id, CambiarEstadoDTO entity) {
		return evaluacionFormatoService.denegarFormato(id, entity);
	}
}

