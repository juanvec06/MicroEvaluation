INSERT INTO evaluacion_formatos (titulo, objetivo_general, estado) VALUES
('Formato de practicas empresariales', 'Evaluar viabilidad y alcance del proyecto de practica', 'FORMULATED'),
('Formato de investigacion aplicada', 'Evaluar pertinencia metodologica del proyecto', 'IN_EVALUATION'),
('Formato de trabajo de grado', 'Revisar consistencia entre objetivos y entregables', 'FORMULATED_WITH_OBSERVATIONS');

INSERT INTO observaciones_evaluacion (formato_id, observacion) VALUES
(3, 'Ajustar redaccion del objetivo general para mayor claridad'),
(3, 'Agregar criterio de evaluacion de impacto esperado');