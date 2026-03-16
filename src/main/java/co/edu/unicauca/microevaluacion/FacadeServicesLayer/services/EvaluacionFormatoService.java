package co.edu.unicauca.microevaluacion.FacadeServicesLayer.services;

import org.springframework.stereotype.Service;

import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EvaluacionFormato;
import co.edu.unicauca.microevaluacion.AccessDataLayer.repositories.EvaluacionFormatoRepository;
import co.edu.unicauca.microevaluacion.AccessDataLayer.repositories.ObservacionRepository;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.input.AgregarObservacionDTO;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output.EstadoFormatoResponseDTO;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.DTOs.output.EvaluacionFormatoResponseDTO;
import co.edu.unicauca.microevaluacion.FacadeServicesLayer.mapper.EvaluacionFormatoMapper;
import co.edu.unicauca.microevaluacion.StateLayer.state.AccionEstado;

@Service
public class EvaluacionFormatoService {
	private final EvaluacionFormatoRepository formatoRepository;
	private final ObservacionRepository observacionRepository;
	private final ManejoEstadoService manejoEstadoService;
	private final EvaluacionFormatoMapper mapper;

	public EvaluacionFormatoService(
			EvaluacionFormatoRepository formatoRepository,
			ObservacionRepository observacionRepository,
			ManejoEstadoService manejoEstadoService,
			EvaluacionFormatoMapper mapper) {
		this.formatoRepository = formatoRepository;
		this.observacionRepository = observacionRepository;
		this.manejoEstadoService = manejoEstadoService;
		this.mapper = mapper;
	}

	public EstadoFormatoResponseDTO iniciarEvaluacion(String idPath) {
		Long formatoId = resolverFormatoId(idPath);
		validarExistencia(formatoId);
		return EstadoFormatoResponseDTO.builder()
				.idFormato(formatoId)
				.nuevoEstado(manejoEstadoService.cambiarEstado(formatoId, AccionEstado.INICIAR_EVALUACION).name())
				.build();
	}

	public EvaluacionFormatoResponseDTO terminarEvaluacion(String idPath, AgregarObservacionDTO dto) {
		Long formatoId = resolverFormatoId(idPath);
		validarExistencia(formatoId);

		if (dto != null && dto.getObservacion() != null && !dto.getObservacion().isBlank()) {
			observacionRepository.guardar(formatoId, dto.getObservacion());
		}
		manejoEstadoService.cambiarEstado(formatoId, AccionEstado.ENVIAR_CON_OBSERVACIONES);

		EvaluacionFormato formato = formatoRepository.findById(formatoId)
				.orElseThrow(() -> new IllegalArgumentException("No existe el formato con id " + formatoId));
		String ultimaObservacion = observacionRepository.obtenerUltimaPorFormatoId(formatoId).orElse(null);
		return mapper.toResponse(formato, ultimaObservacion);
	}

	public EstadoFormatoResponseDTO aprobarFormato(String idPath) {
		Long formatoId = resolverFormatoId(idPath);
		validarExistencia(formatoId);
		return EstadoFormatoResponseDTO.builder()
				.idFormato(formatoId)
				.nuevoEstado(manejoEstadoService.cambiarEstado(formatoId, AccionEstado.APROBAR).name())
				.build();
	}

	public EstadoFormatoResponseDTO denegarFormato(String idPath) {
		Long formatoId = resolverFormatoId(idPath);
		validarExistencia(formatoId);
		return EstadoFormatoResponseDTO.builder()
				.idFormato(formatoId)
				.nuevoEstado(manejoEstadoService.cambiarEstado(formatoId, AccionEstado.DENEGAR).name())
				.build();
	}

	private void validarExistencia(Long formatoId) {
		if (!formatoRepository.existsById(formatoId)) {
			throw new IllegalArgumentException("No existe el formato con id " + formatoId);
		}
	}

	private Long resolverFormatoId(String idPath) {
		if (idPath == null || idPath.isBlank()) {
			throw new IllegalArgumentException("Debe enviar id del formato en el request param");
		}
		return Long.valueOf(idPath);
	}
}

