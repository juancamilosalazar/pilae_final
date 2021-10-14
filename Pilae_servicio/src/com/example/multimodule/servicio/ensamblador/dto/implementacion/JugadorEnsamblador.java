package com.example.multimodule.servicio.ensamblador.dto.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.EnsambladorDTO;
import main.com.example.multimodule.dominio.JugadorDominio;
import main.com.example.multimodule.dto.Jugador;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class JugadorEnsamblador implements EnsambladorDTO<Jugador, JugadorDominio> {

    private ModelMapper modelMapper = new ModelMapper();

    private static final EnsambladorDTO<Jugador, JugadorDominio> instancia = new JugadorEnsamblador();

    private JugadorEnsamblador() {
    }

    public static EnsambladorDTO<Jugador, JugadorDominio> obtenerJugadorEnsambladorDTO() {
        return instancia;
    }

    @Override
    public JugadorDominio ensamblarDominio(Jugador dto) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "El objeto es nullo";
            String mensajeTecnico = "El objeto es nullo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        return modelMapper.map(dto,JugadorDominio.class);
    }

    @Override
    public Jugador ensamblarDTO(JugadorDominio dominio) {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "El objeto es nullo";
            String mensajeTecnico = "El objeto es nullo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(dominio,Jugador.class);
    }

    @Override
    public List<Jugador> ensamblarListaDTO(List<JugadorDominio> listaDominios) {

        List<Jugador> listaDTOs = new ArrayList<>();

        if (!UtilObjeto.objetoEsNulo(listaDominios)) {
            for (JugadorDominio JugadorDominio : listaDominios) {
                listaDTOs.add(ensamblarDTO(JugadorDominio));
            }
        }
        return listaDTOs;
    }
}
