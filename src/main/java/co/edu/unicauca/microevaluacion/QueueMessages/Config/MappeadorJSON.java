package co.edu.unicauca.microevaluacion.QueueMessages.Config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.support.converter.DefaultClassMapper;

import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output.EvaluacionFormatoResponseDTO;

public class MappeadorJSON extends DefaultClassMapper {

    public MappeadorJSON() {
        Map<String, Class<?>> idClassMapping = new HashMap<>();

        // Local message types
        idClassMapping.put(EvaluacionFormatoResponseDTO.class.getName(), EvaluacionFormatoResponseDTO.class);
        idClassMapping.put("EvaluacionFormatoResponseDTO", EvaluacionFormatoResponseDTO.class);

        // Incoming message types from microformatos
        idClassMapping.put("edu.co.unicauca.microformatos.FacadeServicesLayer.DTOs.output.ResearchProposalResponseDTO",
                EvaluacionFormatoResponseDTO.class);
        idClassMapping.put("edu.co.unicauca.microformatos.FacadeServicesLayer.DTOs.output.PracticeProposalResponseDTO",
                EvaluacionFormatoResponseDTO.class);
        idClassMapping.put("ResearchProposalResponseDTO", EvaluacionFormatoResponseDTO.class);
        idClassMapping.put("PracticeProposalResponseDTO", EvaluacionFormatoResponseDTO.class);

        this.setIdClassMapping(idClassMapping);
        this.setTrustedPackages(
                "co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output",
                "edu.co.unicauca.microformatos.FacadeServicesLayer.DTOs.output");
    }
}