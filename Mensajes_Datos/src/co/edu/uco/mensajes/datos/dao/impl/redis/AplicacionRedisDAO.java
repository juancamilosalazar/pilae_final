package co.edu.uco.mensajes.datos.dao.impl.redis;

import co.edu.uco.mensajes.datos.dao.AplicacionDAO;
import co.edu.uco.mensajes.dto.AplicacionDTO;
import co.edu.uco.transversal.utilitarios.UtilObjeto;
import co.edu.uco.transversal.utilitarios.UtilTexto;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AplicacionRedisDAO implements AplicacionDAO {

	private RedisTemplate<String, AplicacionDTO> plantillaRedis;
	private HashOperations<String, String, AplicacionDTO> operaciones;

	public AplicacionRedisDAO(RedisTemplate<String, AplicacionDTO> plantillaRedis) {
		super();
		this.plantillaRedis = plantillaRedis;
		this.operaciones = plantillaRedis.opsForHash();
	}

	@Override
	public void crear(AplicacionDTO aplicacion) {
		operaciones.put("pilae", aplicacion.getCodigo(), aplicacion);
	}

	@Override
	public void modificar(AplicacionDTO aplicacion) {
		crear(aplicacion);
	}

	@Override
	public void eliminar(AplicacionDTO aplicacion) {
		operaciones.delete("pilae", aplicacion.getCodigo());
	}

	@Override
	public List<AplicacionDTO> consultar(AplicacionDTO aplicacion) {
		List<AplicacionDTO> listaRetorno = null;

		if (!UtilObjeto.objetoEsNulo(aplicacion) && !UtilTexto.estaVacia(aplicacion.getCodigo())) {
			listaRetorno = new ArrayList<>();

			AplicacionDTO aplicacionTmp = operaciones.entries("pilae").get(aplicacion.getCodigo());

			if (!UtilObjeto.objetoEsNulo(aplicacionTmp)) {
				listaRetorno.add(aplicacionTmp);
			}
		} else {
			listaRetorno = new ArrayList<>(operaciones.entries("pilae").values());
		}

		return listaRetorno;
	}
}