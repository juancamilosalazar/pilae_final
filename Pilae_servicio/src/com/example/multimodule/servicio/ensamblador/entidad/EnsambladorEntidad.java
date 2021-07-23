package com.example.multimodule.servicio.ensamblador.entidad;

import java.util.List;

public interface EnsambladorEntidad<E, D> {

	E ensamblarEntidad(D dominio);

	D ensamblarDominio(E entidad);

	List<D> ensamblarListaDominio(List<E> listaEntidades);
}
