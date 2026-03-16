package co.edu.unicauca.microevaluacion.ControllersLayer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output.EstadoFormatoResponseDTO;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.facades.EvaluacionFormatoFacade;

@RestController
@RequestMapping("/api/conceptoFormato")
public class ConceptoFormatoController {
	private EvaluacionFormatoFacade facade;

	public ConceptoFormatoController(EvaluacionFormatoFacade facade) {
		this.facade = facade;
	}

	@PutMapping("/aprobar")
	public ResponseEntity<EstadoFormatoResponseDTO> approveFormato(@RequestParam String id) {
		EstadoFormatoResponseDTO response = facade.approveFormato(id);
		return ResponseEntity.status(200).body(response);
	}

	@PutMapping("/denegar")
	public ResponseEntity<EstadoFormatoResponseDTO> denyFormato(@RequestParam String id) {
		EstadoFormatoResponseDTO response = facade.denyFormato(id);
		return ResponseEntity.status(200).body(response);
	}

}

