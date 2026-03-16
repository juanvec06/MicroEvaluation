package co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CambiarEstadoDTO {
    private Long idFormato;
    private String comentario;
}