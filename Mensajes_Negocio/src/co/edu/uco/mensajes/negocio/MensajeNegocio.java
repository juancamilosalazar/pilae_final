package co.edu.uco.mensajes.negocio;

import java.util.List;

import co.edu.uco.mensajes.dto.MensajeDTO;

public interface MensajeNegocio {

	void crear(MensajeDTO mensaje);

	void modificar(MensajeDTO mensaje);

	void eliminar(MensajeDTO mensaje);

	List<MensajeDTO> consultar(MensajeDTO mensaje);
}
