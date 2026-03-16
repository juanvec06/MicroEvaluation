package co.edu.unicauca.microevaluacion.StateLayer.state;

import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EstadoFormato;

public class AprobadoState implements FormatoState {
	@Override
	public EstadoFormato resolverSiguienteEstado(AccionEstado accion) {
		throw new IllegalStateException("El estado APPROVED no permite nuevas transiciones");
	}
}

