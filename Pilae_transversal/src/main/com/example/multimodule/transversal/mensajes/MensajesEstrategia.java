package main.com.example.multimodule.transversal.mensajes;


import co.edu.uco.mensajes.dto.MensajeDTO;

public interface MensajesEstrategia {

	void sincronizar(MensajeDTO mensaje);

	MensajeDTO obtenerMensaje(String codigo);

}
