package co.edu.uco.mensajes.negocio;

import co.edu.uco.mensajes.dto.MensajeDTO;

import java.util.List;

public interface MensajeNegocio {

	void crear(MensajeDTO mensaje);

	void modificar(MensajeDTO mensaje);

	void eliminar(MensajeDTO mensaje);

	List<MensajeDTO> consultar(MensajeDTO mensaje);
}
