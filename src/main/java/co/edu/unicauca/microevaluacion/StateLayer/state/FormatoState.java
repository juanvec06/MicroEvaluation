package co.edu.unicauca.microevaluacion.StateLayer.state;

import co.edu.unicauca.microevaluacion.AccessDataLayer.models.EstadoFormato;

public interface FormatoState {
	EstadoFormato resolverSiguienteEstado(AccionEstado accion);
}

