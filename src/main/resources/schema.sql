DROP TABLE IF EXISTS observaciones_evaluacion;
DROP TABLE IF EXISTS evaluacion_formatos;

CREATE TABLE evaluacion_formatos (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(255) NOT NULL,
	objetivo_general VARCHAR(1000),
	estado VARCHAR(40) NOT NULL DEFAULT 'FORMULATED',
	fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT chk_estado_formato CHECK (
		estado IN (
			'FORMULATED',
			'IN_EVALUATION',
			'FORMULATED_WITH_OBSERVATIONS',
			'APPROVED',
			'NOT_APPROVED'
		)
	)
);

CREATE TABLE observaciones_evaluacion (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	formato_id BIGINT NOT NULL,
	observacion VARCHAR(1000) NOT NULL,
	fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_observacion_formato
		FOREIGN KEY (formato_id) REFERENCES evaluacion_formatos(id) ON DELETE CASCADE
);
