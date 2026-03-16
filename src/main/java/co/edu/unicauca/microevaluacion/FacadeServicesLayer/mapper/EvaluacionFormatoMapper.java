package co.edu.unicauca.microevaluacion.FacadeServicesLayer.mapper;

import org.springframework.stereotype.Component;

import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EvaluacionFormato;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output.EvaluacionFormatoResponseDTO;

@Component
public class EvaluacionFormatoMapper {
	public EvaluacionFormatoResponseDTO toResponse(EvaluacionFormato formato, String observacion) {
		return EvaluacionFormatoResponseDTO.builder()
				.id(formato.getId())
				.titulo(formato.getTitulo())
				.objetivoGeneral(formato.getObjetivoGeneral())
				.observacion(observacion)
				.estado(formato.getEstado())
				.fechaCreacion(formato.getFechaCreacion())
				.fechaActualizacion(formato.getFechaActualizacion())
				.build();
	}
}

