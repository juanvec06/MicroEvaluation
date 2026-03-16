package co.edu.unicauca.microevaluacion.StateLayer.state;

import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EstadoFormato;

public class FormuladoConObservacionesState implements FormatoState {
	@Override
	public EstadoFormato resolverSiguienteEstado(AccionEstado accion) {
		if (accion == AccionEstado.INICIAR_EVALUACION) {
			return EstadoFormato.IN_EVALUATION;
		}
		throw new IllegalStateException("Transicion no valida desde FORMULATED_WITH_OBSERVATIONS con accion " + accion);
	}
}

