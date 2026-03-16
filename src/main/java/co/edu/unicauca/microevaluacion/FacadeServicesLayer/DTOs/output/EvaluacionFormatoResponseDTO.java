package co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output;

import java.time.LocalDateTime;

import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EstadoFormato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionFormatoResponseDTO {
	private Long id;
	private String titulo;
	private String objetivoGeneral;
	private String observacion;
	private EstadoFormato estado;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaActualizacion;
}

