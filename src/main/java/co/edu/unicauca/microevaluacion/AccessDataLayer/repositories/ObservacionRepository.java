package co.edu.unicauca.microevaluacion.AccessDataLayer.repositories;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ObservacionRepository {
	private final JdbcTemplate jdbcTemplate;

	public ObservacionRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void guardar(Long formatoId, String observacion) {
		jdbcTemplate.update(
				"INSERT INTO observaciones_evaluacion (formato_id, observacion) VALUES (?, ?)",
				formatoId,
				observacion);
	}

	public Optional<String> obtenerUltimaPorFormatoId(Long formatoId) {
		return jdbcTemplate.query(
				"SELECT observacion FROM observaciones_evaluacion WHERE formato_id = ? ORDER BY fecha_creacion DESC, id DESC LIMIT 1",
				(rs, rowNum) -> rs.getString("observacion"),
				formatoId).stream().findFirst();
	}
}

