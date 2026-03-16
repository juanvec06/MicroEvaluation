package co.edu.unicauca.microevaluacion.AccessDataLayer.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EstadoFormato;
import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EvaluacionFormato;

@Repository
public class EvaluacionFormatoRepository {
	private final JdbcTemplate jdbcTemplate;

	public EvaluacionFormatoRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean existsById(Long id) {
		Integer count = jdbcTemplate.queryForObject(
				"SELECT COUNT(1) FROM evaluacion_formatos WHERE id = ?",
				Integer.class,
				id);
		return count != null && count > 0;
	}

	public Optional<EvaluacionFormato> findById(Long id) {
		return jdbcTemplate.query(
				"SELECT id, titulo, objetivo_general, estado, fecha_creacion, fecha_actualizacion FROM evaluacion_formatos WHERE id = ?",
				(rs, rowNum) -> mapRow(rs),
				id).stream().findFirst();
	}

	public void actualizarEstado(Long id, EstadoFormato nuevoEstado) {
		jdbcTemplate.update(
				"UPDATE evaluacion_formatos SET estado = ?, fecha_actualizacion = CURRENT_TIMESTAMP WHERE id = ?",
				nuevoEstado.name(),
				id);
	}

	private EvaluacionFormato mapRow(ResultSet rs) throws SQLException {
		return EvaluacionFormato.builder()
				.id(rs.getLong("id"))
				.titulo(rs.getString("titulo"))
				.objetivoGeneral(rs.getString("objetivo_general"))
				.estado(EstadoFormato.valueOf(rs.getString("estado")))
				.fechaCreacion(toLocalDateTime(rs.getTimestamp("fecha_creacion")))
				.fechaActualizacion(toLocalDateTime(rs.getTimestamp("fecha_actualizacion")))
				.build();
	}

	private LocalDateTime toLocalDateTime(Timestamp timestamp) {
		return timestamp == null ? null : timestamp.toLocalDateTime();
	}
}

