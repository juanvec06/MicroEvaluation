package co.edu.unicauca.microevaluacion.ControllersLayer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.input.AgregarObservacionDTO;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.input.CambiarEstadoDTO;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output.EstadoFormatoResponseDTO;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output.EvaluacionFormatoResponseDTO;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.facades.EvaluacionFormatoFacade;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/evaluarFormato")
public class EvaluacionFormatoController {
    private EvaluacionFormatoFacade facade;
    public EvaluacionFormatoController(EvaluacionFormatoFacade facade) {
        this.facade = facade;
    }
    @PutMapping("/iniciar")
    public ResponseEntity<EstadoFormatoResponseDTO> startEvaluacionFormato(
            @RequestParam String id,
            @RequestBody CambiarEstadoDTO entity) {
        EstadoFormatoResponseDTO response = facade.startEvaluacionFormato(id, entity);
        return ResponseEntity.status(200).body(response);
    }
    @PutMapping("/terminar")
    public ResponseEntity<EvaluacionFormatoResponseDTO> terminateEvaluacionFormato(@RequestParam String id, @RequestBody AgregarObservacionDTO entity) {
        EvaluacionFormatoResponseDTO response = facade.terminateEvaluacionFormato(id, entity);
        return ResponseEntity.status(200).body(response);
    }
}

