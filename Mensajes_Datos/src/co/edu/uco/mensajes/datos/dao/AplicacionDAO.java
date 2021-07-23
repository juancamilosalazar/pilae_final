package co.edu.uco.mensajes.datos.dao;

import java.util.List;

import co.edu.uco.mensajes.dto.AplicacionDTO;

public interface AplicacionDAO {

	void crear(AplicacionDTO aplicacion);

	void modificar(AplicacionDTO aplicacion);

	void eliminar(AplicacionDTO aplicacion);

	List<AplicacionDTO> consultar(AplicacionDTO aplicacion);

}
