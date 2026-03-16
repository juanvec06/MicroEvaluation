package co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EstadoFormato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EvaluacionFormatoResponseDTO {
	@JsonAlias({ "id" })
	private Long id;
	@JsonAlias({ "title" })
	private String titulo;
	@JsonAlias({ "generalObjective" })
	private String objetivoGeneral;
	@JsonAlias({ "observations" })
	private String observacion;
	@JsonAlias({ "state" })
	private EstadoFormato estado;
	@JsonAlias({ "submissionDate", "createdAt" })
	private LocalDateTime fechaCreacion;
	@JsonAlias({ "updatedAt" })
	private LocalDateTime fechaActualizacion;
}

