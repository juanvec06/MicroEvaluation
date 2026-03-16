package co.edu.unicauca.microevaluacion.StateLayer.context;

import org.springframework.stereotype.Component;

import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EstadoFormato;
import co.edu.unicauca.microevaluacion.StateLayer.state.AccionEstado;
import co.edu.unicauca.microevaluacion.StateLayer.state.AprobadoState;
import co.edu.unicauca.microevaluacion.StateLayer.state.EnEvaluacionState;
import co.edu.unicauca.microevaluacion.StateLayer.state.EnFormulacionState;
import co.edu.unicauca.microevaluacion.StateLayer.state.FormatoState;
import co.edu.unicauca.microevaluacion.StateLayer.state.FormuladoConObservacionesState;
import co.edu.unicauca.microevaluacion.StateLayer.state.NoAprobadoState;

@Component
public class FormatoStateContext {
	public EstadoFormato resolverSiguienteEstado(EstadoFormato estadoActual, AccionEstado accion) {
		FormatoState estado = obtenerImplementacion(estadoActual);
		return estado.resolverSiguienteEstado(accion);
	}

	private FormatoState obtenerImplementacion(EstadoFormato estadoActual) {
		return switch (estadoActual) {
			case FORMULATED -> new EnFormulacionState();
			case IN_EVALUATION -> new EnEvaluacionState();
			case FORMULATED_WITH_OBSERVATIONS -> new FormuladoConObservacionesState();
			case APPROVED -> new AprobadoState();
			case NOT_APPROVED -> new NoAprobadoState();
		};
	}
}

