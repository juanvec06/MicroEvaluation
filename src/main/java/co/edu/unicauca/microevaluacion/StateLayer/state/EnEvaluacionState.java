package co.edu.unicauca.microevaluacion.StateLayer.state;

import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EstadoFormato;

public class EnEvaluacionState implements FormatoState {
	@Override
	public EstadoFormato resolverSiguienteEstado(AccionEstado accion) {
		if (accion == AccionEstado.APROBAR) {
			return EstadoFormato.APPROVED;
		}
		if (accion == AccionEstado.DENEGAR) {
			return EstadoFormato.NOT_APPROVED;
		}
		if (accion == AccionEstado.ENVIAR_CON_OBSERVACIONES) {
			return EstadoFormato.FORMULATED_WITH_OBSERVATIONS;
		}
		throw new IllegalStateException("Transicion no valida desde IN_EVALUATION con accion " + accion);
	}
}

