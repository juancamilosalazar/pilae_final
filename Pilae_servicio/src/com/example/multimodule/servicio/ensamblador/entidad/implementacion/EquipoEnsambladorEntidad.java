package com.example.multimodule.servicio.ensamblador.entidad.implementacion;

import com.example.multimodule.entidad.EquipoEntidad;
import com.example.multimodule.servicio.ensamblador.entidad.EnsambladorEntidad;
import main.com.example.multimodule.dominio.EquipoDominio;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EquipoEnsambladorEntidad implements EnsambladorEntidad<EquipoEntidad, EquipoDominio> {

	private ModelMapper modelMapper = new ModelMapper();
	
	private static final EnsambladorEntidad<EquipoEntidad, EquipoDominio> instancia = new EquipoEnsambladorEntidad();

	private EquipoEnsambladorEntidad() {
	}

	public static EnsambladorEntidad<EquipoEntidad, EquipoDominio> obtenerEquipoEnsambladorEntidad() {
		return instancia;
	}

	@Override
	public EquipoEntidad ensamblarEntidad(EquipoDominio dominio) {

		if (UtilObjeto.objetoEsNulo(dominio)) {
			String mensajeUsuario = "objeto nulo";
			String mensajeTecnico = "objeto nulo";
			throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
		}

		return modelMapper.map(dominio,EquipoEntidad.class);
	}

	@Override
	public EquipoDominio ensamblarDominio(EquipoEntidad entidad) {

		if (UtilObjeto.objetoEsNulo(entidad)) {
			String mensajeUsuario = "objeto nulo";
			String mensajeTecnico = "objeto nulo";
			throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
		}

		return modelMapper.map(entidad,EquipoDominio.class);
	}

	@Override
	public List<EquipoDominio> ensamblarListaDominio(List<EquipoEntidad> listaEntidades) {
		List<EquipoDominio> listaDominios = new ArrayList<>();

		if (!UtilObjeto.objetoEsNulo(listaEntidades)) {
			for (EquipoEntidad equipoEntidad : listaEntidades) {
				listaDominios.add(ensamblarDominio(equipoEntidad));
			}
		}

		return listaDominios;
	}
}
