package co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoFormatoResponseDTO {
	private Long idFormato;
	private String nuevoEstado;
}

