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
public class ObservacionEvaluacion {
	private Long id;
	private Long formatoId;
	private String observacion;
	private LocalDateTime fechaCreacion;
}

