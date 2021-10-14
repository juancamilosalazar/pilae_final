package co.edu.uco.mensajes.datos.dao;

import co.edu.uco.mensajes.dto.AplicacionDTO;

import java.util.List;

public interface AplicacionDAO {

	void crear(AplicacionDTO aplicacion);

	void modificar(AplicacionDTO aplicacion);

	void eliminar(AplicacionDTO aplicacion);

	List<AplicacionDTO> consultar(AplicacionDTO aplicacion);

}
