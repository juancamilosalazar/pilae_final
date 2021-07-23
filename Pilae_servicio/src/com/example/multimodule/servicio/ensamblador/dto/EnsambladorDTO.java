package com.example.multimodule.servicio.ensamblador.dto;

import java.util.List;


public interface EnsambladorDTO<T, D> {

	D ensamblarDominio(T dto);

	T ensamblarDTO(D dominio);

	List<T> ensamblarListaDTO(List<D> listaDominios);
}
