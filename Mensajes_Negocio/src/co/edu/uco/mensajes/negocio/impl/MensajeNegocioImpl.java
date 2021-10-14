package co.edu.uco.mensajes.negocio.impl;

import co.edu.uco.mensajes.datos.dao.AplicacionDAO;
import co.edu.uco.mensajes.dto.AplicacionDTO;
import co.edu.uco.mensajes.dto.MensajeDTO;
import co.edu.uco.mensajes.negocio.MensajeNegocio;
import co.edu.uco.transversal.utilitarios.UtilTexto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeNegocioImpl implements MensajeNegocio {

	@Autowired
	private AplicacionDAO aplicacionDao;

	@Override
	public void crear(MensajeDTO mensaje) {
		List<AplicacionDTO> listaAplicaciones = aplicacionDao.consultar(mensaje.getAplicacion());

		if (listaAplicaciones.isEmpty()) {
			AplicacionDTO aplicacion = AplicacionDTO.crear().setCodigo(mensaje.getAplicacion().getCodigo()).setNombre(mensaje.getAplicacion().getNombre());
			aplicacion.getMensajes().add(mensaje);
			aplicacionDao.crear(aplicacion);
		} else {
			AplicacionDTO aplicacion = listaAplicaciones.get(0);

			if (aplicacion.getMensajes().stream().filter(mess -> mess.getCodigo().equals(mensaje.getCodigo())).count() == 0) {
				aplicacion.getMensajes().add(mensaje);
				aplicacionDao.modificar(aplicacion);
			}
		}
	}

	@Override
	public void modificar(MensajeDTO mensaje) {

		eliminar(mensaje);

		List<AplicacionDTO> listaAplicaciones = aplicacionDao.consultar(mensaje.getAplicacion());

		if (!listaAplicaciones.isEmpty()) {
			AplicacionDTO aplicacion = listaAplicaciones.get(0);
			aplicacion.getMensajes().add(mensaje);
			aplicacionDao.modificar(aplicacion);
		}
	}

	@Override
	public void eliminar(MensajeDTO mensaje) {
		List<AplicacionDTO> listaAplicaciones = aplicacionDao.consultar(mensaje.getAplicacion());

		if (!listaAplicaciones.isEmpty()) {
			AplicacionDTO aplicacion = listaAplicaciones.get(0);

			while (aplicacion.getMensajes().stream().filter(mess -> mess.getCodigo().equals(mensaje.getCodigo())).count() > 0) {
				aplicacion.getMensajes().remove(aplicacion.getMensajes().stream().filter(mess -> mess.getCodigo().equals(mensaje.getCodigo())).findFirst().get());
				aplicacionDao.modificar(aplicacion);
			}
		}
	}

	@Override
	public List<MensajeDTO> consultar(MensajeDTO mensaje) {
		List<AplicacionDTO> listaAplicaciones = aplicacionDao.consultar(mensaje.getAplicacion());
		List<MensajeDTO> listaRetorno = new ArrayList<>();

		if (!listaAplicaciones.isEmpty()) {

			AplicacionDTO aplicacion = listaAplicaciones.get(0);

			if (UtilTexto.estaVacia(mensaje.getCodigo())) {
				listaRetorno = aplicacion.getMensajes();
			} else {
				listaRetorno = aplicacion.getMensajes().stream().filter(mess -> mess.getCodigo().equals(mensaje.getCodigo())).collect(Collectors.toList());
			}
		}
		return listaRetorno;
	}

}
