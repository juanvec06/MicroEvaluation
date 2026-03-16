package co.edu.unicauca.microevaluacion.AccessDataLayer.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionFormato {
	private Long id;
	private String titulo;
	private String objetivoGeneral;
	private EstadoFormato estado;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaActualizacion;
}

