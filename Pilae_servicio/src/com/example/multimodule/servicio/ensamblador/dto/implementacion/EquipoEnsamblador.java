package com.example.multimodule.servicio.ensamblador.dto.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.EnsambladorDTO;
import main.com.example.multimodule.dominio.EquipoDominio;
import main.com.example.multimodule.dto.Equipo;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EquipoEnsamblador implements EnsambladorDTO<Equipo, EquipoDominio> {

	private ModelMapper modelMapper = new ModelMapper();

	private static final EnsambladorDTO<Equipo, EquipoDominio> instancia = new EquipoEnsamblador();

	private EquipoEnsamblador() {
	}

	public static EnsambladorDTO<Equipo, EquipoDominio> obtenerEquipoEnsambladorDTO() {
		return instancia;
	}

	@Override
	public EquipoDominio ensamblarDominio(Equipo dto) {
		if (UtilObjeto.objetoEsNulo(dto)) {
			String mensajeUsuario = "El objeto es nullo";
			String mensajeTecnico = "El objeto es nullo";
			throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
		}
		return modelMapper.map(dto,EquipoDominio.class);
	}

	@Override
	public Equipo ensamblarDTO(EquipoDominio dominio) {

		if (UtilObjeto.objetoEsNulo(dominio)) {
			String mensajeUsuario = "El objeto es nullo";
			String mensajeTecnico = "El objeto es nullo";
			throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
		}

		return modelMapper.map(dominio,Equipo.class);
	}

	@Override
	public List<Equipo> ensamblarListaDTO(List<EquipoDominio> listaDominios) {

		List<Equipo> listaDTOs = new ArrayList<>();

		if (!UtilObjeto.objetoEsNulo(listaDominios)) {
			for (EquipoDominio equipoDominio : listaDominios) {
				listaDTOs.add(ensamblarDTO(equipoDominio));
			}
		}
		return listaDTOs;
	}
}
