package com.example.multimodule.servicio.ensamblador.dto.implementacion;

import com.example.multimodule.servicio.ensamblador.dto.EnsambladorDTO;
import main.com.example.multimodule.dominio.TorneoDominio;
import main.com.example.multimodule.dto.Torneo;
import main.com.example.multimodule.transversal.excepciones.PILAEDominioExcepcion;
import main.com.example.multimodule.transversal.excepciones.base.TipoExcepcionEnum;
import main.com.example.multimodule.transversal.utilitarios.UtilObjeto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class TorneoEnsamblador implements EnsambladorDTO<Torneo, TorneoDominio> {
    private ModelMapper modelMapper = new ModelMapper();

    private static final EnsambladorDTO<Torneo, TorneoDominio> instancia = new TorneoEnsamblador();

    private TorneoEnsamblador() {
    }

    public static EnsambladorDTO<Torneo, TorneoDominio> obtenerTorneoEnsambladorDTO() {
        return instancia;
    }

    @Override
    public TorneoDominio ensamblarDominio(Torneo dto) {
        if (UtilObjeto.objetoEsNulo(dto)) {
            String mensajeUsuario = "El objeto es nullo";
            String mensajeTecnico = "El objeto es nullo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }
        return modelMapper.map(dto,TorneoDominio.class);
    }

    @Override
    public Torneo ensamblarDTO(TorneoDominio dominio) {

        if (UtilObjeto.objetoEsNulo(dominio)) {
            String mensajeUsuario = "El objeto es nullo";
            String mensajeTecnico = "El objeto es nullo";
            throw PILAEDominioExcepcion.crear(TipoExcepcionEnum.NEGOCIO, mensajeUsuario, mensajeTecnico);
        }

        return modelMapper.map(dominio,Torneo.class);
    }

    @Override
    public List<Torneo> ensamblarListaDTO(List<TorneoDominio> listaDominios) {

        List<Torneo> listaDTOs = new ArrayList<>();

        if (!UtilObjeto.objetoEsNulo(listaDominios)) {
            for (TorneoDominio TorneoDominio : listaDominios) {
                listaDTOs.add(ensamblarDTO(TorneoDominio));
            }
        }
        return listaDTOs;
    }
}
